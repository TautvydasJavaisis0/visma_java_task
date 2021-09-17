package com.example.visma_java_task.book;

import com.example.visma_java_task.book.model.Book;
import com.example.visma_java_task.book.model.Loan;
import com.example.visma_java_task.book.model.dto.BookDto;
import com.example.visma_java_task.util.BookUtil;
import com.example.visma_java_task.util.JSONFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    public Optional<BookDto> getBook(Long GUID) {
        return BookUtil.getBookByGUID(GUID).map(BookMapper::toBookDto);
    }

    public List<BookDto> getFilteredBookList(String author, String category, String language, String ISBN, String name, String isTaken) {
        List<Book> books = JSONFileUtil.getBooksFromJson();
        return books
                .stream()
                .filter(b -> BookUtil.doesBookFitFilters(b, author, category, language, ISBN, name, isTaken))
                .map(BookMapper::toBookDto)
                .collect(Collectors.toList());
    }

    public BookDto addBook(BookDto bookDto) {
        Book newBook = BookMapper.toBook(bookDto);
        List<Book> books = JSONFileUtil.getBooksFromJson();
        books.add(newBook);
        JSONFileUtil.writeBooksToJson(books);
        return bookDto;
    }

    public void addBookLoan(Long GUID, Long patronID, String startDate, String endDate) {

        Optional<Book> bookOptional = BookUtil.getBookByGUID(GUID);
        if (!(bookOptional.isPresent()
                && BookUtil.isPatronValidForLoan(patronID)
                && BookUtil.isDateValidForLoan(startDate, endDate))) {
            return;
        }

        List<Book> bookList = JSONFileUtil.getBooksFromJson();
        for (Book book : bookList) {
            if (book.getGuid().equals(GUID)) {
                book.setLoan(Loan.builder()
                        .patronID(patronID)
                        .startDate(startDate)
                        .endDate(endDate)
                        .build());
            }
        }
        JSONFileUtil.writeBooksToJson(bookList);
    }

    public void deleteBook(Long GUID) {
        List<Book> books = JSONFileUtil.getBooksFromJson();
        Optional<Book> bookToDelete = BookUtil.getBookByGUID(GUID);
        bookToDelete.ifPresent(books::remove);
        JSONFileUtil.writeBooksToJson(books);
    }

}
