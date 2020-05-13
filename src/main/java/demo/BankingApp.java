package demo;

import java.util.concurrent.TimeUnit;

public class BankingApp {

	//Should all general msgs be static?
	//Have I been consistent with 'this'?
	//Do all comparisons need to be changed to Big Int?
	//Consistent with use of decimal point for doubles?
	//Consistent in debit/credit/deposit/withdrawal
	
	public static void main(String[] args) {

		Bank BankOCheese = new Bank("BankOCheese");
		Teller tel = new Teller(BankOCheese.getCustomers());

		Customer bod1 = new Customer("bod1");
		tel.addCustomer(bod1);

		Basic testAcc = new Basic();
		testAcc.setAccountName("Basic Test");

		testAcc.addTxn(1, "Credit");
		try {
			TimeUnit.SECONDS.sleep(1);			
		} catch (Exception e) {
			
		}

		testAcc.addTxn(2, "Credit");
		testAcc.addTxn(3, "Debit");
		try {
			TimeUnit.SECONDS.sleep(1);			
		} catch (Exception e) {
			
		}
		testAcc.addTxn(4, "Credit");
		testAcc.addTxn(5, "Credit");
		testAcc.addTxn(6, "Debit");
		try {
			TimeUnit.SECONDS.sleep(1);			
		} catch (Exception e) {
			
		}
		
		testAcc.getStatement();
		
		
		/*
		while (true) {
			tel.tellerLogon();			
		}
		*/

	}
	

}
