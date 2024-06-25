// For user registration, login, and management.
package com.hdfc.finance.service;

import java.util.List;

import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import com.hdfc.finance.config.CustomUserDetails;
import com.hdfc.finance.model.User;
import com.hdfc.finance.repository.UserRepository;

@SuppressWarnings("deprecation")
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	private UserDetails userDetails;

	public User registerUser(String username, String password, String email, String role) {
		// Check if the user with the given email already exists
		if (userRepository.findByEmail(email).isPresent()) {
			throw new IllegalStateException("User with the given email already exists");
		}

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setRole(role);
		return userRepository.save(user);
	}

	public User loginUser(String email, String password) {
		User user = userRepository.findByEmailAndPassword(email, password)
				.orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

		userDetails = new CustomUserDetails(user);

		return user;
	}

	private void validateUserLoggedIn() {
		if (userDetails == null) {
			throw new IllegalStateException("User not logged in");
		}
	}

	private void validateAdminRole() {
		validateUserLoggedIn();
		String getRole = ((CustomUserDetails) userDetails).getRole();
		if (!getRole.equals("admin")) {
			throw new IllegalStateException("User does not have admin privileges");
		}
	}

	public List<User> getAllUsers() {
		validateAdminRole();
		return userRepository.findAll();
	}

	public User getUserById(Long userId) {
		validateAdminRole();
		return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
	}

	public User updateUserProfile(String newUsername, String newPassword, String newEmail) {
		validateUserLoggedIn();

		Long userId = ((CustomUserDetails) userDetails).getUserId();
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

		if (StringUtils.isBlank(newUsername)) {
			throw new IllegalArgumentException("New username cannot be blank");
		}

		if (StringUtils.isBlank(newPassword) || newPassword.length() < 6) {
			throw new IllegalArgumentException("New password must be at least 6 characters");
		}
		if (!isValidEmail(newEmail) || StringUtils.isBlank(newEmail)) {
			throw new IllegalArgumentException("Invalid email format");
		}

		user.setUsername(newUsername);
		user.setPassword(newPassword);
		user.setEmail(newEmail);
		return userRepository.save(user);
	}

	private boolean isValidEmail(String email) {
		return EmailValidator.getInstance().isValid(email);
	}

	public void resetPassword() {
		validateUserLoggedIn();

		// Implement password reset logic (send reset link to the user's email)
	}

}
