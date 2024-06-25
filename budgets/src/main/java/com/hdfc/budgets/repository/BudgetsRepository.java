package com.hdfc.budgets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hdfc.budgets.model.Budgets;

@Repository
public interface BudgetsRepository extends JpaRepository<Budgets, Long> {
    List<Budgets> findByUsername(String username);
}