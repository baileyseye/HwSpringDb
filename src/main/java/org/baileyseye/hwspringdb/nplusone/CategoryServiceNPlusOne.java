package org.baileyseye.hwspringdb.nplusone;

import org.baileyseye.hwspringdb.model.Category;
import org.baileyseye.hwspringdb.model.Product;
import org.baileyseye.hwspringdb.repositories.CategoryRepository;

import java.util.Set;

public class CategoryServiceNPlusOne {

    private final CategoryRepository categoryRepository;

    public CategoryServiceNPlusOne(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Set<Category> getAllCategoriesWithProductsButThisIsNPlusOne() {
        Set<Category> categories = (Set<Category>) categoryRepository.findAll();
        for (Category category : categories) {
            Set<Product> products = category.getProducts();
        }
        return categories;
    }
}