// For expense tracking and categorization.
package com.hdfc.finance.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hdfc.finance.model.Expense;
import com.hdfc.finance.model.User;
import com.hdfc.finance.repository.ExpenseRepository;

@Service
public class ExpenseTrackingService {

	@Autowired
	private ExpenseRepository expenseRepository;

	public Expense trackExpense(User user, LocalDate date, double amount, String category, String description) {

		Expense expense = new Expense();
		expense.setUser(user);
		expense.setDate(date);
		expense.setAmount(amount);
		expense.setCategory(category);
		expense.setDescription(description);

		return expenseRepository.save(expense);
	}

	public List<Expense> getExpensesByUser(User user) {
		return expenseRepository.findByUser(user);
	}

	public List<Expense> getExpensesByCategory(User user, String category) {
		return expenseRepository.findByUserAndCategory(user, category);
	}

	public Expense updateExpense(Long expenseId, LocalDate date, double amount, String category, String description) {
		Optional<Expense> optionalExpense = expenseRepository.findById(expenseId);
		if (optionalExpense.isPresent()) {
			Expense expense = optionalExpense.get();
			expense.setDate(date);
			expense.setAmount(amount);
			expense.setCategory(category);
			expense.setDescription(description);
			return expenseRepository.save(expense);
		} else {
			throw new IllegalArgumentException("Expense not found");
		}
	}

	public void deleteExpense(Long expenseId) {
		expenseRepository.deleteById(expenseId);
	}

	public double getTotalExpenses(User user) {
		List<Expense> userExpenses = expenseRepository.findByUser(user);
		return userExpenses.stream().mapToDouble(Expense::getAmount).sum();
	}

	public double getCategoryTotalExpenses(User user, String category) {
		List<Expense> categoryExpenses = expenseRepository.findByUserAndCategory(user, category);
		return categoryExpenses.stream().mapToDouble(Expense::getAmount).sum();
	}

	public List<Expense> getAllExpensesForAdmin(User user) {
		validateAdminRole(user);
		return expenseRepository.findAll();
	}

	private void validateAdminRole(User user) {
		String userRole = user.getRole();
		if (!userRole.equals("admin")) {
			throw new IllegalStateException("User does not have admin privileges");
		}
	}

}
