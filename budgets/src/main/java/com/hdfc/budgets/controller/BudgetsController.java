package com.hdfc.budgets.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hdfc.budgets.model.Budgets;
import com.hdfc.budgets.service.BudgetsService;
import com.hdfc.budgets.jwt.JwtTokenUtil;

@RestController
@RequestMapping("/budgets")
public class BudgetsController {

	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private BudgetsService budgetsService;
	
//	static final String USER_URL_MS="http://localhost:8080/users/";
	
	@GetMapping("/all")
	public ResponseEntity<List<Budgets>> getAllBudgetsByUsername(@RequestHeader("Authorization") String authorizationHeader) {
		String jwtToken = authorizationHeader.substring(7);
        String username = jwtTokenUtil.extractUsername(jwtToken); 
		List<Budgets> budgets = budgetsService.getAllBudgetsByUsername(username);
		return ResponseEntity.ok(budgets);
	}

	@PostMapping
	public ResponseEntity<Budgets> addBudget(@RequestBody Budgets budget,@RequestHeader("Authorization") String authorizationHeader) {
		String jwtToken = authorizationHeader.substring(7);
        String username = jwtTokenUtil.extractUsername(jwtToken);       
		budget.setUsername(username);
        Budgets addedBudget = budgetsService.addBudget(budget);
		return new ResponseEntity<>(addedBudget, HttpStatus.CREATED);
	}

	@DeleteMapping("/{budgetId}")
	public ResponseEntity<Void> deleteBudget(@PathVariable Long budgetId) {
		budgetsService.deleteBudget(budgetId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{budgetId}")
	public ResponseEntity<Budgets> updateBudget(@PathVariable Long budgetId, @RequestBody Budgets updatedBudget) {
		Budgets budget = budgetsService.updateBudget(budgetId, updatedBudget);
		if (budget != null) {
			return ResponseEntity.ok(budget);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
