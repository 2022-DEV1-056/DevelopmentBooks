package com.bnpp.katas.developmentbooks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/developmentbooks")
public class DevelopmentBooksController {

	@GetMapping("/getBooks")
	public String getBooks() {
		return "Success";
	}

}
