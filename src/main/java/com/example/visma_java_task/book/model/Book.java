package com.example.visma_java_task.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String name;
    private String author;
    private String category;
    private String language;
    private String publication;
    private String date;
    private String isbn;
    private Long guid;
    private Loan loan;
}
