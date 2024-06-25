package com.hdfc.budgets.service;

import java.util.List;

import org.springframework.stereotype.Service;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

import com.hdfc.budgets.model.Budgets;
import com.hdfc.budgets.repository.BudgetsRepository;

@Service
public class BudgetsService {

    private final BudgetsRepository budgetsRepository;

    public BudgetsService(BudgetsRepository budgetsRepository) {
        this.budgetsRepository = budgetsRepository;
    }

    public List<Budgets> getAllBudgetsByUsername(String username) {
        return budgetsRepository.findByUsername(username);
    }

    public List<Budgets> getAllBudgetsForAdmin() {
        return budgetsRepository.findAll();
    }

    public Budgets addBudget(Budgets budget) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userRole = authentication.getAuthorities().stream().findFirst().orElseThrow().getAuthority();
    	String userRole = "USER";
        if ("USER".equals(userRole)) {
            return budgetsRepository.save(budget);
        } else {
            throw new IllegalArgumentException("Insufficient privileges to add a budget");
        }
    }

    public void deleteBudget(Long budgetId) {
        Budgets budget = budgetsRepository.findById(budgetId)
                .orElseThrow(() -> new IllegalArgumentException("Budget not found"));
        budgetsRepository.deleteById(budgetId);
        
    }

    public Budgets updateBudget(Long budgetId, Budgets updatedBudget) {
        Budgets budget = budgetsRepository.findById(budgetId)
                .orElseThrow(() -> new IllegalArgumentException("Budget not found"));

        budget.setBudgetName(updatedBudget.getBudgetName());
        budget.setStartDate(updatedBudget.getStartDate());
        budget.setEndDate(updatedBudget.getEndDate());
        budget.setTotalBudgetAmount(updatedBudget.getTotalBudgetAmount());
        return budgetsRepository.save(budget);  
    }

}
