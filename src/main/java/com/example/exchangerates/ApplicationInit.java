package com.example.exchangerates;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.example.exchangerates.model.Exchange;
import com.example.exchangerates.model.ExchangeRates;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ApplicationInit {

	@Autowired
	ResourceLoader resourceLoader;

	private static final Logger log = LoggerFactory.getLogger(ExchangeRatesApplication.class);

	@PostConstruct
	private void init() {
		log.info("InitDemoApplication initialization logic ...");
		Resource resource = resourceLoader.getResource("classpath:currency.json");
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			InputStream inputStream = resource.getInputStream();

			String exchangeData = new String(FileCopyUtils.copyToByteArray(inputStream), StandardCharsets.UTF_8);

			ExchangeRates.setListOfExchanges(objectMapper.readValue(exchangeData, new TypeReference<Map<String, Exchange>>() {
			}));

		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
