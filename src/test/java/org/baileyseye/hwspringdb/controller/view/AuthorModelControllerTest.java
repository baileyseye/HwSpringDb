package org.baileyseye.hwspringdb.controller.view;

import org.baileyseye.hwspringdb.model.Author;
import org.baileyseye.hwspringdb.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorModelController.class)
public class AuthorModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Test
    void testGetAllAuthorsReturnsNotEmpty() throws Exception {
        Author author = new Author();
        author.setName("John Doe");
        given(authorService.getAllAuthors()).willReturn(List.of(author));

        mockMvc.perform(get("/authors/view")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void testGetAllAuthorsReturnsEmpty() throws Exception {
        given(authorService.getAllAuthors()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/authors/view")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}