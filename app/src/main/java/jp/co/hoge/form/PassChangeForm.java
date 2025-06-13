package jp.co.hoge.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PassChangeForm {

	@NotBlank(message = "ユーザーIDまたはメールアドレスを入力してください")
	private String loginIdOrEmail;
	
	@NotBlank(message = "新しいパスワードを入力してください")
	@Size(min = 6, max = 100, message = "パスワードは6文字以上100文字以内で入力してください")
	private String newPassword;
	
	
	private String confirmPassword;
	
	
	public String getLoginIdOrEmail() {
		return loginIdOrEmail;
	}
	public void setLoginIdOrEmail(String loginIdOrEmail) {
		this.loginIdOrEmail = loginIdOrEmail;
	}
	
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
