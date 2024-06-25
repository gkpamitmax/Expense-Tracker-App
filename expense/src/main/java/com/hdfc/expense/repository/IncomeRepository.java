package com.hdfc.expense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdfc.expense.model.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUsername(String username);
}
