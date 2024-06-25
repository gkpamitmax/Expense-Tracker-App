package com.hdfc.expense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
import com.hdfc.expense.model.Income;
import com.hdfc.expense.service.IncomeService;
import com.hdfc.expense.service.UserService;

@RestController
@RequestMapping("/income")
@CrossOrigin(origins = "*")
public class IncomeController {

	@Autowired
	private IncomeService incomeService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/all")
	public ResponseEntity<List<Income>> getAllincomesByUsername(
			@RequestHeader("Authorization") String authorizationHeader) {
		String jwtToken = authorizationHeader.substring(7);
		String username = jwtTokenUtil.extractUsername(jwtToken);
		
		List<Income> incomes = incomeService.getAllIncomesByUsername(username);
		return ResponseEntity.ok(incomes);
	}

	@PostMapping
	public ResponseEntity<Income> addincome(@RequestBody Income income,
			@RequestHeader("Authorization") String authorizationHeader) {

		String jwtToken = authorizationHeader.substring(7);
		String username = jwtTokenUtil.extractUsername(jwtToken);
		Long userId = userService.getUserByUsername(username).getUserId();
		income.setUsername(username);
		double balance = userService.updateBalance(username, income.getAmount());
		Income addedincome = incomeService.addIncome(income);
		return new ResponseEntity<>(addedincome, HttpStatus.CREATED);
	}

	@DeleteMapping("/{incomeId}")
	public ResponseEntity<Void> deleteincome(@PathVariable Long incomeId) {
		incomeService.deleteIncome(incomeId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{incomeId}")
	public ResponseEntity<Income> updateincome(@PathVariable Long incomeId, @RequestBody Income updatedincome) {
		Income income = incomeService.updateIncome(incomeId, updatedincome);
		if (income != null) {
			return ResponseEntity.ok(income);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//	@GetMapping("/getgoals")
//	public ResponseEntity<Object> getGoals(HttpServletRequest request) {
//		String authToken = request.getHeader("Authorization");
//		
//		ResponseEntity<Object> response = restTemplate.exchange(
//                "http://localhost:9100/goals/all",
//                HttpMethod.GET,
//                createHttpEntityWithAccessTokenAndBody(authToken),  // Include access token and request body in headers
//                Object.class);
////		
//		return response;
//	}
	
	private HttpEntity<String> createHttpEntityWithAccessTokenAndBody(String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);
        return new HttpEntity<>(headers);
    }
	
	

}