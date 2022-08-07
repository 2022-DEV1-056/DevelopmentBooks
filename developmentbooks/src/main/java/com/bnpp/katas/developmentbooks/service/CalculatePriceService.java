package com.bnpp.katas.developmentbooks.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bnpp.katas.developmentbooks.dto.BookDto;
import com.bnpp.katas.developmentbooks.store.DevelopmentBooksEnum;
import com.bnpp.katas.developmentbooks.store.DiscountProviderEnum;

@Service
public class CalculatePriceService {

	private static final int HUNDRED = 100;
	private static final int ZERO_PERCENT = 0;
	private static final int ONE_QUANTITY = 1;

	public Double calculatePrice(List<BookDto> listOfBooks) {
		Map<Integer, Double> bookIdPriceMap = Arrays.stream(DevelopmentBooksEnum.values())
				.collect(Collectors.toMap(DevelopmentBooksEnum::getId, DevelopmentBooksEnum::getPrice));

		Map<Integer, Integer> itemIdQuantityMap = listOfBooks.stream()
				.collect(Collectors.toMap(BookDto::getId, BookDto::getQuantity));
		int discountGroup = itemIdQuantityMap.size();
		List<Integer> groupItems = itemIdQuantityMap.keySet().stream().limit(discountGroup)
				.collect(Collectors.toList());
		double actualPriceforDiscountedItem = groupItems.stream()
				.mapToDouble(bookId -> bookIdPriceMap.get(bookId) * ONE_QUANTITY).sum();
		double discountedPrice = (actualPriceforDiscountedItem * getDiscountPercentage(discountGroup)) / HUNDRED;
		groupItems.forEach(itemId -> {
			int quantity = itemIdQuantityMap.get(itemId);
			if (quantity > ONE_QUANTITY) {
				itemIdQuantityMap.put(itemId, quantity - ONE_QUANTITY);
			} else {
				itemIdQuantityMap.remove(itemId);
			}
		});
		double priceForDiscountedItems = actualPriceforDiscountedItem - discountedPrice;
		Set<Integer> remainingItems = itemIdQuantityMap.keySet();
		double priceForRemainingItems = remainingItems.stream()
				.mapToDouble(id -> itemIdQuantityMap.get(id) * bookIdPriceMap.get(id)).sum();

		return (priceForDiscountedItems + priceForRemainingItems);
	}

	private int getDiscountPercentage(long numberOfDistinctItems) {
		Optional<DiscountProviderEnum> discount = Arrays.stream(DiscountProviderEnum.values())
				.sorted(Comparator.reverseOrder())
				.filter(discountGroup -> discountGroup.getNumberOfDistinctItems() <= numberOfDistinctItems).findFirst();
		return (discount.isPresent()) ? discount.get().getDiscountPercentage() : ZERO_PERCENT;
	}

}