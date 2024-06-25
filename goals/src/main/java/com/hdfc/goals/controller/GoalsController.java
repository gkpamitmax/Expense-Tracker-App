package com.hdfc.goals.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.hdfc.goals.jwt.JwtTokenUtil;
import com.hdfc.goals.model.Goals;
import com.hdfc.goals.service.GoalsService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/goals")
@CrossOrigin(origins = "*")
public class GoalsController {
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private RestTemplate restTemplate;
	

	private final GoalsService goalsService;
	
	static final String USER_URL_MS="http://localhost:8087/users/";

	public GoalsController(GoalsService goalsService) {
		this.goalsService = goalsService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Goals>> getAllGoalsByUsername(@RequestHeader("Authorization") String authorizationHeader) {
		String jwtToken = authorizationHeader.substring(7);
        String username = jwtTokenUtil.extractUsername(jwtToken); 
		List<Goals> goals = goalsService.getAllGoalsByUsername(username);
		return ResponseEntity.ok(goals);
	}

	@GetMapping("/admin")
	public ResponseEntity<List<Goals>> getAllGoalsForAdmin() {
		List<Goals> goals = goalsService.getAllGoalsForAdmin();
		return ResponseEntity.ok(goals);
	}

	@PostMapping
	public ResponseEntity<Goals> addGoal(@RequestBody Goals goal,@RequestHeader("Authorization") String authorizationHeader) {
		String jwtToken = authorizationHeader.substring(7);
        String username = jwtTokenUtil.extractUsername(jwtToken);       
        goal.setUsername(username);
        
        //set balance of user based on currentAmount entered
        ResponseEntity<Double> balance = restTemplate.exchange(
                USER_URL_MS + "update/" + username,
                HttpMethod.POST,
                createHttpEntityWithAccessTokenAndBody(authorizationHeader, -goal.getCurrentAmount()),  // Include access token and request body in headers
                Double.class);
		Goals addedGoal = goalsService.addGoal(goal);
		return new ResponseEntity<>(addedGoal, HttpStatus.CREATED);
	}

	@DeleteMapping("/{goalId}")
	public ResponseEntity<Void> deleteGoal(@PathVariable Long goalId) {
		goalsService.deleteGoal(goalId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{goalId}")
	public ResponseEntity<Goals> updateGoal(@PathVariable Long goalId, @RequestBody Goals updatedGoal,
	        HttpServletRequest request) {
	    String authToken = request.getHeader("Authorization");
	    String jwtToken = authToken.substring(7);
	    String username = jwtTokenUtil.extractUsername(jwtToken);
	    
	    //amount to be deducted from balance
	   
//		Goals previousGoal = goalsService.getGoalById(goalId);
//		Double amountToDeduct = updatedGoal.getCurrentAmount()-previousGoal.getCurrentAmount();
	
	    
	    ResponseEntity<Double> balance = restTemplate.exchange(
        USER_URL_MS + "update/" + username,
        HttpMethod.POST,
        createHttpEntityWithAccessTokenAndBody(authToken, -updatedGoal.getCurrentAmount()),  // Include access token and request body in headers
        Double.class);

	    Goals goal = goalsService.updateGoal(goalId, updatedGoal);

	    if (goal != null) {
	        return ResponseEntity.ok(goal);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	private HttpEntity<Double> createHttpEntityWithAccessTokenAndBody(String authToken, Double requestBody) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", authToken);
	    return new HttpEntity<>(requestBody, headers);
	}
	
	
	

}