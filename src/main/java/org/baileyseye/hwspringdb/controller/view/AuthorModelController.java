package org.baileyseye.hwspringdb.controller.view;

import org.baileyseye.hwspringdb.model.Author;
import org.baileyseye.hwspringdb.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AuthorModelController {

    private final AuthorService authorService;

    @Autowired
    public AuthorModelController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors/view")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        if (authors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(authors);
    }
}