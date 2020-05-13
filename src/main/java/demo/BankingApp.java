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

		while (true) {
			tel.tellerLogon();			
		}
	
	}
	

}
