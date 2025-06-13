package jp.co.hoge.service;

import java.util.List;

import jp.co.hoge.entity.Category;

public interface CategoryService {
    List<Category> findAll();
    String findNameById(int categoryId);
}

