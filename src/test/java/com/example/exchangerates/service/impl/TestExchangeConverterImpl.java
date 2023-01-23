package com.example.exchangerates.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.exchangerates.model.Exchange;
import com.example.exchangerates.model.ExchangeRates;
import com.example.exchangerates.model.ExchangeRequest;
import com.example.exchangerates.model.ExchangeResponse;
import com.example.exchangerates.service.ExchangeConverter;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestExchangeConverterImpl {
	
	@Autowired
	private ExchangeConverter exchangeConverter;
	
	ExchangeRequest exchangeRequest = null;
	
	Map<String,Exchange> listOfExchanges= null;
	List<String> errors = null;
	Set<ConstraintViolation<?>> constraintViolations = null;

	@Before
	public void setUp() throws Exception {
		
		listOfExchanges= new HashMap<>();
		Exchange exchange = new Exchange("USD", "USD", "840", "U.S. Dollar", 1.0827620909104f, "Fri, 20 Jan 2023 23:55:02 GMT", 0.92356391897612f);
		Exchange exchange1 = new Exchange("GBP", "GBP", "826", "U.K. Pound Sterling", 0.87611253732102f, "Fri, 20 Jan 2023 23:55:02 GMT", 1.1414058781282f);
		listOfExchanges.put("USD",exchange);
		listOfExchanges.put("GBP",exchange1);
		ExchangeRates.setListOfExchanges(listOfExchanges);
		exchangeRequest = new ExchangeRequest("USD","GBP",1.2f);
	}

	@Test
	public void testConvert() {
		ExchangeResponse exchangeResponse = exchangeConverter.convert(exchangeRequest);
		assertThat(exchangeResponse.getBaseCurrency()).isEqualTo("USD");
	}
	
	@Test
	public void testConvertWithException() {
		
		errors = new ArrayList<>();

		exchangeRequest = new ExchangeRequest("USD", "GBP1", 1.2f);
		ConstraintViolationException constraintViolationException = Assertions
				.assertThrows(ConstraintViolationException.class, () -> exchangeConverter.convert(exchangeRequest));
		constraintViolations = constraintViolationException.getConstraintViolations();
		errors = constraintViolations.stream().map(constraintViolation -> constraintViolation.getMessage())
				.collect(Collectors.toList());

		Assertions.assertEquals("Invalid Target Currency key", errors.get(0));
	}
	
	@Test
	public void testConvertWithBaseCurrencyException() {

		errors = new ArrayList<>();

		exchangeRequest = new ExchangeRequest("USD1", "GBP", 1.2f);
		ConstraintViolationException constraintViolationException = Assertions
				.assertThrows(ConstraintViolationException.class, () -> exchangeConverter.convert(exchangeRequest));
		constraintViolations = constraintViolationException.getConstraintViolations();
		errors = constraintViolations.stream().map(constraintViolation -> constraintViolation.getMessage())
				.collect(Collectors.toList());

		Assertions.assertEquals("Invalid Base Currency key", errors.get(0));
	}
	
	@Test
	public void testConvertWithBaseValueException() {
		
		errors = new ArrayList<>();
		
		exchangeRequest = new ExchangeRequest("USD","GBP",0);
		ConstraintViolationException constraintViolationException = Assertions.assertThrows(ConstraintViolationException.class, 
				() -> exchangeConverter.convert(exchangeRequest));
		constraintViolations = constraintViolationException.getConstraintViolations();
		errors = constraintViolations.stream().map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.toList());
		
		Assertions.assertEquals("Base Currency value should be greater than 0", errors.get(0));
	}
}
