package com.hdfc.expense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.expense.model.User;
import com.hdfc.expense.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User registerUser(User user) {
		if(userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new IllegalStateException("User with the given email already exists");
		}
		if(userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new IllegalStateException("Username already taken");
		}
		System.out.println(user.getBalance());
		return userRepository.save(user);
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

	public User getUserById(Long userId) {
		//validateAdminRole();
		return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
	}


	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
	}

	public double updateBalance(String username, double balance) {
		User user = userRepository.findByUsername(username).orElse(null);
		user.setBalance(user.getBalance()+balance);
		double temp = user.getBalance();
		User saveduser = userRepository.save(user);
		return user.getBalance();
	
	}

	public double getBalance(String username) {
		User user = userRepository.findByUsername(username).orElse(null);
		
		return user.getBalance();
	}


}