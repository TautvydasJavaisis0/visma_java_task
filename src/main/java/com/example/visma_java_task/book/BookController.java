package com.example.visma_java_task.book;

import com.example.visma_java_task.book.model.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping(path = "/{GUID}")
    public BookDto getBook(@PathVariable Long GUID) {
        return bookService.getBook(GUID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found by GUID " + GUID));
    }

    @GetMapping(path = "/filter-by")
    public List<BookDto> getBook(@RequestParam(required = false) String author,
                                 @RequestParam(required = false) String category,
                                 @RequestParam(required = false) String language,
                                 @RequestParam(required = false) String isbn,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String isTaken) {
        return bookService.getFilteredBookList(author, category, language, isbn, name, isTaken);
    }

    @PostMapping
    public BookDto addBook(@RequestBody @Valid BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @PutMapping(path = "/{GUID}/loan")
    public void loanBook(@PathVariable Long GUID,
                         @RequestParam Long patronID,
                         @RequestParam String startDate,
                         @RequestParam String endDate) {
        bookService.addBookLoan(GUID, patronID, startDate, endDate);
    }

    @DeleteMapping(path = "/{GUID}")
    public void deleteBook(@PathVariable Long GUID) {
        bookService.deleteBook(GUID);
    }
}
