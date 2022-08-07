package com.bnpp.katas.developmentbooks.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bnpp.katas.developmentbooks.dto.BookDto;
import com.bnpp.katas.developmentbooks.store.DevelopmentBooksEnum;

@Service
public class CalculatePriceService {

	public Double calculatePrice(List<BookDto> listOfBooks) {
		Map<Integer, Double> bookIdPriceMap = Arrays.stream(DevelopmentBooksEnum.values())
				.collect(Collectors.toMap(DevelopmentBooksEnum::getId, DevelopmentBooksEnum::getPrice));

		return listOfBooks.stream().mapToDouble(book -> bookIdPriceMap.get(book.getId()) * book.getQuantity()).sum();
	}

}