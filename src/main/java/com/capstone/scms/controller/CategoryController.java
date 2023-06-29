package com.capstone.scms.controller;

import com.capstone.scms.model.Category;
import com.capstone.scms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    private ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

    @GetMapping("/list")
    private ResponseEntity<List<Category>> getListCategory() {
        List<Category> categoryList = categoryService.getListCategory();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/list/{categoryId}")
    private ResponseEntity<Category> getCategory(@PathVariable("categoryId") Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/update/{categoryId}")
    private ResponseEntity<Category> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(categoryId, category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/delete/{categoryId}")
    private ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("category deleted id: " + categoryId);
    }
}
