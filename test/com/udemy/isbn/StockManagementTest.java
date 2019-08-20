package com.udemy.isbn;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StockManagementTest {

	@Test
	public void testCanGetACorrectLocatorCode() {
		ExternalISBNDataService testWebService = getTestWebService();
		ExternalISBNDataService testDatabaseService = getTestDatabaseService();
		StockManager stock = new StockManager();
		
		stock.setWebService(testWebService);
		stock.setDatabaseService(testDatabaseService);
		
		String isbn = "0140177396";
		String locatorCode = stock.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

	private ExternalISBNDataService getTestWebService() {
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
	
	private ExternalISBNDataService getTestDatabaseService() {
		{
			ExternalISBNDataService testService = new ExternalISBNDataService() {

				@Override
				public Book lookup(String isbn) {
					return null;
				}
			};

			return testService;
		}
	}
}
