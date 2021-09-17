package com.example.visma_java_task.book;

import com.example.visma_java_task.book.model.Book;
import com.example.visma_java_task.book.model.dto.BookDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Test
    @DisplayName("Should Create Book")
    void shouldCreateBook() {
        List<Book> bookList = new ArrayList<>();
        BookDto bookDto = new BookDto(null, "name", "name", "name", "name", "2020-01-01", "name", 1L);
        bookList.add(BookMapper.toBook(bookDto));

        assertFalse(bookList.isEmpty());
        assertEquals(1, bookList.size());
    }

    @Test
    @DisplayName("Should Not Create book When Name is Null")
    void shouldNotCreateBookWithoutName() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        BookDto bookDto = new BookDto(null, "name", "name", "name", "name", "2020-01-01", "name", 1L);
        Set<ConstraintViolation<BookDto>> constraintViolations = validator.validate(bookDto);

        assertEquals(1, constraintViolations.size());
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("Should Not Create book When GUID is Null")
    void shouldNotCreateBookWithoutGUID() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        BookDto bookDto = new BookDto("name", "name", "name", "name", "name", "2020-01-01", "name", null);
        Set<ConstraintViolation<BookDto>> constraintViolations = validator.validate(bookDto);

        assertEquals(1, constraintViolations.size());
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }
}