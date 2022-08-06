package com.bnpp.katas.developmentbooks.store;

public enum DevelopmentBooksEnum {

	CLEAN_CODE(1, "Clean Code", "Robert Martin", 2008, 50.00,
			"https://github.com/stephane-genicot/katas/raw/master/images/Kata_DevelopmentBooks_CleanCode.png"),
	THE_CLEAN_CODER(2, "The Clean Coder", "Robert Martin", 2011, 50.00,
			"https://github.com/stephane-genicot/katas/raw/master/images/Kata_DevelopmentBooks_CleanCoder.png"),
	CLEAN_ARCHITECTURE(3, "Clean Architecture", "Robert Martin", 2017, 50.00,
			"https://github.com/stephane-genicot/katas/raw/master/images/Kata_DevelopmentBooks_CleanArchitecture.jpeg"),
	TEST_DRIVEN_DEVELOPMENT(4, "Test-Driven Development By Example", "Kent Beck", 2003, 50.00,
			"https://github.com/stephane-genicot/katas/raw/master/images/Kata_DevelopmentBooks_TDD.jpeg"),
	WORKING_WITH_LEGACY_CODE(5, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 50.00,
			"https://github.com/stephane-genicot/katas/raw/master/images/Kata_DevelopmentBooks_Refactoring.jpeg");

	private int id;
	private String title;
	private String author;
	private int year;
	private double price;
	private String imageUrl;

	DevelopmentBooksEnum(int id, String title, String author, int year, double price, String imageUrl) {
		this.setId(id);
		this.setTitle(title);
		this.setAuthor(author);
		this.setYear(year);
		this.setPrice(price);
		this.setImageUrl(imageUrl);
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	private void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	private void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	private void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	private void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}