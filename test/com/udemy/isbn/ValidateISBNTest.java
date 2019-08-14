package com.udemy.isbn;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateISBNTest {

	@Test
	public void checkIsbnNumber() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449116");
		assertTrue("CheckIsbnNumber - first value.", result);
		result = validator.checkISBN("0140177396");
		assertTrue("CheckIsbnNumber - second value.", result);
	}
	
	@Test
	public void checkInvalidIsbnNumber() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449117");
		assertFalse(result);
	}

}
