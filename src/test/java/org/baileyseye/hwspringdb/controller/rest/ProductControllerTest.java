package org.baileyseye.hwspringdb.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.baileyseye.hwspringdb.DTO.ProductDTO;
import org.baileyseye.hwspringdb.model.Category;
import org.baileyseye.hwspringdb.model.Product;
import org.baileyseye.hwspringdb.service.CategoryService;
import org.baileyseye.hwspringdb.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ProductService productService;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addProductTest() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName("Test Product");
        productDTO.setProductPrice(new BigDecimal("29.99"));
        productDTO.setCategoryId(1);

        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());

        Category category = new Category();
        category.setId(productDTO.getCategoryId());
        product.setCategories(category);

        Mockito.when(categoryService.getCategoryById(productDTO.getCategoryId())).thenReturn(category);
        Mockito.when(productService.addProduct(Mockito.any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk());
    }
}