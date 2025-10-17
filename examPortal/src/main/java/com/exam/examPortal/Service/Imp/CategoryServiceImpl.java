package com.exam.examPortal.Service.Imp;

import com.exam.examPortal.Service.CategoryService;
import com.exam.examPortal.model.exam.Category;
import com.exam.examPortal.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Set<Category> getCategories(){
        return new LinkedHashSet<>(categoryRepository.findAll());
    }

    @Override
    public Category getCategory(Long category) {
        return categoryRepository.findById(category).get();
    }

    @Override
    public void deleteCategory(Long categoryId) {
    Category category = new Category();
    category.setCid(categoryId);
        categoryRepository.delete(category);
    }
}
