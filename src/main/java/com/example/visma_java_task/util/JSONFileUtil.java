package com.example.visma_java_task.util;

import com.example.visma_java_task.book.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class JSONFileUtil {
    private static final String FILENAME = "data.json";

    public static void writeBooksToJson(List<Book> books) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(FILENAME), books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> getBooksFromJson() {
        List<Book> books = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(new File(FILENAME));
            TypeReference<List<Book>> typeReference = new TypeReference<List<Book>>() {
            };
            books = mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }
}
