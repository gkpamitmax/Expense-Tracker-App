package com.hdfc.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.expense.jwt.JwtTokenUtil;
import com.hdfc.expense.model.User;
import com.hdfc.expense.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		System.out.println(user.getBalance());
		return userService.registerUser(user);
	}
	
	@GetMapping("/user/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}
	
	@GetMapping("/id/{username}")
	public Long getUserIdByUsername(@PathVariable String username) {
		return userService.getUserByUsername(username).getUserId();
	}
	
	@PostMapping("/update/{username}")
	public double updateBalance(@PathVariable String username,@RequestBody double balance) {
		return userService.updateBalance(username,balance);
	}
	
	@GetMapping("/balance")
	public double getBalance(@RequestHeader("Authorization") String authorizationHeader) {
		String jwtToken = authorizationHeader.substring(7);
		String username = jwtTokenUtil.extractUsername(jwtToken);
		return userService.getBalance(username);
	}
}