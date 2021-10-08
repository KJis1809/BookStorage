package com.example.bookstorage.mapper;

import com.example.bookstorage.controller.dto.BookDto;
import com.example.bookstorage.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto bookToBookDto(Book book);

    List<BookDto> listBookToListBookDto(List<Book> bookList);

    Book bookDtoToBook(BookDto bookDto);

    List<Book> listBookDtoToListBook(List<BookDto> bookList);
}
