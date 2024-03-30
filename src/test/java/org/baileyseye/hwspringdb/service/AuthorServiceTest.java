package org.baileyseye.hwspringdb.service;

import org.baileyseye.hwspringdb.DTO.AuthorDTO;
import org.baileyseye.hwspringdb.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    public void testAddAuthor() {
        AuthorDTO authorDTO = new AuthorDTO("Test Author");
        authorDTO.setCategoryId(1);
        Author savedAuthor = authorService.addAuthor(authorDTO);
        assertThat(savedAuthor).isNotNull();
        assertThat(savedAuthor.getName()).isEqualTo("Test Author");
    }


    @Test
    public void testGetAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        assertThat(authors).isNotEmpty();
    }

    @Test
    public void testGetAuthorById() {
        Integer authorId = 1;
        assertTrue(authorService.getAuthorById(authorId).isPresent());
    }
}