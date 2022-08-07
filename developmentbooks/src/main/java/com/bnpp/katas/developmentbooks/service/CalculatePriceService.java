package com.bnpp.katas.developmentbooks.service;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bnpp.katas.developmentbooks.store.DevelopmentBooksEnum;

@Service
public class CalculatePriceService {

	public Double calculatePrice(int bookId) {
		Map<Integer, Double> bookIdPriceMap = Arrays.stream(DevelopmentBooksEnum.values())
				.collect(Collectors.toMap(DevelopmentBooksEnum::getId, DevelopmentBooksEnum::getPrice));
		return bookIdPriceMap.get(bookId);
	}

}