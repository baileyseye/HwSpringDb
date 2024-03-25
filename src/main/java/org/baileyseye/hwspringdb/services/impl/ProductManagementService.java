package org.baileyseye.hwspringdb.services.impl;

import org.baileyseye.hwspringdb.model.Product;
import org.baileyseye.hwspringdb.repositories.ProductRepository;
import org.baileyseye.hwspringdb.services.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManagementService implements ProductManager {


    private final ProductRepository productRepository;

    @Autowired
    public ProductManagementService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}