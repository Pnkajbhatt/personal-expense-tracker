package com.geekbits.personalexpensetracker.repository;

import com.geekbits.personalexpensetracker.entity.Expenses;
import com.geekbits.personalexpensetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ExpenseRepository extends JpaRepository<Expenses,Long> {
    List<Expenses> findAllByUser(User user);
}
