package com.example.bookstorage.controller.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

@Data
public class BookFilterDto {
   private int pageNumber;
   private int pageSize;
   private String bookName;
   private LocalDate releaseDate;
   private Sort.Direction direction;
   private String sortBy;
   private String isbnCode;
}
