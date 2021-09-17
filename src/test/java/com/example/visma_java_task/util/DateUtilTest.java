package com.example.visma_java_task.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    @Test
    @DisplayName("Should return more than -1 less than 2")
    void getDurationInMonths() {
        String startDate = "2021-02-31";
        String endDate = "2021-03-02";
        assertEquals(0, DateUtil.getDurationInMonths(startDate,endDate));

    }
    @Test
    @DisplayName("Should return -1 because date of opposite date difference")
    void InvalidGetDurationInMonths() {
        String startDate = "2021-03-31";
        String endDate = "2021-02-02";
        assertNotEquals(0, DateUtil.getDurationInMonths(startDate,endDate));
        assertNotEquals(1, DateUtil.getDurationInMonths(startDate,endDate));
        assertEquals(-1, DateUtil.getDurationInMonths(startDate,endDate));
    }

    @Test
    @DisplayName("Should parse from string to localdate")
    void StringToLocalDate() {
        String date = "2021-03-31";
        LocalDate localDate = DateUtil.stringToLocalDate(date);
        assertEquals(date, localDate.toString());
    }

    @Test
    @DisplayName("Should throw exception with bad date format")
    void StringToLocalDateException() {
        String date = "2021.03.31";
        Throwable exception = assertThrows(DateTimeParseException.class, () -> DateUtil.stringToLocalDate(date));
        assertEquals("Text '2021.03.31' could not be parsed at index 4", exception.getMessage());
    }

}