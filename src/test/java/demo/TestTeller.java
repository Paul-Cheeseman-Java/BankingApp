package demo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TestTeller {

	
	@Test
	void testAddCustomer() {
		ArrayList<Customer> testCustList = new ArrayList<Customer>();
		Teller testTel = new Teller(testCustList);
		Customer testCust = new Customer("Mr Test");
		testTel.addCustomer(testCust);
		assertEquals(1, testCustList.size());
	}
	
	@Test
	void testValidCustomerNameValidName() {
		ArrayList<Customer> testCustList = new ArrayList<Customer>();
		Teller testTel = new Teller(testCustList);
		assertTrue(testTel.validCustomerName("Pass Test"));
	}
	
	@Test
	void testValidCustomerNameInvalidNameDueToLength() {
		ArrayList<Customer> testCustList = new ArrayList<Customer>();
		Teller testTel = new Teller(testCustList);
		assertFalse(testTel.validCustomerName("a"));
	}
	
	@Test
	void testValidCustomerNameInvalidNameDueToBlankChars() {
		ArrayList<Customer> testCustList = new ArrayList<Customer>();
		Teller testTel = new Teller(testCustList);
		assertFalse(testTel.validCustomerName("   "));
	}
	
	
	@Test
	void testCustomerExistWithValidCustomer() {
		//Add customer to bank/teller
		ArrayList<Customer> testCustList = new ArrayList<Customer>();
		Customer testCust = new Customer("Mr Test");
		testCustList.add(testCust);
		Teller testTel = new Teller(testCustList);
		assertTrue(testTel.customerExist(testCust.getId()));
	}
	
	@Test
	void testCustomerExistWithInvalidCustomer() {
		ArrayList<Customer> testCustList = new ArrayList<Customer>();
		Teller testTel = new Teller(testCustList);
		assertFalse(testTel.customerExist(9999));
	}

	
	
	@Test
	void testGetCustomerWithValidCustomer() {
		ArrayList<Customer> testCustList = new ArrayList<Customer>();
		Customer testCust = new Customer("Mr Test");
		testCustList.add(testCust);
		Teller testTel = new Teller(testCustList);
		assertEquals("Mr Test", testTel.getCustomer(testCust.getId()).getName());
	}
	
	
	@Test
	void testGetCustomerWithInvalidCustomer() {
		ArrayList<Customer> testCustList = new ArrayList<Customer>();
		Teller testTel = new Teller(testCustList);
		assertNull(testTel.getCustomer(99999));
	}
	
	
	
}
