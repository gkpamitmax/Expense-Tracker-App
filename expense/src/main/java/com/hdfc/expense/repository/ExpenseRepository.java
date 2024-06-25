package com.hdfc.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hdfc.expense.model.Expense;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUsername(String username);
}