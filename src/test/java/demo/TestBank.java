package demo;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;


/*
//public static Customer validCustomer(int custID) {}
//public static Teller validTeller(int tellerID) {}
//public static int getValidInt() {}
//public static Account getAccount(int accountNum) {}
//public static Account findAccount(int accountNum) {}	
//public static int addTeller() {}
 */


class TestBank {

	@Test
	void testAddTeller() {
		int testTellerID1 = Bank.addTeller();
		int testTellerID2 = Bank.addTeller();
		assertEquals(1, testTellerID1);
		assertEquals(2, testTellerID2);
	}
	
		
	@Test
	void testValidTellerwithValidTeller() {
		assertEquals(1, Bank.validTeller(Bank.addTeller()).getId());
	}
	
		
	@Test
	void testValidCustomerwithValidCustomer() {
		Bank.addTeller();
		Teller testTeller = Bank.validTeller(1);
		Customer testCust = new Customer("Test Customer");
		Customer returnedCust = Bank.validCustomer(1);
		assertEquals(testCust, returnedCust);
	}

	
}
