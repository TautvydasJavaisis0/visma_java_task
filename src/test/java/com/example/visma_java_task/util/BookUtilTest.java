package com.example.visma_java_task.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class BookUtilTest {

    @Test
    void isPatronValidForLoan() {
        assertEquals(true, BookUtil.isPatronValidForLoan(1L));
    }

    @Test
    @DisplayName("Should return true for valid date")
    void testIsDateValidForLoan() {
        String startDate = "2021-02-31";
        String endDate = "2021-03-02";
        assertEquals(true, BookUtil.isDateValidForLoan(startDate, endDate));
    }

    @Test
    @DisplayName("Should throw exception for invalid date")
    void testExceptionIsDateValidForLoan() {
        String startDate = "2021-03-31";
        String endDate = "2021-02-02";
        Throwable exception = assertThrows(ResponseStatusException.class, () -> BookUtil.isDateValidForLoan(startDate, endDate));
        assertEquals("400 BAD_REQUEST \"invalid time difference between dates\"", exception.getMessage());
    }
}