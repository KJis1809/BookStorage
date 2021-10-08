package com.example.bookstorage.config;

import com.example.bookstorage.repo.AuthorRepository;
import com.example.bookstorage.repo.BookRepository;
import com.example.bookstorage.service.AuthorService;
import com.example.bookstorage.service.BookService;
import com.example.bookstorage.service.predicate.BookFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class TestBase {
    @MockBean
    public BookRepository bookRepositoryMock;

    @MockBean
    public AuthorRepository authorRepositoryMock;

    @Autowired
    public BookService bookService;

    @Autowired
    public AuthorService authorService;
}
