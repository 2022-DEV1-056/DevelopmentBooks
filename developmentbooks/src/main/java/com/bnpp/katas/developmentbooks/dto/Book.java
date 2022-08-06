package com.bnpp.katas.developmentbooks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book {
	private int id;
	private String title;
	private String author;
	private int year;
	private double price;
	private String url;
}
