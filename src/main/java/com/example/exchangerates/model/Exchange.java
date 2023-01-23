package com.example.exchangerates.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class Exchange {

	private String code;
	private String alphaCode;
	private String numericCode;
	private String name;
	private float rate;
	private String date;
	private float inverseRate;
}
