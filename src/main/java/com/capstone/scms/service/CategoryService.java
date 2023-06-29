package com.capstone.scms.service;

import com.capstone.scms.model.Category;
import com.capstone.scms.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getListCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Category updateCategory(Long categoryId, Category getCategory) {
        Category setCategory = categoryRepository.findById(categoryId).orElse(null);
        if (setCategory != null) {
            setCategory.setName(getCategory.getName());
            setCategory.setDescription(getCategory.getDescription());
            categoryRepository.save(setCategory);
        }
        return setCategory;
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
