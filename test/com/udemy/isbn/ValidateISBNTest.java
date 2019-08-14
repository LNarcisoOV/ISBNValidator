package com.udemy.isbn;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateISBNTest {

	@Test
	public void checkIsbnNumber() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN(140449116);
		assertTrue(result);
	}
	
	@Test
	public void checkInvalidIsbnNumber() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN(140449117);
		assertFalse(result);
	}

}
