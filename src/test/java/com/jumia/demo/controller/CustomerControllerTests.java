package com.jumia.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnCustomersList() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Yosaf Karrouch")));
	}

	@Test
	public void shouldReturnCustomersListFilteredByCounrty() throws Exception {
		this.mockMvc.perform(get("/?countrySearch=morocco&stateSearch=")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Chouf Malo")));
	}

	@Test
	public void shouldReturnCustomersListFilteredByState() throws Exception {
		this.mockMvc.perform(get("/?countrySearch=&stateSearch=valid")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Ezequiel Fenias")));
	}

	@Test
	public void shouldReturnCustomersListFilteredByCounrtyAndState() throws Exception {
		this.mockMvc.perform(get("/?countrySearch=morocco&stateSearch=valid")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Nada Sofie")));
	}

	@Test
	public void shouldReturnEmptyList() throws Exception {
		this.mockMvc.perform(get("/?countrySearch=portugal&stateSearch=")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("")));
	}
}
