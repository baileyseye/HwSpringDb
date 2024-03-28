package org.baileyseye.hwspringdb.service;

import org.baileyseye.hwspringdb.model.Product;
import org.baileyseye.hwspringdb.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProductsByIds(List<Integer> productIds) {
        return productRepository.getProductsByIds(productIds);
    }

    @Cacheable(value = "productsCache")
    public List<Product> getProductsUnderPrice(BigDecimal priceLimit) {
        return productRepository.findByProductPriceLessThan(priceLimit);
    }

    @Cacheable(value = "randomProductCache",
            unless = "#result == null or T(java.util.Collections).emptyList().equals(#result)")
    public Product getOneRandomProduct() {
        List<Product> products = productRepository.findAll();
        int totalProducts = products.size();
        if (totalProducts == 0) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(totalProducts);
        return products.get(randomIndex);
    }

    @Cacheable(value = "randomProductsCache")
    public List<Product> getRandomProductsBatch() {
        List<Product> randomProducts = new ArrayList<>();
        int targetCount = 15000;

        while (randomProducts.size() < targetCount) {
            Product randomProduct = getRandomProduct();
            if (randomProduct != null) {
                randomProducts.add(randomProduct);
            } else {
                break;
            }
        }

        return randomProducts;
    }

    private Product getRandomProduct() {
        long count = productRepository.count();
        if (count == 0) {
            return null;
        }

        Random random = new Random();
        long randomId = random.nextInt((int) count) + 1;
        return productRepository.findById(randomId).orElse(null);
    }

}
