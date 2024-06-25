package com.hdfc.expense.service;

import java.util.List;

import javax.ws.rs.NotFoundException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.hdfc.expense.model.Expense;
import com.hdfc.expense.repository.ExpenseRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;

@Service
public class ExpenseService {
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	private ExpenseRepository expenseRepository;

    private HttpEntity<String> createHttpEntityWithAccessToken(String jwtToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+jwtToken);
        System.out.println(headers);
        return new HttpEntity<>(headers);
    }
	
	
	public List<Expense> getAllExpensesByUsername(String username) {
		return expenseRepository.findByUsername(username);
	}

	public Expense addExpense(Expense expense) {
		return expenseRepository.save(expense);
	}

	public void deleteExpense(Long expenseId) {
		expenseRepository.deleteById(expenseId);
	}

	public Expense updateExpense(Long expenseId, Expense updatedExpense) {
		Expense existingExpense = expenseRepository.findById(expenseId)
				.orElseThrow(() -> new NotFoundException("Expense not found with ID: " + expenseId));

		existingExpense.setDate(updatedExpense.getDate());
		existingExpense.setAmount(updatedExpense.getAmount());
		existingExpense.setCategory(updatedExpense.getCategory());
		existingExpense.setDescription(updatedExpense.getDescription());

		return expenseRepository.save(existingExpense);
	}
	
    public String hello(String jwtToken) {
    	String gatewayUrl = "http://localhost:8080";
    	String destinationUrl = gatewayUrl + "/goals/welcome";
    	 try {
			ResponseEntity<String> response = restTemplate.exchange(
			         destinationUrl,
			         HttpMethod.GET,
			         createHttpEntityWithAccessToken(jwtToken),
			         String.class);
			  String responseBody = response.getBody();
			 return responseBody;
		} catch (RestClientException e) {
			e.printStackTrace();
			return null;
		}
    }
}