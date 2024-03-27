package org.baileyseye.hwspringdb.controller;

import org.baileyseye.hwspringdb.DTO.ProductDTO;
import org.baileyseye.hwspringdb.model.Category;
import org.baileyseye.hwspringdb.model.Product;
import org.baileyseye.hwspringdb.service.CategoryService;
import org.baileyseye.hwspringdb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());

        Category category = categoryService.getCategoryById(productDTO.getCategoryId());
        product.setCategories(category);

        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.ok(savedProduct);
    }
}