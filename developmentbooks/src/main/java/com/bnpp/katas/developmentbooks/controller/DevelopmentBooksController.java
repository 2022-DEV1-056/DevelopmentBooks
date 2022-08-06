package com.bnpp.katas.developmentbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnpp.katas.developmentbooks.dto.Book;
import com.bnpp.katas.developmentbooks.service.DevelopmentBooksService;

@RestController
@RequestMapping("${developmentbooks.controller.path}")
public class DevelopmentBooksController {

	@Autowired
	private DevelopmentBooksService developmentBooksService;

	@GetMapping("${developmentbooks.endpoints.getbooks}")
	public List<Book> getBooks() {
		return developmentBooksService.getBooks();
	}

}
