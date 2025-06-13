package jp.co.hoge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.hoge.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAll();
}
