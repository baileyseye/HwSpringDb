package org.baileyseye.hwspringdb.controller.rest;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.baileyseye.hwspringdb.DTO.AuthorDTO;
import org.baileyseye.hwspringdb.model.Author;
import org.baileyseye.hwspringdb.model.Category;
import org.baileyseye.hwspringdb.service.AuthorService;
import org.baileyseye.hwspringdb.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class AuthorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthorService authorService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private AuthorController authorController;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(authorController).build();
    }

    @Test
    void addAuthor_Success() throws Exception {
        AuthorDTO authorDTO = new AuthorDTO("Author Name");
        authorDTO.setCategoryId(1);

        Category category = new Category();
        category.setId(1);

        Author author = new Author();
        author.setName("Author Name");
        author.setCategories(category);

        given(categoryService.getCategoryById(anyInt())).willReturn(category);
        given(authorService.addAuthor(any(AuthorDTO.class))).willReturn(author);

        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(author.getName()));
    }

    @Test
    void deleteAuthorByName_Success() throws Exception {
        doNothing().when(authorService).deleteAuthorByName(anyString());

        mockMvc.perform(delete("/authors/{name}", "Author Name"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAllAuthors_Success() throws Exception {
        Author author = new Author();
        author.setName("Author Name");

        given(authorService.getAllAuthors()).willReturn(List.of(author));

        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Author Name"));
    }

    @Test
    void getAuthorById_Success() throws Exception {
        Author author = new Author();
        author.setId(1);
        author.setName("Author Name");

        given(authorService.getAuthorById(1)).willReturn(Optional.of(author));

        mockMvc.perform(get("/authors/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Author Name"));
    }
}