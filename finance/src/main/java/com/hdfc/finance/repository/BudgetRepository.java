package com.hdfc.finance.repository;

import com.hdfc.finance.model.Budget;
import com.hdfc.finance.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BudgetRepository extends JpaRepository<Budget, Long> {
	List<Budget> findByUser(User user);
}
