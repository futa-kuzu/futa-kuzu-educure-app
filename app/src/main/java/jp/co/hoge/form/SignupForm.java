package jp.co.hoge.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jp.co.hoge.validation.UniqueEmail;
import jp.co.hoge.validation.UniqueLoginId;

public class SignupForm {

    @NotBlank(message = "ユーザーIDを入力してください")
    @Size(max = 50, message = "ユーザーIDは50文字以内で入力してください")
    @UniqueLoginId
    private String loginId;

    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "メールアドレスの形式で入力してください")
    @UniqueEmail
    private String email;

    @NotBlank(message = "名前を入力してください")
    @Size(max = 50, message = "名前は50文字以内で入力してください")
    private String userName;

    @NotBlank(message = "パスワードを入力してください")
    @Size(min = 6, max = 100, message = "パスワードは6文字以上100文字以内で入力してください")
    private String password;

    
    private String confirmPassword;

    
    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

