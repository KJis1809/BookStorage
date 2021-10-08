package com.example.bookstorage.service.predicate;

import com.example.bookstorage.controller.dto.BookFilterDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import static com.example.bookstorage.entity.QBook.book;

public final class BookFilter {

    private BookFilter() {}

    public static Predicate getBookFilterPredicate(BookFilterDto bookFilterDto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        addFindByBookName(booleanBuilder, bookFilterDto.getBookName());
        return booleanBuilder;
    }

    private static void addFindByBookName(BooleanBuilder booleanBuilder, String bookName) {
        if (bookName == null) {
            return;
        }

        String nameLike = "%" + bookName + "%";
        booleanBuilder.and(book.name.like(nameLike));
    }
}
