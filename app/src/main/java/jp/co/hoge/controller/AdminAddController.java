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
@RequestMapping("/admin/user")
public class AdminAddController {

    private final AuthService authService;
    private final HttpSession session;

    public AdminAddController(AuthService authService, HttpSession session) {
        this.authService = authService;
        this.session = session;
    }

    // 入力画面の表示
    @GetMapping("/add")
    public String showAdminUserAddForm(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "userAdd";
    }

    // 確認画面への遷移
    @PostMapping("/confirm")
    public String confirm(@ModelAttribute("signupForm") @Valid SignupForm form,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {
            return "userAdd";
        }

        // セッションに保存（roleId は登録時に渡す）
        session.setAttribute("signupForm", form);
        return "userAddConfirm";
    }

    // 登録完了処理
    @PostMapping("/complete")
    public String complete(@RequestParam("confirmPassword") String confirmPassword,
                           Model model) {

        SignupForm form = (SignupForm) session.getAttribute("signupForm");
        if (form == null) {
            return "redirect:/admin/user/add";
        }

        if (!form.getPassword().equals(confirmPassword)) {
            model.addAttribute("signupForm", form);
            model.addAttribute("confirmPasswordError", "パスワードが一致しません。");
            return "userAddConfirm";
        }

        // 管理者ユーザーとして登録（roleId = 1）
        boolean success = authService.registerUser(form, 1);
        session.removeAttribute("signupForm");

        if (!success) {
            model.addAttribute("error", "ユーザー登録に失敗しました。");
            return "userAdd";
        }

        return "userAddComplete";
    }
    
    @GetMapping("/back")
    public String backToForm(Model model) {
        SignupForm form = (SignupForm) session.getAttribute("signupForm");
        if (form == null) {
            form = new SignupForm();
        }
        model.addAttribute("signupForm", form);
        return "userAdd";
    }


}
