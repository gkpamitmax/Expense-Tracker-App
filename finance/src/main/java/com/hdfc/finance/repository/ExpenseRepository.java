package com.hdfc.finance.repository;

import com.hdfc.finance.model.Expense;
import com.hdfc.finance.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	List<Expense> findByUser(User user);
	List<Expense> findByUserAndCategory(User user, String category);
	
}