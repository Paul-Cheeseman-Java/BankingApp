package demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSaving {


	@Test
	void testBooleanRemoveFundsWithinDailyLimitAndAvailableFunds() {
		Saving test = new Saving("Test", 10.0);
		assertTrue(test.removeFunds(5.0));
	}

	@Test
	void testAmountRemoveFundsWithinDailyLimitAndAvailableFunds() {
		Saving test = new Saving("Test", 10.0);
		test.removeFunds(5.0);
		assertEquals(test.getBalance(), 5.0);
	}

		
	@Test
	void testBooleanRemoveFundsBreachingDailyLimit() {
		Saving test = new Saving("Test", 100.0);
		test.removeFunds(50.0);
		assertFalse(test.removeFunds(0.01));
	}
	
	@Test
	void testAmountRemoveFundsBreachingDailyLimit() {
		Saving test = new Saving("Test", 100.0);
		test.removeFunds(50.0);
		test.removeFunds(0.01);
		assertEquals(test.getBalance(), 50.0);
	}
	
	
	@Test
	void testBooleanDailyLimitResetThenRemoveFunds() {
		Saving test = new Saving("Test", 100.0);
		test.removeFunds(50.0);
		test.setLastTxnDate(test.getLastTxnDate().minusDays(1));
		assertTrue(test.removeFunds(40.0));
	}
	
	@Test
	void testAmountDailyLimitResetThenRemoveFunds() {
		Saving test = new Saving("Test", 100.0);
		test.removeFunds(50.0);
		test.setLastTxnDate(test.getLastTxnDate().minusDays(1));
		test.removeFunds(40.0);
		assertEquals(test.getBalance(), 10.0);
	}

	
	@Test
	void testBooleanRemoveFundsWithinDailyLimitAndNoFunds() {
		Saving test = new Saving("Test", 0.0);
		assertFalse(test.removeFunds(5.0));
	}

	@Test
	void testAmountRemoveFundsWithinDailyLimitAndNoFunds() {
		Saving test = new Saving("Test", 2.0);
		test.removeFunds(5.0);
		assertEquals(test.getBalance(), 2.0);
	}

	
	@Test
	void testBooleanAddFundsWithinDailyLimitAndAvailableFunds() {
		Saving test = new Saving("Test", 10.0);
		assertTrue(test.addFunds(5.0));
	}

	@Test
	void testAmountAddFundsWithinDailyLimitAndAvailableFunds() {
		Saving test = new Saving("Test", 10.0);
		test.addFunds(5.0);
		assertEquals(test.getBalance(), 15.0);
	}
	
	@Test
	void testBooleanAddFundsBreachingDailyLimit() {
		Saving test = new Saving("Test", 10.0);
		test.addFunds(45.0);
		assertFalse(test.addFunds(5.01));
	}
	
	@Test
	void testAmountAddFundsBreachingDailyLimit() {
		Saving test = new Saving("Test", 10.0);
		test.addFunds(45.0);
		test.addFunds(5.01);
		assertEquals(test.getBalance(), 55.0);
	}
	
	@Test
	void testBooleanDailyLimitResetThenAddFunds() {
		Saving test = new Saving("Test", 10.0);
		test.addFunds(50.0);
		test.setLastTxnDate(test.getLastTxnDate().minusDays(1));
		assertTrue(test.addFunds(10.0));
	}
	
	@Test
	void testAmountDailyLimitResetThenAddFunds() {
		Saving test = new Saving("Test", 10.0);
		test.addFunds(50.0);
		test.setLastTxnDate(test.getLastTxnDate().minusDays(1));
		test.addFunds(10.0);
		assertEquals(test.getBalance(), 70.0);
	}
	
	
	@Test
	void testwithinDailyWithdrawalLimitWithinLimit() {
		//Only testing true/false, doesn't change balance
		Saving test = new Saving("Test", 100.0);
		assertTrue(test.withinDailyWithdrawalLimit(40.0));
	}
	
	@Test
	void testwithinDailyWithdrawalLimitOverLimit() {
		//Only testing true/false, doesn't change balance
		Saving test = new Saving("Test", 100.0);
		assertFalse(test.withinDailyWithdrawalLimit(60.0));
	}
	
	
	@Test
	void testwithinDailyDepositLimitWithinLimit() {
		//Only testing true/false, doesn't change balance
		Saving test = new Saving("Test", 100.0);
		assertTrue(test.withinDailyDepositLimit(40.0));
	}
	
	@Test
	void testwithinDailyDepositlLimitOverLimit() {
		//Only testing true/false, doesn't change balance
		Saving test = new Saving("Test", 100.0);
		assertFalse(test.withinDailyDepositLimit(60.0));
	}
	
	@Test
	void testBooleanAddToDailyWithdrawalTotalWithinLimit() {
		Saving test = new Saving("Test", 100.0);
		assertTrue(test.addToDailyWithdrawalTotal(40));
	}
	
	@Test
	void testBooleanAddToDailyWithdrawalTotalBreachingLimit() {
		Saving test = new Saving("Test", 100.0);
		test.removeFunds(30.0);	//Will add £30 to withdrawal total
		assertFalse(test.addToDailyWithdrawalTotal(40));
	}
	
	
	@Test
	void testValueAddToDailyWithdrawalTotalWithinLimit() {
		Saving test = new Saving("Test", 100.0);
		test.addToDailyWithdrawalTotal(40.0);
		assertEquals(test.getDailyWithdrawalTotal(), 40.0);
	}
	
	@Test
	void testValueAddToDailyWithdrawalTotalBreachingLimit() {
		Saving test = new Saving("Test", 100.0);
		test.addToDailyWithdrawalTotal(70.0);
		assertEquals(test.getDailyWithdrawalTotal(), 0.0);
	}
	

	@Test
	void testBooleanAddToDailyDepositTotalWithinLimit() {
		Saving test = new Saving("Test", 100.0);
		assertTrue(test.addToDailyDepositTotal(40));
	}
	
	@Test
	void testBooleanAddToDailyDepositTotalBreachingLimit() {
		Saving test = new Saving("Test", 100.0);
		test.addFunds(30.0);	//Will add £30 to deposit total
		assertFalse(test.addToDailyDepositTotal(40));
	}
	
	
	@Test
	void testValueAddToDailyDepositTotalWithinLimit() {
		Saving test = new Saving("Test", 100.0);
		test.addToDailyDepositTotal(40.0);
		assertEquals(test.getDailyDepositTotal(), 40.0);
	}
	
	@Test
	void testValueAddToDailyDepositTotalBreachingLimit() {
		Saving test = new Saving("Test", 100.0);
		test.addToDailyDepositTotal(70.0);
		assertEquals(test.getDailyDepositTotal(), 0.0);
	}

}
