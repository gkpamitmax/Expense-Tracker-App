// Managing budgets for different categories and periods.
package com.hdfc.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.finance.model.Budget;
import com.hdfc.finance.model.User;
import com.hdfc.finance.repository.BudgetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

	@Autowired
	private BudgetRepository budgetRepository;

	public List<Budget> getBudgetsByUser(User user) {
		return budgetRepository.findByUser(user);
	}

	public Budget createBudget(User user, String budgetName, LocalDate startDate, LocalDate endDate,
			double totalBudgetAmount) {
		if (startDate.isAfter(endDate)) {
			throw new IllegalArgumentException("Start date must be before or on the end date");
		}

		Budget budget = new Budget();
		budget.setUser(user);
		budget.setBudgetName(budgetName);
		budget.setStartDate(startDate);
		budget.setEndDate(endDate);
		budget.setTotalBudgetAmount(totalBudgetAmount);

		return budgetRepository.save(budget);
	}

	public Budget updateBudget(Long budgetId, String budgetName, LocalDate startDate, LocalDate endDate,
			double totalBudgetAmount) {
		Optional<Budget> optionalBudget = budgetRepository.findById(budgetId);
		if (optionalBudget.isPresent()) {
			Budget budget = optionalBudget.get();
			if (startDate.isAfter(endDate)) {
				throw new IllegalArgumentException("Start date must be before or on the end date");
			}

			budget.setBudgetName(budgetName);
			budget.setStartDate(startDate);
			budget.setEndDate(endDate);
			budget.setTotalBudgetAmount(totalBudgetAmount);

			return budgetRepository.save(budget);
		} else {
			throw new IllegalArgumentException("Budget not found");
		}
	}

	public void deleteBudget(Long budgetId) {
		budgetRepository.deleteById(budgetId);
	}

	public double getBudgetSummary(User user) {
		List<Budget> userBudgets = budgetRepository.findByUser(user);
		double totalBudgetedAmount = 0.0;
		for (Budget budget : userBudgets) {
			totalBudgetedAmount += budget.getTotalBudgetAmount();
		}
		return totalBudgetedAmount;
	}

	public List<Budget> getAllBudgetsForAdmin(User user) {
		validateAdminRole(user);
		return budgetRepository.findAll();
	}

	private void validateAdminRole(User user) {
		String userRole = user.getRole();
		if (!userRole.equals("admin")) {
			throw new IllegalStateException("User does not have admin privileges");
		}
	}

}
