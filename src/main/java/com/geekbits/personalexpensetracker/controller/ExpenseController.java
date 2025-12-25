package com.geekbits.personalexpensetracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekbits.personalexpensetracker.dto.ExpenseRequest;
import com.geekbits.personalexpensetracker.dto.ExpenseResponse;
import com.geekbits.personalexpensetracker.service.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    /**
     * POST /api/expenses - Create a new expense for the current user
     */
    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@Valid @RequestBody ExpenseRequest request) {
        ExpenseResponse response = expenseService.createExpense(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * GET /api/expenses - Get all expenses for the current user
     */
    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getAllExpenses() {
        List<ExpenseResponse> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    /**
     * GET /api/expenses/{id} - Get a specific expense (verify ownership)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> getExpense(@PathVariable Long id) {
        ExpenseResponse expense = expenseService.getById(id);
        return ResponseEntity.ok(expense);
    }

    /**
     * PUT /api/expenses/{id} - Update an expense (verify ownership)
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(@PathVariable Long id,
            @Valid @RequestBody ExpenseRequest request) {
        ExpenseResponse updatedExpense = expenseService.updateExpense(id, request);
        return ResponseEntity.ok(updatedExpense);
    }

    /**
     * DELETE /api/expenses/{id} - Delete an expense (verify ownership)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}