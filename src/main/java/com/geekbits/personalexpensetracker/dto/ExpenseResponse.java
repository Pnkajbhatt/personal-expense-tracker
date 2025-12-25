package com.geekbits.personalexpensetracker.dto;
import com.geekbits.personalexpensetracker.entity.Expenses;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExpenseResponse(
        Long id,
        BigDecimal amount,
        Expenses.Category category,
        String description,
        LocalDate date,
        LocalDateTime createdAt,
        UserResponse user
) {}
