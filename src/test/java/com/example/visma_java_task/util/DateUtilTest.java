package com.example.visma_java_task.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}