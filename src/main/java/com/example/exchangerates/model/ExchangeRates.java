package com.example.exchangerates.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ExchangeRates {
	
	public static Map<String,Exchange> listOfExchanges= new HashMap<>();

	public static Map<String, Exchange> getListOfExchanges() {
		return listOfExchanges;
	}

	public static void setListOfExchanges(Map<String, Exchange> listOfExchanges) {
		ExchangeRates.listOfExchanges = listOfExchanges;
	}
}