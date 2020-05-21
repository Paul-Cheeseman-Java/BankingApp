package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.jupiter.api.Test;

public class TestCustomer {

	
	@Test
	void testAmountOfAccounts() {
		Customer testCust = new Customer("Mr Test");
		Account testAcc1 = new Basic();
		Account testAcc2 = new Basic();
		testCust.addAccount(testAcc1);
		testCust.addAccount(testAcc2);
		assertEquals(2, testCust.amountOfAccounts());
	}
	
	
	
	@Test
	void testGetAccountWithoutValidAccountNumber() {
		Customer testCust = new Customer("Mr Test");
		Account testAcc1 = new Basic();
		Account testAcc2 = new Basic();
		testCust.addAccount(testAcc1);
		testCust.addAccount(testAcc2);
		assertSame(testAcc1, testCust.getAccount(testAcc1.getAccountNumber()));
		assertSame(testAcc2, testCust.getAccount(testAcc2.getAccountNumber()));
	}
	

	@Test
	void testGetAccountWithoutInvalidAccountNumber() {
		Customer testCust = new Customer("Mr Test");
		Account testAcc1 = new Basic();
		testCust.addAccount(testAcc1);
		assertNotSame(testAcc1, testCust.getAccount(9999));
	}

}
