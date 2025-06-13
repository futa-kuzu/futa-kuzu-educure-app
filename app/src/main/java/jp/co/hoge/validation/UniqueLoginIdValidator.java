package jp.co.hoge.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jp.co.hoge.repository.UserRepository;

@Component
public class UniqueLoginIdValidator implements ConstraintValidator<UniqueLoginId, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String loginId, ConstraintValidatorContext context) {
        if (loginId == null || loginId.isEmpty()) {
            return true; // 空欄チェックは他で行う
        }
        return userRepository.findByLoginId(loginId).isEmpty();
    }
}
