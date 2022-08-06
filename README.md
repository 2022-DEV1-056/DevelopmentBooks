# DevelopmentBooks

There is a series of books about software development that have been read by a lot of developers who want to improve their development skills. Let’s say an editor, in a gesture of immense generosity to mankind (and to increase sales as well), is willing to set up a pricing model where you can get discounts when you buy these books. The available books are :

1. Clean Code (Robert Martin, 2008)
2. The Clean Coder (Robert Martin, 2011)
3. Clean Architecture (Robert Martin, 2017)
4. Test Driven Development by Example (Kent Beck, 2003)
5. Working Effectively With Legacy Code (Michael C. Feathers, 2004)


# Rules 

One copy of the five books costs 50 EUR.

- If, however, you buy two different books from the series, you get a 5% discount on those two books.
- If you buy 3 different books, you get a 10% discount.
- If you buy 4 different books, you get a 20% discount.
- If you go for the whole hog, and buy all 5, you get a huge 25% discount.
- Note that if you buy, say, 4 books, of which 3 are different titles, you get a 10% discount on the 3 that form part of a set, but the 4th book still costs 50 EUR.


# Purpose

Develop a application to **calculate the best price** of any conceivable shopping basket by applicable the above discounts using **Test Driven Development** (TDD). 

- **Backend** : Java & Springboot
- **Frontend**: ReactJS

# Functional case

If the shopping basket contains the below books.

- 2 copies of the “Clean Code” book
- 2 copies of the “Clean Coder” book
- 2 copies of the “Clean Architecture” book
- 1 copy of the “Test Driven Development by Example” book
- 1 copy of the “Working effectively with Legacy Code” book

We can avail the discounts for above shopping basket containing 8 books by grouping [5,3] or [4,4] or [3,3,2], etc. Output should be **320 EUR** as the best price by applying **[4,4]** as below. 

- (4 * 50 EUR) - 20% [first book, second book, third book, fourth book]
- (4 * 50 EUR) - 20% [first book, second book, third book, fifth book]

= (160 EUR + 160 EUR) => **320 EUR**