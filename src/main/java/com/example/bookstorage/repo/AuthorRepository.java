package com.example.bookstorage.repo;

import com.example.bookstorage.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorByPseudonym(String pseudonym);
}