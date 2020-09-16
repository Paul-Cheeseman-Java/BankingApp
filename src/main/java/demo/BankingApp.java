package demo;


public class BankingApp {
	
	public static void main(String[] args) {
		Teller tell = Bank.getTeller(Bank.addTeller());

		System.out.println("When using the bank its best to intially open accounts for customer '1' with");
		System.out.println("teller '1' as tellers can set up account specifics (credit limit, initial");
		System.out.println("deposits etc) when opening accounts for customers.");
		System.out.println("");
		System.out.println("Teller with an id of: 1 has been created");
		Customer customer = new Customer("Test Bod");
		tell.addCustomer(customer);
		System.out.println("");
		
		
		Bank.startApp();
	}


}
