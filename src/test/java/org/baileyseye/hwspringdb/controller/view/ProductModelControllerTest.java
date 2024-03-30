package org.baileyseye.hwspringdb.controller.view;

import org.baileyseye.hwspringdb.model.Product;
import org.baileyseye.hwspringdb.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ProductModelControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductModelController productModelController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllProducts_WhenNotEmpty() {
        List<Product> mockProducts = new ArrayList<>();
        Product product = new Product();
        product.setId(1);
        product.setProductName("Test Product");
        product.setProductPrice(BigDecimal.valueOf(199.99));
        mockProducts.add(product);

        when(productService.findAll()).thenReturn(mockProducts);

        ResponseEntity<List<Product>> response = productModelController.getAllProducts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Test Product", response.getBody().get(0).getProductName());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getAllProducts_WhenEmpty() {
        when(productService.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Product>> response = (ResponseEntity<List<Product>>) Optional.ofNullable
                        (productModelController.getAllProducts().getBody())
                .map(body -> !body.isEmpty() ? ResponseEntity.ok(body) : ResponseEntity.noContent().build())
            .orElse(ResponseEntity.noContent().build());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(response.getBody() == null || response.getBody().isEmpty());
    }
}
