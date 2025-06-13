package jp.co.hoge.form;

import jakarta.validation.constraints.NotBlank;

public class LoginForm {
	
	@NotBlank(message = "ログインIDまたはメールアドレスを入力してください")
	private String loginIdOrEmail;
	@NotBlank(message = "パスワードを入力してください")
	private String password;
	
	public String getLoginIdOrEmail() {
		return loginIdOrEmail;
	}
	
	public void setLoginIdOrEmail(String loginIdOrEmail) {
		this.loginIdOrEmail = loginIdOrEmail;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
}
