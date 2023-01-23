package com.example.exchangerates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.example" })
public class ExchangeRatesApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ExchangeRatesApplication.class, args);
	}
	
}
