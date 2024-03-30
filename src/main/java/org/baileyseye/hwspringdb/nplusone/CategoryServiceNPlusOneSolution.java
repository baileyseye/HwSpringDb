package org.baileyseye.hwspringdb.nplusone;

import org.baileyseye.hwspringdb.model.Category;
import org.baileyseye.hwspringdb.repositories.CategoryRepository;

import java.util.List;

public class CategoryServiceNPlusOneSolution {

    private final CategoryRepository categoryRepository;

    public CategoryServiceNPlusOneSolution(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategoriesWithProductsWithoutNPlusOne() {
        return categoryRepository.findAllWithProducts();
    }
}