package com.bnpp.katas.developmentbooks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnpp.katas.developmentbooks.dto.Book;

@SpringBootTest
class DevelopmentBooksServiceTest {

	private static final int NUMBER_OF_BOOKS = 5;

	@Autowired
	private DevelopmentBooksService developmentBooksService;

	@Test
	@DisplayName("Get books should return five development books")
	void getBooks_shouldReturnListOfFiveBooks() {
		List<Book> books = developmentBooksService.getBooks();

		assertEquals(NUMBER_OF_BOOKS, books.size(), "Got all development books");
	}
}
