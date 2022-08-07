package com.bnpp.katas.developmentbooks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnpp.katas.developmentbooks.dto.BookDto;

@SpringBootTest
class CalculatePriceServiceTest {

	private static final int ONE = 1;
	private static final double BOOK_PRICE = 50.00;

	@Autowired
	private CalculatePriceService calculatePriceService;

	@Test
	@DisplayName("calculate price for a book should return 50")
	void calculatePriceForABook_shouldReturnFifty() {
		BookDto bookDto = new BookDto(ONE, ONE);

		Double actualPrice = calculatePriceService.calculatePrice(bookDto);

		assertEquals(BOOK_PRICE, actualPrice);
	}

	@ParameterizedTest
	@CsvSource({ "1,50", "2,100", "3,150", "4,200", "10,500" })
	@DisplayName("calculate price should return price based on quantities")
	void calculatePrice_shouldReturnPriceBasedOnQuantity(int quantities, double expectedPrice) {
		BookDto bookDto = new BookDto(ONE, quantities);

		Double actualPrice = calculatePriceService.calculatePrice(bookDto);

		assertEquals(expectedPrice, actualPrice);
	}
}