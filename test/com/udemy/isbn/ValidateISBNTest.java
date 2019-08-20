package com.udemy.isbn;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateISBNTest {

	@Test
	public void check10DigitISBNNumber() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449116");
		assertTrue("CheckIsbnNumber - first value.", result);
		result = validator.checkISBN("0140177396");
		assertTrue("CheckIsbnNumber - second value.", result);
	}
	
	@Test
	public void checkAValid13DigitISBNNumber(){
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9781853260087");
		assertTrue(result);
		result = validator.checkISBN("9781853267338");
		assertTrue(result);
	}
	
	@Test
	public void check10DigitISBNNumberEndingInAnXAreValid() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("012000030X");
		assertTrue(result);
	}	 	

	@Test
	public void check10DigitInvalidIsbnNumber() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449117");
		assertFalse(result);
	}
	
	@Test
	public void check13DigitInvalidIsbnNumber() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9781853267336");
		assertFalse(result);
	}

	@Test(expected = NumberFormatException.class)
	public void check9DigitsISBNsAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.checkISBN("123456789");
	}
	
	@Test(expected = NumberFormatException.class)
	public void nonNumeric10DigitISBNsAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.checkISBN("helloworld");
	}

}
