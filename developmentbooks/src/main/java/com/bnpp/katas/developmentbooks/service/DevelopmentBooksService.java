package com.bnpp.katas.developmentbooks.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bnpp.katas.developmentbooks.dto.Book;
import com.bnpp.katas.developmentbooks.store.DevelopmentBooksEnum;

@Service
public class DevelopmentBooksService {

	public List<Book> getBooks() {
		return Arrays
				.stream(DevelopmentBooksEnum.values()).map(bookEnum -> new Book(bookEnum.getId(), bookEnum.getTitle(),
						bookEnum.getAuthor(), bookEnum.getYear(), bookEnum.getPrice(), bookEnum.getImageUrl()))
				.collect(Collectors.toList());
	}

}
