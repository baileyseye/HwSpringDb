package org.baileyseye.hwspringdb.controller.rest;

import org.baileyseye.hwspringdb.service.ProductBulkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bulk")
public class ProductBulkController {

    private final ProductBulkService productBulkService;

    @Autowired
    public ProductBulkController(ProductBulkService productBulkService) {
        this.productBulkService = productBulkService;
    }

    @PostMapping("/addMillionProducts")
    public void addMillionProducts() {
        productBulkService.addMillionRandomProducts();
    }
}