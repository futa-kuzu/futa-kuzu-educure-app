package jp.co.hoge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.hoge.form.SignupForm;
import jp.co.hoge.service.AuthService;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final AuthService authService;
    private final HttpSession session;

    public SignupController(AuthService authService, HttpSession session) {
        this.authService = authService;
        this.session = session;
    }

    // 入力画面の表示
    @GetMapping
    public String showSignupForm(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "signup";
    }

    // 確認画面への遷移
    @PostMapping("/confirm")
    public String confirm(@ModelAttribute("signupForm") @Valid SignupForm form,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {
            return "signup"; // 入力エラー時は入力画面へ戻る
        }

        // 一時的にフォームをセッションに保存
        session.setAttribute("signupForm", form);
        return "signupConfirm";
    }

    // 登録完了処理
    @PostMapping("/complete")
    public String complete(@RequestParam("confirmPassword") String confirmPassword,
                           Model model) {

        // セッションからフォームを取得
        SignupForm form = (SignupForm) session.getAttribute("signupForm");
        if (form == null) {
            return "redirect:/signup"; // セッション切れ時
        }

        // パスワード一致チェック
        if (!form.getPassword().equals(confirmPassword)) {
            model.addAttribute("signupForm", form);
            model.addAttribute("confirmPasswordError", "パスワードが一致しません。");
            return "signupConfirm";
        }

        // ユーザー登録処理
        boolean success = authService.registerUser(form, 2);
        session.removeAttribute("signupForm");

        if (!success) {
            model.addAttribute("error", "ユーザー登録に失敗しました。");
            return "signup";
        }
        
        //登録完了後、メニューへ遷移させるためにセッションを保存
        authService.findByLoginId(form.getLoginId()).ifPresent(user -> {
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getUserId());
        });


        return "signupComplete";
    }
    
    // 入力画面へ戻る
    @GetMapping("/back")
    public String backToSignupForm(Model model) {
        SignupForm form = (SignupForm) session.getAttribute("signupForm");
        if (form == null) {
            form = new SignupForm();
        }
        model.addAttribute("signupForm", form);
        return "signup";
    }

}

