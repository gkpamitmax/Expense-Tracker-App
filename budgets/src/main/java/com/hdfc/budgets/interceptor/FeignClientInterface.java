package com.hdfc.budgets.interceptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="Authentication", url="http://localhost:8000")
@Component
public interface FeignClientInterface {

	@GetMapping("/auth/validate")
	String validateToken(@RequestParam String token);
}
