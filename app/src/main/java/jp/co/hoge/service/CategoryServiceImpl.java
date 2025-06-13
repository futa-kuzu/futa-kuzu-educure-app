package jp.co.hoge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.hoge.entity.Category;
import jp.co.hoge.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    
    @Override
    public String findNameById(int categoryId) {
    	return categoryRepository.findById(categoryId)
    			.map(Category::getCategoryName)
    			.orElse("");
    }
}
