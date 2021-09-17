package com.example.visma_java_task.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    Long patronID;
    String startDate;
    String endDate;
}
