package org.baileyseye.hwspringdb.service;

import org.baileyseye.hwspringdb.model.Product;
import org.baileyseye.hwspringdb.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product();
        testProduct.setId(1);
        testProduct.setProductName("Test Product");
        testProduct.setProductPrice(BigDecimal.valueOf(100));
    }

    @Test
    void addProduct_ShouldReturnProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        Product created = productService.addProduct(testProduct);

        assertThat(created).isNotNull();
        assertThat(created.getProductName()).isEqualTo("Test Product");
    }

    @Test
    void getProductsUnderPrice_ShouldReturnListOfProducts() {
        when(productRepository.findByProductPriceLessThan(any(BigDecimal.class)))
                .thenReturn(Collections.singletonList(testProduct));

        List<Product> products = productService.getProductsUnderPrice(BigDecimal.valueOf(150));

        assertThat(products).isNotEmpty();
        assertThat(products.getFirst().getProductName()).isEqualTo("Test Product");
    }

    @Test
    void getOneRandomProduct_ShouldReturnProduct() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(testProduct));

        Product product = productService.getOneRandomProduct();

        assertThat(product).isNotNull();
        assertThat(product.getProductName()).isEqualTo("Test Product");
    }

    @Test
    void getRandomProductsBatch_ShouldReturnListOfProducts() {
        when(productRepository.count()).thenReturn(1L);
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(testProduct));

        List<Product> products = productService.getRandomProductsBatch();

        assertThat(products).isNotEmpty();
        assertThat(products.getFirst().getProductName()).isEqualTo("Test Product");
    }
}