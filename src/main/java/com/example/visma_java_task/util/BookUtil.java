package com.example.visma_java_task.util;

import com.example.visma_java_task.book.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class BookUtil {

    private static final int MAX_MONTHS_FOR_LOAN = 2;
    private static final int MAX_PATRON_LOANS = 3;

    public static Optional<Book> getBookByGUID(Long GUID) {

        List<Book> books = JSONFileUtil.getBooksFromJson();
        for (Book book : books) {
            if (book.getGuid().equals(GUID))
                return Optional.of(book);
        }
        return Optional.empty();
    }

    public static boolean doesBookFitFilters(Book book, String author, String category, String language, String ISBN, String name, String isTaken) {

        return (author == null || book.getAuthor().contains(author)) &&
                (category == null || book.getCategory().contains(category)) &&
                (language == null || book.getLanguage().contains(language)) &&
                (ISBN == null || book.getIsbn().contains(ISBN)) &&
                (name == null || book.getName().contains(name)) &&
                (isTaken == null || (isTaken.equals("false") && book.getLoan() == null) || (isTaken.equals("true") && book.getLoan() != null));
    }

    public static boolean isPatronValidForLoan(Long patronID) {

        List<Book> books = JSONFileUtil.getBooksFromJson();
        int count = 0;
        for (Book book : books) {
            if (book.getLoan() != null) {
                if (book.getLoan().getPatronID().equals(patronID)) {
                    count++;
                }
                if (count >= MAX_PATRON_LOANS) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "patron already has max number of loaned books");
                }
            }
        }
        return true;
    }

    public static boolean isDateValidForLoan(String startDate, String EndDate) {

        int months = DateUtil.getDurationInMonths(startDate, EndDate);
        if (months < MAX_MONTHS_FOR_LOAN && months > -1) {
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid time difference between dates");
        }
    }
}
