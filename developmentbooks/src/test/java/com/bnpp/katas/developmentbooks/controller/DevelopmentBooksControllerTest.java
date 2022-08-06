package com.bnpp.katas.developmentbooks.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = DevelopmentBooksController.class)
class DevelopmentBooksControllerTest {

	@Autowired
	private DevelopmentBooksController developmentBooksController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("DevelopmentBooks controller bean should not be null")
	void developmentBooksController_shouldNotBeNull() {
		assertThat(developmentBooksController).isNotNull();
	}

	@Test
	@DisplayName("API getBooks should return status OK")
	void getBooks_Api_shouldReturn_StatusOK() throws Exception {
		mockMvc.perform(get("/api/developmentbooks/getBooks")).andExpect(status().isOk());
	}

}
