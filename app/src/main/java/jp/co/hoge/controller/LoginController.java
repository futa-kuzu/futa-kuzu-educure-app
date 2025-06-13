package jp.co.hoge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.hoge.entity.User;
import jp.co.hoge.form.LoginForm;
import jp.co.hoge.service.AuthService;

@Controller
public class LoginController {

	@Autowired
	private AuthService authService;
	
	//ログイン画面表示
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login"; //login.jspに遷移
	}
	
	
	//ログイン処理
	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") @Valid LoginForm form,
			BindingResult bindingResult,
			HttpSession session,
			Model model) {
		
		// 入力された情報を出力（デバッグ用）
	    System.out.println("ログインIDまたはメール: " + form.getLoginIdOrEmail());
	    System.out.println("パスワード: " + form.getPassword());
	    
	    //バリデーションエラーの場合はlogin.jspへ戻る
	    if(bindingResult.hasErrors()) {
	    	System.out.println("バリデーションエラーあり！");
	    	bindingResult.getAllErrors().forEach(e -> System.out.println(e));
	    	return "login";
	    }
	    
		//ログイン情報処理
		User user = authService.authenticateUser(form.getLoginIdOrEmail(), form.getPassword());
		
		if(user != null) {
			//ログイン成功時はコンソールにメッセージを表示
			System.out.println("ログイン成功");
			System.out.println("roleId:" + user.getRoleId());
			
			//ユーザー情報をセッションに保存
			session.setAttribute("user", user);
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("roleId", user.getRoleId());
			
			//role_idによる分岐
			if(user.getRoleId() == 1) {
				return "redirect:/admin/menu";
			} else {
				return "redirect:/menu";
			}
		} else {
			//ログイン失敗時はコンソールにメッセージを表示
			System.out.println("ログイン失敗");
			
			model.addAttribute("error", "ユーザーID、メールアドレス、またはパスワードが正しくありません。");
			return "login";
		}
	}
	
	
	//ログアウト処理
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		//ログアウト画面へ遷移
		return "logout";
	}
	
}
