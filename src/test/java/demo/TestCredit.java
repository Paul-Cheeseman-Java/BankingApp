package demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class TestCredit {

	@Test
	void testincreaseCreditLimitFromZero() {
		Credit testFunds = new Credit ("testAccount");
		testFunds.increaseCreditLimit(20.0);
		assertEquals(testFunds.getCreditLimit(), 20.0);
	}

	@Test
	void testincreaseCreditLimitFromNonZero() {
		Credit testFunds = new Credit ("testAccount", 10.0);
		testFunds.increaseCreditLimit(20.0);
		assertEquals(testFunds.getCreditLimit(), 30.0);
	}

	
	@Test
	void testincreaseCreditLimitFromZeroAvailableFunds() {
		Credit testFunds = new Credit ("testAccount");
		testFunds.increaseCreditLimit(20.0);
		assertEquals(testFunds.getAvailableFunds(), 20.0);
	}

	
	@Test
	void testincreaseCreditLimitFromNonZeroAvailableFunds() {
		Credit testFunds = new Credit ("testAccount", 10.0);
		testFunds.increaseCreditLimit(20.0);
		assertEquals(testFunds.getAvailableFunds(), 30.0);
	}
	
	
	@Test
	void testReduceCreditLimitFromZero() {
		Credit testFunds = new Credit ("testAccount", 10.0);
		testFunds.reduceCreditLimit(20.0);
		assertEquals(testFunds.getCreditLimit(), 10.0);
	}
	
	@Test
	void testReduceCreditLimitFromNonZero() {
		Credit testFunds = new Credit ("testAccount", 30.0);
		testFunds.reduceCreditLimit(20.0);
		assertEquals(testFunds.getCreditLimit(), 10.0);
	}
	
	@Test
	void testReduceCreditLimitTooMuchFromNonZero() {
		Credit testFunds = new Credit ("testAccount", 30.0);
		testFunds.reduceCreditLimit(40.0);
		assertEquals(testFunds.getCreditLimit(), 30.0);
	}
	
	
	@Test
	void testFundsAvailableWithZeroCredit() {
		Credit testFunds = new Credit ("testAccount");
		assertFalse(testFunds.fundsAvailable(30.0));
	}	
	
	
	@Test
	void testFundsAvailableWithNotEnoughCredit() {
		Credit testFunds = new Credit ("testAccount", 20.0);
		assertFalse(testFunds.fundsAvailable(30.0));
	}	
	
	@Test
	void testFundsAvailableWithEnoughCredit() {
		Credit testFunds = new Credit ("testAccount", 40.0);
		assertTrue(testFunds.fundsAvailable(30.0));
	}	
	
	
	@Test
	void testpaymentWithinCreditOwedPaymentLessThanCredit() {
		Credit testFunds = new Credit ("testAccount", 40.0);
		assertTrue(testFunds.paymentWithinCreditOwed(30.0));
	}	
	
	@Test
	void testpaymentWithinCreditOwedPaymentMoreThanCredit() {
		Credit testFunds = new Credit ("testAccount", 20.0);
		assertFalse(testFunds.paymentWithinCreditOwed(30.0));
	}	
	
	
	@Test
	void testAddFundsWithinCreditOwed() {
		Credit testFunds = new Credit ("testAccount", 20.0);
		testFunds.removeFunds(10.0);
		testFunds.addFunds(5.0);
		assertEquals(testFunds.getAvailableFunds(), 15.0);
	}	
	
	@Test
	void testAddFundsWithinOverCreditOwed() {
		Credit testFunds = new Credit ("testAccount", 20.0);
		testFunds.removeFunds(10.0);
		testFunds.addFunds(15.0);
		assertEquals(testFunds.getAvailableFunds(), 10.0);
	}	
	
	@Test
	void testAddFundsToZeroCreditLimit() {
		Credit testFunds = new Credit ("testAccount");
		testFunds.addFunds(15.0);
		assertEquals(testFunds.getAvailableFunds(), 0.0);
	}	
	
	
	
}
