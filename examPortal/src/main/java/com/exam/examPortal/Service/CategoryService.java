package com.exam.examPortal.Service;

import com.exam.examPortal.model.exam.Category;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CategoryService {
    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    public Set<Category> getCategories();
    public Category getCategory(Long category);
    public void deleteCategory(Long categoryId);

}
