package org.baileyseye.hwspringdb.controller.rest;

import org.baileyseye.hwspringdb.DTO.AuthorDTO;
import org.baileyseye.hwspringdb.model.Author;
import org.baileyseye.hwspringdb.model.Category;
import org.baileyseye.hwspringdb.service.AuthorService;
import org.baileyseye.hwspringdb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class    AuthorController {
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public AuthorController(AuthorService authorService, CategoryService categoryService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @PostMapping("/authors")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorDTO authorDTO) {
        Category category = categoryService.getCategoryById(authorDTO.getCategoryId());
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setCategories(category);


        Author savedAuthor = authorService.addAuthor(authorDTO);
        return ResponseEntity.ok(savedAuthor);
    }

    @DeleteMapping("/authors/{name}")
    public ResponseEntity<Void> deleteAuthorByName(@PathVariable("name") String name) {
        authorService.deleteAuthorByName(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Integer id) {
        Optional<Author> authorOptional = authorService.getAuthorById(id);
        return authorOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}

