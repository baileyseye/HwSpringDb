package org.baileyseye.hwspringdb.service;

import org.baileyseye.hwspringdb.DTO.AuthorDTO;
import org.baileyseye.hwspringdb.model.Author;
import org.baileyseye.hwspringdb.model.Category;
import org.baileyseye.hwspringdb.repositories.AuthorRepository;
import org.baileyseye.hwspringdb.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public Author addAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());

        Category category = categoryRepository.findById(authorDTO.getCategoryId()).orElseThrow();
        author.setCategories(category);

        return authorRepository.save(author);
    }

    public void deleteAuthorByName(String name) {
        Optional<Author> authorOptional = authorRepository.findByName(name);
        authorOptional.ifPresent(authorRepository::delete);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Integer id) {
        return authorRepository.findById(id);
    }


}
