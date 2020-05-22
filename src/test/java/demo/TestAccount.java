package demo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TestAccount {

	
	@Test
	void testValidAccountNameWithValidName() {
		Basic testAcc = new Basic();
		assertTrue(testAcc.validAccountName("ab cd"));
	}
	
	@Test
	void testValidAccountNameWithInvalidName() {
		Basic testAcc = new Basic();
		assertFalse(testAcc.validAccountName("a"));
	}
	
	
	@Test
	void testValidAccountNameWithSpaces() {
		Basic testAcc = new Basic();
		assertFalse(testAcc.validAccountName("     "));
	}
	
	
	@Test
	void testIsCloseableWhenCloseable() {
		Basic testAcc = new Basic();
		assertTrue(testAcc.isCloseable());
	}
	
	@Test
	void testIsCloseableWhenNotCloseable() {
		Basic testAcc = new Basic();
		testAcc.addFunds(20.00);
		assertFalse(testAcc.isCloseable());
	}
	
	@Test
	void testIsTransferableWhenTransferable() {
		Credit testAcc = new Credit();
		assertTrue(testAcc.isTransferable());
	}
	
	@Test
	void testIsTransferableWhenNotTransferable() {
		Basic testAcc = new Basic();
		assertFalse(testAcc.isTransferable());
	}
	
	
	@Test
	void testFundsAvailableWhenFundsAvailable() {
		Basic testAcc = new Basic();
		testAcc.addFunds(20.00);
		assertTrue(testAcc.fundsAvailable(10));
	}
	
	@Test
	void testFundsAvailableWhenFundsNotAvailable() {
		Basic testAcc = new Basic();
		assertFalse(testAcc.fundsAvailable(10));
	}
	
	
	@Test
	void testRemoveFundsWhenFundsAvailable() {
		Basic testAcc = new Basic();
		testAcc.addFunds(20);
		testAcc.removeFunds(10.00);
		assertEquals(10.00, testAcc.getAvailableFunds());
	}
	
	@Test
	void testRemoveFundsWhenFundsNotAvailable() {
		Basic testAcc = new Basic();
		testAcc.removeFunds(10.00);
		assertEquals(00.00, testAcc.getAvailableFunds());
	}
	
	
	@Test
	void testAddFundsWhenFundsInAccount() {
		Basic testAcc = new Basic();
		testAcc.addFunds(20);
		testAcc.addFunds(10);
		assertEquals(30.00, testAcc.getAvailableFunds());
	}
	
	@Test
	void testAddFundsWhenNoInAccount() {
		Basic testAcc = new Basic();
		testAcc.addFunds(10);
		assertEquals(10.00, testAcc.getAvailableFunds());
	}
	
	
	@Test
	void testAddTxn() {
		Basic testAcc = new Basic();
		testAcc.addTxn(10, "Credit");
		ArrayList<Txn> testTxns = testAcc.getTransactions();
		assertEquals(10.00, testTxns.get(0).getAmount());
		assertEquals("Credit", testTxns.get(0).getType());
	}
	
}
