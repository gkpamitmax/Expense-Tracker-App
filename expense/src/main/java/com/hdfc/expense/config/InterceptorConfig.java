package com.hdfc.expense.config;

import org.springframework.context.annotation.Configuration;

import com.hdfc.expense.interceptor.FeignClientInterface;
import com.hdfc.expense.interceptor.RequestInterceptor;

@Configuration
public class InterceptorConfig {

    public RequestInterceptor requestInterceptor(FeignClientInterface feign) {
        return new RequestInterceptor(feign);
    }
}
