package com.hdfc.budgets.config;

import org.springframework.context.annotation.Configuration;

import com.hdfc.budgets.interceptor.FeignClientInterface;
import com.hdfc.budgets.interceptor.RequestInterceptor;

@Configuration
public class InterceptorConfig {
    
	
    public RequestInterceptor requestInterceptor(FeignClientInterface feign) {
        return new RequestInterceptor(feign);
    }
}
