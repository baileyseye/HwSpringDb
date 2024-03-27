package org.baileyseye.hwspringdb.service;

import org.baileyseye.hwspringdb.model.Category;
import org.baileyseye.hwspringdb.model.Product;
import org.baileyseye.hwspringdb.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ProductBulkService {

    @Autowired
    private ProductRepository productRepository;

    public void addMillionRandomProducts() {
        List<Product> products = new ArrayList<>();
        Random random = new Random();
        int totalSaved = 0;

        for (int i = 0; i < 1000000; i++) {
            Product product = new Product();
            product.setProductName(generateRandomProductName());
            product.setProductPrice(BigDecimal.valueOf(random.nextDouble() * 1000));

            Category category = new Category();
            category.setId(random.nextInt(2) + 1);
            product.setCategories(category);

            products.add(product);

            if (i % 100 == 0 && i != 0) {
                productRepository.saveAll(products);
                totalSaved += products.size();
                products.clear();

                System.out.println("Saved " + totalSaved + " products.");
            }
        }

        if (!products.isEmpty()) {
            productRepository.saveAll(products);
            totalSaved += products.size();

            System.out.println("Saved " + totalSaved + " products.");
        }
    }

    private String generateRandomProductName() {
        return "Product-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
