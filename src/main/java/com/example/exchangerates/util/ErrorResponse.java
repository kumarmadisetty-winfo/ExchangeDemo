package com.example.exchangerates.util;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorResponse {
	private Integer code = null;
	private List<String> message = new ArrayList<>();

	public ErrorResponse(Integer code, String message) {
		this.code = code;
		this.message.add(message);
	}

	public ErrorResponse(Integer code, List<String> messages) {
		this.code = code;
		this.message= messages;
	}
}
