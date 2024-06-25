package com.hdfc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hdfc.dto.User;

@Component
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	static final String USER_URL_MS="http://localhost:8087/users/";
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = restTemplate.getForObject(USER_URL_MS+"user/"+username, User.class);
		if (user == null) {
            // Handle user not found scenario
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
		return new CustomUserDetails(user);
	}
	
	
	
}
