package demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class TestCurrent {


	@Test
	void testCompareTo() {
		 ArrayList<Account> ordered = new ArrayList<Account>(Arrays.asList(new Current ("aTest", 0.0), new Current ("bTest", 0.0), new Current ("cTest", 0.0)));
		 ArrayList<Account> unOrdered = new ArrayList<Account>(Arrays.asList(new Current ("cTest", 0.0), new Current ("bTest", 0.0), new Current ("aTest", 0.0)));		
		 Collections.sort(unOrdered);
		 assertIterableEquals(ordered, unOrdered);
	}

	@Test
	void testGetAvailableFundsBalancePosNoOverdraft() {
		Current testFunds = new Current ("testAccount", 10.0);
		System.out.println(testFunds.getAvailableFunds());
		assertEquals(testFunds.getAvailableFunds(), 10.0);
	}

	@Test
	void testGetAvailableFundsBalancePosWithOverdraft() {
		Current testFunds = new Current ("testAccount", 10.0);
		testFunds.increaseOverdraftLimit(5.0);
		assertEquals(testFunds.getAvailableFunds(), 15.0);
	}
	
	@Test
	void testGetAvailableFundsBalanceNegWithOverdraft() {
		Current testFunds = new Current ("testAccount", 10.0);
		testFunds.increaseOverdraftLimit(5.0);
		testFunds.removeFunds(7.00);
		assertEquals(testFunds.getAvailableFunds(), 8.0);
	}
	

	@Test
	void testRemoveFundsBooleanWithEnoughFundsForDebit() {
		Current testFunds = new Current ("testAccount", 10.0);
		assertTrue(testFunds.removeFunds(5.0));
	}
	

	@Test
	void testRemoveFundsBooleanWhenNotEnoughFundsForDebit() {
		Current testFunds = new Current ("testAccount", 10.0);
		assertFalse(testFunds.removeFunds(10.01));
	}

	
	@Test
	void testRemoveFundsBooleanWhenNotEnoughFundsIncOverdraftForDebit() {
		Current testFunds = new Current ("testAccount", 10.0);
		testFunds.increaseOverdraftLimit(5.0);
		assertFalse(testFunds.removeFunds(16.00));
	}

	
	@Test
	void testRemoveFundsCalulationWithEnoughFundsForDebit() {
		Current testFunds = new Current ("testAccount", 10.0);
		testFunds.removeFunds(5.0);
		assertEquals(testFunds.getAvailableFunds(), 5.0);
	}
			
	@Test
	void testAddFundsBooleanOnAdd() {
		Current testFunds = new Current ("testAccount", 10.0);
		assertTrue(testFunds.addFunds(5.00));
	}
	
	
	@Test
	void testAddFundsCalculationOnAdd() {
		Current testFunds = new Current ("testAccount", 10.0);
		testFunds.addFunds(5.0);
		assertEquals(testFunds.getAvailableFunds(), 15.0);
	}
	
	
	@Test
	void testTxnAddedToTxnListOnCredit() {
		Current testFunds = new Current ("testAccount", 10.0);
		testFunds.addFunds(5.0);
		testFunds.addFunds(4.0);
		testFunds.addFunds(3.0);
		assertEquals(testFunds.getTransactions().size(), 3);
	}
	
	
	@Test
	void testTxnAddedToTxnListOnDebit() {
		Current testFunds = new Current ("testAccount", 10.0);
		testFunds.removeFunds(2.0);
		testFunds.removeFunds(5.0);
		assertEquals(testFunds.getTransactions().size(), 2);
	}
	
	@Test
	void testNoTxnAddedToTxnListWhenDebitRejected() {
		Current testFunds = new Current ("testAccount", 10.0);
		testFunds.removeFunds(2.0);
		testFunds.removeFunds(15.0);
		assertEquals(testFunds.getTransactions().size(), 1);
	}
	
	
	@Test
	void testIncreaseOverdraftLimit() {
		Current testFunds = new Current ("testAccount", 10.0);
		testFunds.increaseOverdraftLimit(5.0);
		assertEquals(testFunds.getAvailableFunds(), 15);
	}
	
	@Test
	void testReduceOverdraftLimitWhenCreditAvailableWithZeroBalance() {
		Current testFunds = new Current ("testAccount", 10.0);
		testFunds.increaseOverdraftLimit(7.0);
		testFunds.removeFunds(10);
		testFunds.reduceOverdraftLimit(2.0);
		assertEquals(testFunds.getAvailableFunds(), 5);
	}
	
	
	@Test
	void testReduceOverdraftLimitWhenCreditAvailableWithSurplusBalance() {
		Current testFunds = new Current ("testAccount", 10.0);
		testFunds.increaseOverdraftLimit(5.0);
		testFunds.removeFunds(5.0);
		testFunds.reduceOverdraftLimit(4.0);
		assertEquals(testFunds.getAvailableFunds(), 6);
	}
	
	@Test
	void testReduceOverdraftLimitWhenCreditAvailableNoSurplusBalance() {
		Current testFunds = new Current ("testAccount", 10.0);
		testFunds.increaseOverdraftLimit(5.0);
		testFunds.removeFunds(10.0);
		testFunds.reduceOverdraftLimit(4.0);
		assertEquals(testFunds.getAvailableFunds(), 1);
	}
	
	
	@Test
	void testReduceOverdraftWhenNoOverdraft() {
		Current testFunds = new Current ("testAccount", 10.0);
		assertFalse(testFunds.reduceOverdraftLimit(5.0));
	}
	
	@Test
	void testReduceOverdraftbyMoreThanOverdraftSizeWithAvailableBalance() {
		Current testFunds = new Current ("testAccount", 10.0);
		testFunds.increaseOverdraftLimit(5.0);
		assertFalse(testFunds.reduceOverdraftLimit(6.0));
	}

}
