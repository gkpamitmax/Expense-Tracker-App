package com.hdfc.budgets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.hdfc.budgets")
@EnableFeignClients(basePackages="com.hdfc.budgets.interceptor")
public class BudgetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetsApplication.class, args);
	}

}
