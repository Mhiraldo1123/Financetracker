package com.marvin.financetracker.service;

import com.marvin.financetracker.exception.ResourceNotFoundException;
import com.marvin.financetracker.model.Category;
import com.marvin.financetracker.model.CategoryType;
import com.marvin.financetracker.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category findByName(String name){
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with name: " + name));
    }

    public Category findByType(CategoryType categorytype){
        return categoryRepository.findByType(categorytype)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with type: " + categorytype));
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    public Category updateCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
