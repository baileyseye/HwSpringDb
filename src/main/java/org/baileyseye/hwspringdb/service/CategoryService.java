package org.baileyseye.hwspringdb.service;

import org.baileyseye.hwspringdb.model.Category;
import org.baileyseye.hwspringdb.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(Integer categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.orElse(null);
    }
}
