package com.example.bookstorage.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String pseudonym;
    private LocalDate dob;
}
