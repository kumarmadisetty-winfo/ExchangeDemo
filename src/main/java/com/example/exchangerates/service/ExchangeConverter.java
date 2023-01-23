package com.example.exchangerates.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.exchangerates.constraint.ExchangeConstraint;
import com.example.exchangerates.model.ExchangeRequest;
import com.example.exchangerates.model.ExchangeResponse;

@Service
@Validated
public interface ExchangeConverter {

	public ExchangeResponse convert(@ExchangeConstraint ExchangeRequest exchangeRequest);
}
