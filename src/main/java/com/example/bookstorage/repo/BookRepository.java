package com.example.bookstorage.repo;

import com.example.bookstorage.entity.Book;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookRepository extends JpaRepository<Book, Long>, QuerydslPredicateExecutor<Book> {

    default Page<Book> findAllByPredicate(Predicate predicate, Pageable pageable) {
        return findAll(predicate, pageable);
    }

}