package com.example.visma_java_task.book.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Valid
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    @NotNull
    private String name;
    @NotNull
    private String author;
    @NotNull
    private String category;
    @NotNull
    private String language;
    @NotNull
    private String publication;
    @NotNull
    private String date;
    @NotNull
    private String isbn;
    @NotNull
    private Long guid;
}
