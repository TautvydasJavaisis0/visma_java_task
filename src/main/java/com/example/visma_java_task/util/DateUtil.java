package com.example.visma_java_task.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static int getDurationInMonths(String startDate, String endDate) {
        Period diff = Period.between(stringToLocalDate(startDate), stringToLocalDate(endDate));
        return diff.getMonths();
    }

    public static LocalDate stringToLocalDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(dateStr, formatter);
    }

}
