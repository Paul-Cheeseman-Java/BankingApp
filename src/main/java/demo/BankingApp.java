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
		BankOCheese.addTeller();	//Add an initial teller with id of 1
		BankOCheese.startApp();

	}
	

}
