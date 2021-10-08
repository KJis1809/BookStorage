package com.example.bookstorage;

import com.example.bookstorage.controller.dto.BookDto;
import com.example.bookstorage.entity.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookTestData {
    public static Book book1 = new Book(
            1L,
            "name",
            LocalDate.now(),
            "1-1234-2322",
            new ArrayList<>(List.of(AuthorTestData.author2))
    );

    public static Book book2 = new Book(
            2L,
            "name2",
            LocalDate.now(),
            "1-4321-2322",
            new ArrayList<>(List.of(AuthorTestData.author))
    );

    public static List<Book> bookList = List.of(book1, book1, book1);

    public static BookDto bookDto = new BookDto(
            1L,
            "nameDto",
            LocalDate.now(),
            "2-1212-2121",
            List.of(AuthorTestData.authorDto)
    );

    public static List<BookDto> bookDtoList = List.of(bookDto);
}
