package com.example.bookstorage.service;

import com.example.bookstorage.entity.Author;
import com.example.bookstorage.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepo;

    @Autowired
    public AuthorService(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    public Author getByPseudonym(String pseudonym) {
        return authorRepo.findAuthorByPseudonym(pseudonym);
    }

    public List<Author> getAll() {
        return authorRepo.findAll();
    }

    public Author getById(Long id) {
        return authorRepo.findById(id).orElse(null);
    }

    public Author createAuthor(Author author) {
        return authorRepo.save(author);
    }

    public void deleteById(Long id) {
        authorRepo.deleteById(id);
    }

}
