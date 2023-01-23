package com.example.exchangerates.constraint;

import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.example.exchangerates.model.Exchange;
import com.example.exchangerates.model.ExchangeRates;
import com.example.exchangerates.model.ExchangeRequest;

@Component
public class ExchangeConstraintImpl implements ConstraintValidator<ExchangeConstraint, ExchangeRequest> {

	private ExchangeConstraint exchangeConstraint;

	@Override
	public void initialize(ExchangeConstraint exchangeConstraint) {
		this.exchangeConstraint = exchangeConstraint;
	}

	@Override
	public boolean isValid(ExchangeRequest exchangeRequest, ConstraintValidatorContext context) {

		Map<String, Exchange> listOfExchanges = ExchangeRates.getListOfExchanges();

		if (listOfExchanges.entrySet().stream()
				.filter(e -> e.getKey().equalsIgnoreCase(exchangeRequest.getBaseCurrency())).count() == 0) {

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Invalid Base Currency key").addConstraintViolation();
			return false;
		}

		if (listOfExchanges.entrySet().stream()
				.filter(e -> e.getKey().equalsIgnoreCase(exchangeRequest.getTargetCurrency())).count() == 0) {

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Invalid Target Currency key").addConstraintViolation();
			return false;
		}
		
		if(exchangeRequest.getBaseValue() <= 0) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Base Currency value should be greater than 0").addConstraintViolation();
			return false;
		}
		return true;
	}
}
