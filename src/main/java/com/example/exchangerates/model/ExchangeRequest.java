package com.example.exchangerates.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class ExchangeRequest {
	
	private String baseCurrency;
	private String targetCurrency;
	private float baseValue;
}
