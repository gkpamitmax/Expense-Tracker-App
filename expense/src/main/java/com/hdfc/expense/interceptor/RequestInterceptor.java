package com.hdfc.expense.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class RequestInterceptor implements HandlerInterceptor {
	

	private FeignClientInterface feign;

	public RequestInterceptor(FeignClientInterface feign) {
		this.feign = feign;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
			System.out.println(request.getRequestURI());
		
	        if (request.getRequestURI().equals("/users/register") || request.getRequestURI().contains("/users/user/") 
	        		|| request.getRequestURI().contains("/auth/validate")
	        		|| request.getRequestURI().contains("/error")) {
	            return true;
	        }

		String jwtToken = request.getHeader("Authorization").substring(7);
		String s = feign.validateToken(jwtToken);
		if("Token is valid".equals(s))
			return true;
		else
			return false;
	}
	
}
