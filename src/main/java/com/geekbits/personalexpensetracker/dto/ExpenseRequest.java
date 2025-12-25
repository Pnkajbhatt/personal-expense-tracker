package com.geekbits.personalexpensetracker.dto;

import com.geekbits.personalexpensetracker.entity.Expenses;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequest(@NotNull(message = "amount is required") BigDecimal amount,
                @NotNull(message = "category is required") Expenses.Category category,
                @NotBlank(message = "description is required") String description,
                @NotNull(message = "date is required") LocalDate date) {
}
