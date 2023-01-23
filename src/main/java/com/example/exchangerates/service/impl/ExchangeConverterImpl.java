package com.example.exchangerates.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.exchangerates.model.Exchange;
import com.example.exchangerates.model.ExchangeRates;
import com.example.exchangerates.model.ExchangeRequest;
import com.example.exchangerates.model.ExchangeResponse;
import com.example.exchangerates.service.ExchangeConverter;
import com.example.exchangerates.util.DateUtils;


@Component
public class ExchangeConverterImpl implements ExchangeConverter {

	@Autowired
	private DateUtils dateUtils;

	@Override
	public ExchangeResponse convert(ExchangeRequest exchangeRequest) {

		Map<String, Exchange> listOfExchanges = ExchangeRates.getListOfExchanges();

		Exchange targetExchange = listOfExchanges.get(exchangeRequest.getTargetCurrency().toLowerCase());

		ExchangeResponse exchangeResponse = new ExchangeResponse(exchangeRequest.getBaseCurrency(),
				exchangeRequest.getTargetCurrency(), exchangeRequest.getBaseValue(),
				exchangeRequest.getBaseValue() * targetExchange.getRate(), dateUtils.getLocalDateTime());

		return exchangeResponse;
	}

}
