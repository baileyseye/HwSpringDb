package org.baileyseye.hwspringdb.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doAnswer;

import org.baileyseye.hwspringdb.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class ProductBulkServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductBulkService productBulkService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addMillionRandomProductsTest() {
        List<Object> callCounter = new ArrayList<>();
        doAnswer(invocation -> {
            List products = invocation.getArgument(0);
            callCounter.add("Called with batch size: " + products.size());
            return null;
        }).when(productRepository).saveAll(any(List.class));

        productBulkService.addMillionRandomProducts();

        int expectedBatches = 10000 / 100;
        verify(productRepository, times(expectedBatches)).saveAll(any(List.class));
    }
}