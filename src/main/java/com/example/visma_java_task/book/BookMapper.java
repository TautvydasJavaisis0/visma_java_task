package com.example.visma_java_task.book;

import com.example.visma_java_task.book.model.Book;
import com.example.visma_java_task.book.model.dto.BookDto;

public class BookMapper {
    private BookMapper() {
    }

    public static BookDto toBookDto(Book book) {
        return BookDto.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .category(book.getCategory())
                .language(book.getLanguage())
                .publication(book.getPublication())
                .date(book.getDate())
                .isbn(book.getIsbn())
                .guid(book.getGuid())
                .build();
    }

    public static Book toBook(BookDto bookDto) {
        return Book.builder()
                .name(bookDto.getName())
                .author(bookDto.getAuthor())
                .category(bookDto.getCategory())
                .language(bookDto.getLanguage())
                .publication(bookDto.getPublication())
                .date(bookDto.getDate())
                .isbn(bookDto.getIsbn())
                .guid(bookDto.getGuid())
                .build();
    }
}
