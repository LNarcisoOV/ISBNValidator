package com.udemy.isbn;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

import com.udemy.isbn.bean.LoanApplication;
import com.udemy.isbn.controller.LoanCalculatorController;
import com.udemy.isbn.repository.LoanRepository;

public class RepaymentAmountTest {

	@Spy
	private LoanApplication application;
	private LoanCalculatorController controller;
	private LoanRepository repository;
	private JavaMailSender mailSender;
	private RestTemplate restTemplate;

	@Before
	public void setup() {
		application = spy(new LoanApplication());
		controller = new LoanCalculatorController();
		repository = mock(LoanRepository.class);
		mailSender = mock(JavaMailSender.class);
		restTemplate = mock(RestTemplate.class);

		controller.setData(repository);
		controller.setMailSender(mailSender);
		controller.setRestTemplate(restTemplate);
	}

	@Test
	public void test1YearLoanWholePounds() {
		application.setPrincipal(1200);
		application.setTermInMonths(12);
		doReturn(new BigDecimal(10)).when(application).getInterestRate();

		controller.processNewLoanApplication(application);
		assertEquals(new BigDecimal(110), application.getRepayment());
	}

	@Test
	public void test2YearLoanWholePounds() {
		application.setPrincipal(1200);
		application.setTermInMonths(24);
		doReturn(new BigDecimal(10)).when(application).getInterestRate();

		controller.processNewLoanApplication(application);
		assertEquals(new BigDecimal(60), application.getRepayment());
	}

	@Test
	public void test5YearLoanWithRounding() {
		application.setPrincipal(5000);
		application.setTermInMonths(60);
		doReturn(new BigDecimal(6.5)).when(application).getInterestRate();

		controller.processNewLoanApplication(application);
		assertEquals(new BigDecimal(111), application.getRepayment());
	}

}
