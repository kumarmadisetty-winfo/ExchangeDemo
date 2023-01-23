package com.example.exchangerates.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings({ "rawtypes", "unchecked" })
@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice{ 	
	@ExceptionHandler(Exception.class)
	public ResponseEntity exceptionHandler(Exception ex,WebRequest request) {
		log.error("Exception while checking the preconditions Exception",ex);		
		List<String> errors = new ArrayList<>();		
		if(ex instanceof ConstraintViolationException) {			
			ConstraintViolationException cve = (ConstraintViolationException)ex;
			Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();		
			errors = new ArrayList<>(constraintViolations.size());			
			errors = constraintViolations.stream().map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.toList());			
		} else {			
			errors.add(ex.getLocalizedMessage());
		}
		ErrorResponse responseErrors = new ErrorResponse(HttpStatus.PRECONDITION_FAILED.value(),errors);
		return new ResponseEntity(responseErrors, HttpStatus.PRECONDITION_FAILED);
	}	
}