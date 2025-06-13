package jp.co.hoge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.co.hoge.entity.Goal;

public interface GoalRepository extends JpaRepository<Goal, Integer> {
    @Query("SELECT g FROM Goal g WHERE g.userId = :userId")
    Optional<Goal> findByUserId(Long userId);
}
