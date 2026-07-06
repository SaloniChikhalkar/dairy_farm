package com.example.dairyfarm.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dairyfarm.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Search by Category
    List<Expense> findByCategoryContainingIgnoreCase(String category);

    // Filter by Expense Date
    List<Expense> findByExpenseDate(LocalDate expenseDate);

}
