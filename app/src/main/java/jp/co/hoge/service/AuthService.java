package jp.co.hoge.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.co.hoge.entity.User;
import jp.co.hoge.form.SignupForm;
import jp.co.hoge.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // パスワード認証
    public User authenticateUser(String loginIdOrEmail, String password) {
        Optional<User> userOpt = userRepository.findByLoginIdOrEmail(loginIdOrEmail, loginIdOrEmail);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return userOpt.get();
        }
        return null;
    }

    // パスワード更新
    @Transactional
    public boolean updatePassword(String loginIdOrEmail, String newPassword) {
        Optional<User> userOpt = userRepository.findByLoginIdOrEmail(loginIdOrEmail, loginIdOrEmail);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    // 新規登録
    @Transactional
    public boolean registerUser(SignupForm form, int roleId) {
        boolean exists = userRepository.findByLoginIdOrEmail(form.getLoginId(), form.getEmail()).isPresent();
        if (exists) {
            return false;
        }

        User newUser = new User();
        newUser.setLoginId(form.getLoginId());
        newUser.setEmail(form.getEmail());
        newUser.setUserName(form.getUserName());
        newUser.setPassword(form.getPassword());
        newUser.setRoleId(roleId);

        LocalDateTime now = LocalDateTime.now();
        newUser.setCreateAt(now);
        newUser.setUpdateAt(now);

        userRepository.save(newUser);
        return true;
    }


    // 退会
    @Transactional
    public boolean verifyPassword(Long userId, String rawPassword) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) return false;
        User user = userOpt.get();
        return rawPassword.equals(user.getPassword());
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
    
    //ユーザー一覧取得
    public List<User> findAllUsers(){
    	return userRepository.findAll();
    }
    
    public Optional<User> findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }


}
