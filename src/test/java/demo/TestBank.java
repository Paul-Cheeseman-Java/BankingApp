package demo;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class TestBank {
	
	@Test
	void testValidTellerwithNonValidTeller() {
		Assert.assertNull(Bank.validTeller(9999));
	}
	
	
	@Test
	void testGetTellerWithNonValidTeller() {
		Assert.assertNull(Bank.getTeller(9999));
	}
	
	
	@Test
	void testFindAccountWithValidAccount() {
		Account testAcc = new Basic("Test Account");
		Customer testCust = new Customer("Mr Test");
		//Set up account for customer
		ArrayList<Account> testAccList = new ArrayList<Account>();
		testAccList.add(testAcc);
		testCust.setAccounts(testAccList);
		//Set up customer for bank
		ArrayList<Customer> testCustList = new ArrayList<Customer>();
		testCustList.add(testCust);
		//Add customer to bank
		Bank.setCustomers(testCustList);
		Assert.assertNotNull(Bank.findAccount(testAcc.getAccountNumber()));
	}
	
	
	@Test
	void testFindAccountWithNonValidAccount() {
		Assert.assertNull(Bank.findAccount(9999));
	}
	
	
	
	
}
