package jp.co.hoge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.hoge.entity.User;
import jp.co.hoge.form.PassChangeForm;
import jp.co.hoge.service.AuthService;

@Controller
@RequestMapping("/passChange")
public class PassChangeController {

	private final AuthService authService;
	private final HttpSession session;
	
	public PassChangeController(AuthService authService, HttpSession session) {
		this.authService = authService;
		this.session = session;
	}
	
	//入力画面表示
	@GetMapping
	public String showInputForm(Model model) {
		model.addAttribute("passChangeForm", new PassChangeForm());
		return "passChange";
	}
	
	//確認画面へ遷移
	@PostMapping("/confirm")
	public String showConfirmPage(@ModelAttribute("passChangeForm")
								  @Valid PassChangeForm form,
								  BindingResult bindingResult,
								  Model model) {
		
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(e -> System.out.println(" - " + e));
			return "passChange";
		}
		
		//セッションに一時保存
		session.setAttribute("passChangeForm", form);
		return "passChangeConfirm";
	}
	
	//パスワード更新と完了画面へ遷移
	@PostMapping("/complete")
	public String changePassword(@ModelAttribute("passChangeForm") PassChangeForm form, Model model) {
	    PassChangeForm sessionForm = (PassChangeForm) session.getAttribute("passChangeForm");
	    if (sessionForm == null) {
	        return "redirect:/passChange";
	    }

	    //パスワード一致チェック
	    if (!sessionForm.getNewPassword().equals(form.getConfirmPassword())) {
	        model.addAttribute("confirmPasswordError", "パスワードが一致しません。");
	        model.addAttribute("passChangeForm", sessionForm);
	        return "passChangeConfirm";
	    }

	    //パスワード更新
	    boolean updated = authService.updatePassword(sessionForm.getLoginIdOrEmail(), sessionForm.getNewPassword());
	    if (!updated) {
	        model.addAttribute("error", "パスワードの変更に失敗しました。");
	        return "passChange";
	    }

	    //自動ログイン（再認証）
	    User user = authService.authenticateUser(sessionForm.getLoginIdOrEmail(), sessionForm.getNewPassword());
	    if (user != null) {
	        session.setAttribute("user", user);
	        session.setAttribute("userId", user.getUserId());
	    } else {
	        //ログインできない場合はログイン画面へ
	        return "redirect:/login";
	    }

	    // セッションのパスワード変更フォームをクリア
	    session.removeAttribute("passChangeForm");

	    return "passChangeComplete";
	}
	
	// 確認画面から戻る用
	@GetMapping("/back")
	public String backToInputForm(Model model) {
	    PassChangeForm sessionForm = (PassChangeForm) session.getAttribute("passChangeForm");
	    if (sessionForm != null) {
	        model.addAttribute("passChangeForm", sessionForm);
	    } else {
	        model.addAttribute("passChangeForm", new PassChangeForm());
	    }
	    return "passChange";
	}


}
