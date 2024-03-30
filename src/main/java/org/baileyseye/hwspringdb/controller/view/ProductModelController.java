package org.baileyseye.hwspringdb.controller.view;

import org.baileyseye.hwspringdb.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.baileyseye.hwspringdb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
public class ProductModelController {

    private final ProductService productService;

    @Autowired
    public ProductModelController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/view")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }
}