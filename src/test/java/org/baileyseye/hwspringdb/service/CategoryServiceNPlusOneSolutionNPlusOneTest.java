package org.baileyseye.hwspringdb.service;

import org.baileyseye.hwspringdb.model.Category;
import org.baileyseye.hwspringdb.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoryServiceNPlusOneSolutionNPlusOneTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getCategoryById_existingId_returnCategory() {
        Integer categoryId = 1;
        Category expectedCategory = new Category();
        expectedCategory.setId(categoryId);
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(expectedCategory));

        Category actualCategory = categoryService.getCategoryById(categoryId);

        assertEquals(expectedCategory, actualCategory);
        verify(categoryRepository).findById(categoryId);
    }

    @Test
    void getCategoryById_nonExistingId_returnNull() {
        Integer categoryId = 99;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        Category actualCategory = categoryService.getCategoryById(categoryId);

        assertNull(actualCategory);
        verify(categoryRepository).findById(categoryId);
    }
}