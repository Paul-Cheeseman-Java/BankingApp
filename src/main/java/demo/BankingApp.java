package demo;


public class BankingApp {
	
	public static void main(String[] args) {
		Teller tell = Bank.getTeller(Bank.addTeller());
		Customer customer = new Customer("Test Bod");
		tell.addCustomer(customer);
		Bank.startApp();
	}


}
