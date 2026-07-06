package com.example.dairyfarm.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dairyfarm.entity.Expense;
import com.example.dairyfarm.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Add Expense
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    // Get All Expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Get Expense By ID
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    // Update Expense
    public Expense updateExpense(Long id, Expense expense) {

        Expense existingExpense = expenseRepository.findById(id).orElse(null);

        if (existingExpense != null) {

            existingExpense.setExpenseDate(expense.getExpenseDate());
            existingExpense.setCategory(expense.getCategory());
            existingExpense.setDescription(expense.getDescription());
            existingExpense.setAmount(expense.getAmount());

            return expenseRepository.save(existingExpense);
        }

        return null;
    }

    // Delete Expense
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    // Search by Category
    public List<Expense> searchByCategory(String category) {
        return expenseRepository.findByCategoryContainingIgnoreCase(category);
    }

    // Filter by Date
    public List<Expense> getExpenseByDate(LocalDate date) {
        return expenseRepository.findByExpenseDate(date);
    }
}
