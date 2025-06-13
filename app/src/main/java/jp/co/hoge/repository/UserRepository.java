package jp.co.hoge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.hoge.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByLoginIdOrEmail(String loginId, String email);
	Optional<User> findByLoginId(String loginId);
	Optional<User> findByEmail(String email);
}
