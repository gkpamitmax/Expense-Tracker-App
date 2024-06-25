package com.hdfc.expense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hdfc.expense.jwt.JwtTokenUtil;
import com.hdfc.expense.model.Expense;
import com.hdfc.expense.service.ExpenseService;
import com.hdfc.expense.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/expenses")
@CrossOrigin(origins = "*")

public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/all")
	public ResponseEntity<List<Expense>> getAllExpensesByUsername(
			@RequestHeader("Authorization") String authorizationHeader) {
		String jwtToken = authorizationHeader.substring(7);
		String username = jwtTokenUtil.extractUsername(jwtToken);
		List<Expense> expenses = expenseService.getAllExpensesByUsername(username);
		return ResponseEntity.ok(expenses);
	}

	@PostMapping
	public ResponseEntity<Expense> addExpense(@RequestBody Expense expense,
			@RequestHeader("Authorization") String authorizationHeader) {

		String jwtToken = authorizationHeader.substring(7);
		String username = jwtTokenUtil.extractUsername(jwtToken);
		Long userId = userService.getUserByUsername(username).getUserId();
		expense.setUsername(username);
		double balance = userService.updateBalance(username, -expense.getAmount());
		Expense addedExpense = expenseService.addExpense(expense);
		return new ResponseEntity<>(addedExpense, HttpStatus.CREATED);
	}

	@DeleteMapping("/{expenseId}")
	public ResponseEntity<Void> deleteExpense(@PathVariable Long expenseId) {
		expenseService.deleteExpense(expenseId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{expenseId}")
	public ResponseEntity<Expense> updateExpense(@PathVariable Long expenseId, @RequestBody Expense updatedExpense) {
		Expense expense = expenseService.updateExpense(expenseId, updatedExpense);
		if (expense != null) {
			return ResponseEntity.ok(expense);
		} else {
			return ResponseEntity.notFound().build();
		}
	}


	
	

}