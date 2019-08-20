package com.udemy.isbn;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

	@Test
	public void databaseIsUsedIfDataIsPresent() {
		ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
		ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

		when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

		StockManager stock = new StockManager();
		stock.setWebService(webService);
		stock.setDatabaseService(databaseService);

		String isbn = "0140177396";
		String locatorCode = stock.getLocatorCode(isbn);

		verify(databaseService, times(1)).lookup("0140177396");
		verify(webService, never()).lookup(anyString());
	}

	@Test
	public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
		ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
		ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

		when(databaseService.lookup("0140177396")).thenReturn(null);
		when(webService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

		StockManager stock = new StockManager();
		stock.setWebService(webService);
		stock.setDatabaseService(databaseService);

		String isbn = "0140177396";
		String locatorCode = stock.getLocatorCode(isbn);

		verify(databaseService, times(1)).lookup("0140177396");
		verify(webService, times(1)).lookup("0140177396");
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
