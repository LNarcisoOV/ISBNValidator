package com.udemy.isbn;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StockManagementTest {

	@Test
	public void testCanGetACorrectLocatorCode() {
		ExternalISBNDataService testService = getTestService();
		StockManager stock = new StockManager();
		stock.setService(testService);
		String isbn = "0140177396";
		String locatorCode = stock.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

	private ExternalISBNDataService getTestService() {
		{
			ExternalISBNDataService testService = new ExternalISBNDataService() {

				@Override
				public Book lookup(String isbn) {
					return new Book(isbn, "Of Mice And Men", "J. Steinbeck");
				}
			};

			return testService;
		}
	}
}
