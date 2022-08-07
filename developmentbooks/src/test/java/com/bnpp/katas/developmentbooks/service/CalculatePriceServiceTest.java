package com.bnpp.katas.developmentbooks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculatePriceServiceTest {

	private static final int BOOK_ID = 1;
	private static final double BOOK_PRICE = 50.00;

	@Autowired
	private CalculatePriceService calculatePriceService;

	@Test
	@DisplayName("calculate price for a book should return 50")
	void calculatePriceForABook_shouldReturnFifty() {
		Double actualPrice = calculatePriceService.calculatePrice(BOOK_ID);
		
		assertEquals(BOOK_PRICE, actualPrice);
	}
}