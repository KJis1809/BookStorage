package com.example.bookstorage.service;

import com.example.bookstorage.controller.dto.BookFilterDto;
import com.example.bookstorage.entity.Author;
import com.example.bookstorage.entity.Book;
import com.example.bookstorage.repo.BookRepository;
import com.example.bookstorage.service.predicate.BookFilter;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepo;

    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepo, AuthorService authorService) {
        this.bookRepo = bookRepo;
        this.authorService = authorService;
    }

    public List<Book> getAll() {
        return bookRepo.findAll();
    }

    public Book getById(Long id) throws EntityNotFoundException {
        Book book = bookRepo.findById(id).orElse(null);
        if (book == null) {
            throw new EntityNotFoundException();
        }
        return book;
    }

    public Book create(Book book) {
        book.setAuthors(book.getAuthors().stream()
                .map(author -> {
                    Author authorFromDb = authorService.getByPseudonym(author.getPseudonym());
                    if (authorFromDb != null) {
                        author = authorFromDb;
                    }
                    return author;
                }).collect(Collectors.toList()));

        return bookRepo.save(book);
    }

    public void deleteById(Long id) {
        bookRepo.deleteById(id);
    }

    public Book update(Book newBook, Long id) throws EntityNotFoundException {
        Book book = bookRepo.getById(id);

        book.setName(newBook.getName());
        book.setReleaseDate(newBook.getReleaseDate());
        book.setIsbnCode(newBook.getIsbnCode());

        List<Author> authorList = new ArrayList<>();
        for (Author author : newBook.getAuthors()) {
            Author authorFromDb = authorService.getByPseudonym(author.getPseudonym());
            if (authorFromDb != null) {
                authorList.add(authorFromDb);
            } else {
                authorList.add(authorService.createAuthor(author));
            }
        }
        book.setAuthors(authorList);

        return bookRepo.save(book);
    }

    public Page<Book> getBookByFilter(BookFilterDto filterDto) {
        Predicate predicate = BookFilter.getBookFilterPredicate(filterDto);

        Pageable pageable = PageRequest.of(filterDto.getPageNumber(), filterDto.getPageSize(),
                filterDto.getDirection(), filterDto.getSortBy());

        return bookRepo.findAllByPredicate(predicate, pageable);
    }

}