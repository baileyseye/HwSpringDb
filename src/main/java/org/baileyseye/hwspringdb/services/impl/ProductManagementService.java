package org.baileyseye.hwspringdb.services.impl;

import lombok.RequiredArgsConstructor;
import org.baileyseye.hwspringdb.repositories.ProductRepository;
import org.baileyseye.hwspringdb.model.Product;
import org.baileyseye.hwspringdb.services.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductManagementService implements ProductManager {


    private ProductRepository productRepository;

    public Product addProduct(String name, double price) {
        Product product = new Product();
        product.setProductName(name);
        product.setProductPrice(price);
        return productRepository.save(product);
    }

    @Override
    public List<Product> FindAll() {
        return null;
    }
}