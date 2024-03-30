package org.baileyseye.hwspringdb.DTO;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductDTOTest {

    @Test
    public void testProductName() {
        ProductDTO productDTO = new ProductDTO();
        String expectedName = "Test Product";
        productDTO.setProductName(expectedName);

        assertEquals(expectedName, productDTO.getProductName(),
                "Product name should match the set value");
    }

    @Test
    public void testProductPrice() {
        ProductDTO productDTO = new ProductDTO();
        BigDecimal expectedPrice = BigDecimal.valueOf(99.99);
        productDTO.setProductPrice(expectedPrice);

        assertEquals(expectedPrice, productDTO.getProductPrice(),
                "Product price should match the set value");
    }

    @Test
    public void testCategoryId() {
        ProductDTO productDTO = new ProductDTO();
        Integer expectedCategoryId = 5;
        productDTO.setCategoryId(expectedCategoryId);

        assertEquals(expectedCategoryId, productDTO.getCategoryId(),
                "Category ID should match the set value");
    }
}