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

	private static final int HUNDRED = 100;
	private static final int ZERO_PERCENT = 0;
	private static final int FIVE_PERCENT = 5;
	private static final int TWO_DISTINCT_BOOKS = 2;

	public Double calculatePrice(List<BookDto> listOfBooks) {
		Map<Integer, Double> bookIdPriceMap = Arrays.stream(DevelopmentBooksEnum.values())
				.collect(Collectors.toMap(DevelopmentBooksEnum::getId, DevelopmentBooksEnum::getPrice));

		long distinctBooks = listOfBooks.stream().map(BookDto::getId).distinct().count();
		int discountPercentage = (distinctBooks == TWO_DISTINCT_BOOKS) ? FIVE_PERCENT : ZERO_PERCENT;

		double actualPrice = listOfBooks.stream()
				.mapToDouble(book -> bookIdPriceMap.get(book.getId()) * book.getQuantity()).sum();
		double discountedPrice = (actualPrice * discountPercentage) / HUNDRED;

		return (actualPrice - discountedPrice);
	}

}