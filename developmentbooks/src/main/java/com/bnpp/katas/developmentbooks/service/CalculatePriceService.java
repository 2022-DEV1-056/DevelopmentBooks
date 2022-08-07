package com.bnpp.katas.developmentbooks.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bnpp.katas.developmentbooks.dto.BookDto;
import com.bnpp.katas.developmentbooks.store.DevelopmentBooksEnum;
import com.bnpp.katas.developmentbooks.store.DiscountProviderEnum;

@Service
public class CalculatePriceService {

	private static final int HUNDRED = 100;
	private static final int ZERO_PERCENT = 0;

	public Double calculatePrice(List<BookDto> listOfBooks) {
		Map<Integer, Double> bookIdPriceMap = Arrays.stream(DevelopmentBooksEnum.values())
				.collect(Collectors.toMap(DevelopmentBooksEnum::getId, DevelopmentBooksEnum::getPrice));

		long distinctBooks = listOfBooks.stream().map(BookDto::getId).distinct().count();
		double actualPrice = listOfBooks.stream()
				.mapToDouble(book -> bookIdPriceMap.get(book.getId()) * book.getQuantity()).sum();
		double discountedPrice = (actualPrice * getDiscountPercentage(distinctBooks)) / HUNDRED;

		return (actualPrice - discountedPrice);
	}

	private int getDiscountPercentage(long numberOfDistinctItems) {
		Optional<DiscountProviderEnum> discount = Arrays.stream(DiscountProviderEnum.values())
				.sorted(Comparator.reverseOrder())
				.filter(discountGroup -> discountGroup.getNumberOfDistinctItems() <= numberOfDistinctItems).findFirst();
		return (discount.isPresent()) ? discount.get().getDiscountPercentage() : ZERO_PERCENT;
	}

}