package com.udemy.isbn;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class StockManagementTest {

	private ExternalISBNDataService testWebService;
	private ExternalISBNDataService testDatabaseService;
	private StockManager stockManager;

	@Before
	public void setup() {
		testWebService = mock(ExternalISBNDataService.class);
		testDatabaseService = mock(ExternalISBNDataService.class);
		stockManager = new StockManager();
		stockManager.setWebService(testWebService);
		stockManager.setDatabaseService(testDatabaseService);
	}

	@Test
	public void testCanGetACorrectLocatorCode() {
		when(testWebService.lookup(anyString()))
				.thenReturn(new Book("0140177396", "Of Mice And Men", "J. Steinbeck"));
		when(testDatabaseService.lookup(anyString())).thenReturn(null);

		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

	@Test
	public void databaseIsUsedIfDataIsPresent() {
		when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);

		verify(testDatabaseService, times(1)).lookup("0140177396");
		verify(testWebService, never()).lookup(anyString());
	}

	@Test
	public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
		when(testDatabaseService.lookup("0140177396")).thenReturn(null);
		when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);

		verify(testDatabaseService, times(1)).lookup("0140177396");
		verify(testWebService, times(1)).lookup("0140177396");
	}
}
