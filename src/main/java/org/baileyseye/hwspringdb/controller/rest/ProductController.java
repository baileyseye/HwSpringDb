package org.baileyseye.hwspringdb.controller.rest;

import org.baileyseye.hwspringdb.DTO.ProductDTO;
import org.baileyseye.hwspringdb.model.Category;
import org.baileyseye.hwspringdb.model.Product;
import org.baileyseye.hwspringdb.service.CategoryService;
import org.baileyseye.hwspringdb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());

        Category category = categoryService.getCategoryById(productDTO.getCategoryId());
        product.setCategories(category);

        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping(params = "priceLimit")
    public ResponseEntity<List<Product>> getProductsUnderPriceLimit(@RequestParam BigDecimal priceLimit) {
        List<Product> products = productService.getProductsUnderPrice(priceLimit);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/random")
    public ResponseEntity<Product> getRandomProduct() {
        Product randomProduct = productService.getOneRandomProduct();
        if (randomProduct != null) {
            return ResponseEntity.ok(randomProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/randomBatch")
    public ResponseEntity<List<Product>> getRandomProductsBatch() {
        List<Product> randomProducts = productService.getRandomProductsBatch();
        return ResponseEntity.ok(randomProducts);
    }
}