package com.example.bookstorage;

import com.example.bookstorage.controller.dto.AuthorDto;
import com.example.bookstorage.entity.Author;

import java.time.LocalDate;
import java.util.Collections;

public class AuthorTestData {

    static Author author = new Author(1L,
            "firstname1",
            "surname1",
            "pseudonym1",
            LocalDate.now(),
            Collections.emptyList());

    static Author author2 = new Author(2L,
            "firstname2",
            "surname2",
            "pseudonym2",
            LocalDate.now(),
            Collections.emptyList());

    static AuthorDto authorDto = new AuthorDto(1L,
            "firstnameDto",
            "lastnameDto",
            "pseudonymDto",
            LocalDate.now());
}
