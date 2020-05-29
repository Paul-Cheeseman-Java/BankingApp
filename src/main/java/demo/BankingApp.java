package demo;


public class BankingApp {
	//Should all general msgs be static?
	//Have I been consistent with 'this'?
	//Do all comparisons need to be changed to Big Int?
	//Consistent with use of decimal point for doubles?
	//Consistent in debit/credit/deposit/withdrawal
	
	public static void main(String[] args) {

		Teller tell = Bank.getTeller(Bank.addTeller());
		Customer customer = new Customer("Test Bod");
		tell.addCustomer(customer);
		Bank.startApp();

	}
	

}
