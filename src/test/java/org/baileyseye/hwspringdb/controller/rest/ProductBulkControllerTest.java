package org.baileyseye.hwspringdb.controller.rest;

import org.baileyseye.hwspringdb.service.ProductBulkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductBulkController.class)
public class ProductBulkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductBulkService productBulkService;

    @Test
    void addMillionProductsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/bulk/addMillionProducts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productBulkService, times(1)).addMillionRandomProducts();
    }
}