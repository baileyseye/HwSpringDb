package org.baileyseye.hwspringdb.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void printAllProducts() {
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Product Price: " + product.getProductPrice());
            System.out.println("------------------------");
        }
    }
}
