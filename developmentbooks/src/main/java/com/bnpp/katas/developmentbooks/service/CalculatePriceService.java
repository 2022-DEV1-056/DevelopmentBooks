package com.bnpp.katas.developmentbooks.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bnpp.katas.developmentbooks.dto.BookDto;
import com.bnpp.katas.developmentbooks.dto.BookGroup;
import com.bnpp.katas.developmentbooks.dto.PriceSummaryDto;
import com.bnpp.katas.developmentbooks.exceptions.BookNotFoundException;
import com.bnpp.katas.developmentbooks.store.DevelopmentBooksEnum;
import com.bnpp.katas.developmentbooks.store.DiscountProviderEnum;

@Service
public class CalculatePriceService {

	private static final int HUNDRED = 100;
	private static final int ZERO_PERCENT = 0;
	private static final int ONE_QUANTITY = 1;

	public PriceSummaryDto getPriceSummary(List<BookDto> listOfBooks) {
		validateBooks(listOfBooks);
		Map<Integer, Integer> bookIdQuantityMap = listOfBooks.stream()
				.collect(Collectors.toMap(BookDto::getId, BookDto::getQuantity));
		List<BookGroup> listOfBookGroup = getBookGroupswithDiscount(bookIdQuantityMap, new ArrayList<>());
		BookGroup booksWithoutDiscount = getBookGroupWithoutDiscount(bookIdQuantityMap);
		listOfBookGroup.add(booksWithoutDiscount);
		double actualPrice = listOfBookGroup.stream().mapToDouble(BookGroup::getActualPrice).sum();
		double discount = listOfBookGroup.stream().mapToDouble(BookGroup::getDiscount).sum();
		PriceSummaryDto priceSummaryDto = new PriceSummaryDto();
		priceSummaryDto.setListOfBookGroups(listOfBookGroup);
		priceSummaryDto.setActualPrice(actualPrice);
		priceSummaryDto.setTotalDiscount(discount);
		priceSummaryDto.setFinalPrice(actualPrice - discount);
		return priceSummaryDto;
	}

	private void validateBooks(List<BookDto> listOfBooks) {
		Map<Integer, Double> bookIdPriceMap = getBookIdPriceMap();
		List<Integer> missingBookIds = listOfBooks.stream().filter(book -> !bookIdPriceMap.containsKey(book.getId())).map(BookDto::getId).collect(Collectors.toList());
		if(!missingBookIds.isEmpty())
			throw new BookNotFoundException(missingBookIds);
	}

	private List<BookGroup> getBookGroupswithDiscount(Map<Integer, Integer> bookIdQuantityMap,
			List<BookGroup> bookGroup) {
		Optional<DiscountProviderEnum> discount = getDiscount(bookIdQuantityMap.size());
		if (discount.isPresent()) {
			int bookGroupSize = discount.get().getNumberOfDistinctItems();
			List<Integer> listOfDistinctBooks = bookIdQuantityMap.keySet().stream().limit(bookGroupSize)
					.collect(Collectors.toList());
			BookGroup currentBookGroup = getBookGroup(listOfDistinctBooks);
			bookGroup.add(currentBookGroup);
			cleanupDiscountedItems(bookIdQuantityMap, listOfDistinctBooks);
			getBookGroupswithDiscount(bookIdQuantityMap, bookGroup);
		}
		return bookGroup;
	}

	private BookGroup getBookGroupWithoutDiscount(Map<Integer, Integer> bookIdQuantityMap) {
		Map<Integer, Double> bookIdPriceMap = getBookIdPriceMap();
		Set<Integer> bookIds = bookIdQuantityMap.keySet();
		double actualPrice = bookIds.stream()
				.mapToDouble(bookId -> bookIdPriceMap.get(bookId) * bookIdQuantityMap.get(bookId)).sum();
		return new BookGroup(bookIds.stream().collect(Collectors.toList()), ZERO_PERCENT, actualPrice,
				BigDecimal.ZERO.doubleValue());
	}

	private Optional<DiscountProviderEnum> getDiscount(int numberOfBooks) {
		return Arrays.stream(DiscountProviderEnum.values()).sorted(Comparator.reverseOrder())
				.filter(discountGroup -> discountGroup.getNumberOfDistinctItems() <= numberOfBooks).findFirst();
	}

	private BookGroup getBookGroup(List<Integer> listOfBookToGroup) {
		Map<Integer, Double> bookIdPriceMap = getBookIdPriceMap();
		double actualPrice = listOfBookToGroup.stream().mapToDouble(bookId -> bookIdPriceMap.get(bookId) * ONE_QUANTITY)
				.sum();
		int discountPercentage = getDiscountPercentage(listOfBookToGroup.size());
		double discount = (actualPrice * discountPercentage) / HUNDRED;
		return new BookGroup(listOfBookToGroup, discountPercentage, actualPrice, discount);

	}

	private Map<Integer, Double> getBookIdPriceMap() {
		return Arrays.stream(DevelopmentBooksEnum.values())
				.collect(Collectors.toMap(DevelopmentBooksEnum::getId, DevelopmentBooksEnum::getPrice));
	}

	private void cleanupDiscountedItems(Map<Integer, Integer> itemIdQuantityMap, List<Integer> discountedItems) {
		discountedItems.forEach(itemId -> {
			int quantity = itemIdQuantityMap.get(itemId);
			if (quantity > ONE_QUANTITY) {
				itemIdQuantityMap.put(itemId, quantity - ONE_QUANTITY);
			} else {
				itemIdQuantityMap.remove(itemId);
			}
		});
	}

	private int getDiscountPercentage(int numberOfDistinctItems) {
		Optional<DiscountProviderEnum> discount = getDiscount(numberOfDistinctItems);
		return (discount.isPresent()) ? discount.get().getDiscountPercentage() : ZERO_PERCENT;
	}

}