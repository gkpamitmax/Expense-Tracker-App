package com.hdfc.goals.config;
import org.springframework.context.annotation.Configuration;

import com.hdfc.goals.interceptor.FeignClientInterface;
import com.hdfc.goals.interceptor.RequestInterceptor;

@Configuration
public class InterceptorConfig {
    
	
    public RequestInterceptor requestInterceptor(FeignClientInterface feign) {
        return new RequestInterceptor(feign);
    }
}