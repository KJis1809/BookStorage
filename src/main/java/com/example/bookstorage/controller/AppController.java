package com.example.bookstorage.controller;

import com.example.bookstorage.controller.dto.BookDto;
import com.example.bookstorage.controller.dto.BookFilterDto;
import com.example.bookstorage.entity.Book;
import com.example.bookstorage.mapper.BookMapper;
import com.example.bookstorage.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class AppController {

    private final BookService bookService;
    private final BookMapper bookMapper;


    @Autowired
    public AppController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping
    public BookDto createBook(@RequestBody BookDto bookDto) {
        Book bookFromRequest = bookMapper.bookDtoToBook(bookDto);
        return bookMapper.bookToBookDto(bookService.create(bookFromRequest));
    }

    @GetMapping
    public List<BookDto> readAllBooks() {
        return bookMapper.listBookToListBookDto(bookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> readBookById(@PathVariable Long id) {
        try {
            Book book = bookService.getById(id);
            return new ResponseEntity<>(bookMapper.bookToBookDto(book), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto, @PathVariable Long id) {
        try {
            Book bookFromRequest = bookMapper.bookDtoToBook(bookDto);
            Book updatedBook = bookService.update(bookFromRequest, id);
            return new ResponseEntity<>(bookMapper.bookToBookDto(updatedBook), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }


    @GetMapping("/filter")
    public Page<BookDto> getFilterBook(@RequestBody BookFilterDto filterDto) {
        Page<Book> books = bookService.getBookByFilter(filterDto);
        return books.map(bookMapper::bookToBookDto);
    }

}