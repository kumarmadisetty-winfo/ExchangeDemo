package com.example.exchangerates.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchangerates.model.ExchangeRequest;
import com.example.exchangerates.model.ExchangeResponse;
import com.example.exchangerates.service.ExchangeConverter;

@RestController
public class ExchangeController {
	
	@Autowired
	private ExchangeConverter exchangeConverter;
	
	@PostMapping("/exchange")
	public ResponseEntity<ExchangeResponse> getExchangeRates(@Valid @RequestBody ExchangeRequest exchangeRequest) {
		
		ExchangeResponse exchangeResponse = exchangeConverter.convert(exchangeRequest);
		
		return ResponseEntity.status(HttpStatus.OK).body(exchangeResponse);
	}
}
