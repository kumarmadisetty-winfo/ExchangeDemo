package com.example.exchangerates.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExchangeResponse {

	private String baseCurrency;
	private String targetCurrency;
	private float baseValue;
	private float targetValue;
	private String date;
	@Override
	public String toString() {
		return "ExchangeResponse [baseCurrency=" + baseCurrency + ", targetCurrency=" + targetCurrency + ", baseValue="
				+ baseValue + ", targetValue=" + targetValue + ", date=" + date + "]";
	}
}