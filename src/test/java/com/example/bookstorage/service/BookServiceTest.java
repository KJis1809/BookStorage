package com.example.bookstorage.service;

import com.example.bookstorage.BookTestData;
import com.example.bookstorage.config.TestBase;
import com.example.bookstorage.config.TestConfig;
import com.example.bookstorage.controller.dto.BookFilterDto;
import com.example.bookstorage.entity.Author;
import com.example.bookstorage.entity.Book;
import com.example.bookstorage.service.predicate.BookFilter;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TestConfig.class)
@RunWith(SpringRunner.class)
class BookServiceTest extends TestBase {

    @Test
    void getAll() {
        List<Book> bookList = BookTestData.bookList;
        when(bookRepositoryMock.findAll()).thenReturn(bookList);
        List<Book> booksFromService = bookService.getAll();
        assertEquals(bookList, booksFromService);
    }

    @Test
    void getById() {
        Book book = BookTestData.book1;
        when(bookRepositoryMock.findById(book.getId())).thenReturn(Optional.of(book));
        Book receivedBook = bookService.getById(book.getId());
        assertEquals(book, receivedBook);
    }

    @Test
    void create() {
        Book bookFromRequest = BookTestData.book2;

        when(authorRepositoryMock.findAuthorByPseudonym(any())).thenReturn(null);
        when(bookRepositoryMock.save(any())).thenAnswer(i -> i.getArguments()[0]);

        Book bookFromRepo = bookService.create(bookFromRequest);

        assertEquals(bookFromRepo.getName(), bookFromRequest.getName());
        assertEquals(bookFromRepo.getReleaseDate(), bookFromRequest.getReleaseDate());
        assertEquals(bookFromRepo.getIsbnCode(), bookFromRequest.getIsbnCode());

        List<Author> authorList = bookFromRequest.getAuthors();
        authorList.addAll(bookFromRequest.getAuthors());
        assertEquals(bookFromRepo.getAuthors(), authorList);
    }

    @Test
    void deleteById() {
        Book book = BookTestData.book1;
        bookService.deleteById(book.getId());
        verify(bookRepositoryMock, times(1)).deleteById(book.getId());
    }

    @Test
    void update() {
        Book book = BookTestData.book1;
        Book bookFromRequest = BookTestData.book2;

        when(bookRepositoryMock.getById(book.getId())).thenReturn(book);
        when(authorRepositoryMock.findAuthorByPseudonym(any())).thenReturn(null);
        when(bookRepositoryMock.save(any())).thenAnswer(i -> i.getArguments()[0]);

        Book bookFromRepo = bookService.update(bookFromRequest, book.getId());

        assertEquals(bookFromRepo.getId(), book.getId());
        assertEquals(bookFromRepo.getName(), bookFromRequest.getName());
        assertEquals(bookFromRepo.getReleaseDate(), bookFromRequest.getReleaseDate());
        assertEquals(bookFromRepo.getIsbnCode(), bookFromRequest.getIsbnCode());

        List<Author> authorList = book.getAuthors();
        authorList.addAll(bookFromRequest.getAuthors());
        assertEquals(bookFromRepo.getAuthors(), authorList);
    }

    @Test
    void getBookByFilter() {
        List<Book> bookList = BookTestData.bookList;
        Page<Book> bookPage = new PageImpl<>(bookList);
        try  {
            MockedStatic<BookFilter> bookFilterMockedStatic = Mockito.mockStatic(BookFilter.class);
            bookFilterMockedStatic.when(() -> BookFilter.getBookFilterPredicate(any()))
                    .thenReturn(new BooleanBuilder());

            BookFilterDto bookFilterDto = new BookFilterDto();
            bookFilterDto.setPageNumber(0);
            bookFilterDto.setPageSize(10);
            bookFilterDto.setDirection(Sort.Direction.ASC);
            bookFilterDto.setSortBy("");

            when(bookRepositoryMock.findAllByPredicate(any(), any())).thenReturn(bookPage);
            Page<Book> pageFromRequest = bookService.getBookByFilter(bookFilterDto);
            assertEquals(pageFromRequest, bookPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}