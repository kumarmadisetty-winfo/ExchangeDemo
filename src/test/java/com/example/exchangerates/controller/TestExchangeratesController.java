package com.example.exchangerates.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.exchangerates.model.ExchangeRequest;
import com.example.exchangerates.model.ExchangeResponse;
import com.example.exchangerates.service.ExchangeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest({ExchangeController.class})
public class TestExchangeratesController {
	
	Logger log = LoggerFactory.getLogger(TestExchangeratesController.class);
	
	@MockBean
	private ExchangeConverter exchangeConverter;
	
	@Autowired
	private MockMvc mvc;
	
	private JacksonTester<ExchangeRequest> jsonExchangeRequest;
	
	ExchangeRequest exchangeRequest = null;
	DateTimeFormatter format = null;

	@Before
	public void setUp() throws Exception {
		JacksonTester.initFields(this, new ObjectMapper());
		exchangeRequest = new ExchangeRequest("usd","inr",1.2f);
		
        format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	}

	@Test
	public void testGetExchangeRates() throws Exception{
		
		LocalDateTime now = LocalDateTime.now();  
		ExchangeResponse exchangeResponse = new ExchangeResponse("usd","inr",1.2f,20.44f,now.format(format));
		given(exchangeConverter.convert(exchangeRequest)).willReturn(exchangeResponse);
		
		MockHttpServletResponse response = mvc.perform(post("/exchange")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonExchangeRequest.write(exchangeRequest).getJson()))
				.andDo(print())
				.andReturn()
				.getResponse();
		//then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

}
