package demo;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	
	private static String name;
	private static ArrayList<Customer> customers;
	private static ArrayList<Teller> tellers;	
	/* HAVE I BEEN CONSISTENT THROUGH ALL CLASSES WITH USE OF this ANNOTATION???? */
	
	//Logon screen - Welcome etc, are you a Teller or Customer
	//Teller and Customer have own menus
	
	public Bank(String name) {
		this.setName(name);
		this.setCustomers(new ArrayList<Customer>());
		this.setTellers(new ArrayList<Teller>());
	}
	
	
	
	public static String bankStartMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("******************************");
		System.out.println("* Welcome to Bank'o Wonder'o *");
		System.out.println("******************************");
		System.out.println("1 - Customer sign in");
		System.out.println("2 - Teller sign in");
		char bankMenu = sc.next().toCharArray()[0];
		while (bankMenu != '1' && bankMenu != '2' && bankMenu != '3' && bankMenu != '0'){
			System.out.println("******************************");
			System.out.println("Please select a valid option:");
			System.out.println("******************************");
			System.out.println("1 - Customer sign in");
			System.out.println("2 - Teller sign in");
			bankMenu = sc.next().toCharArray()[0];
		}
		String bankUser = "";
		if (bankMenu == '1') {
			bankUser = "Customer";
		}
		else if (bankMenu == '2') {
			bankUser = "Teller";
		}
		return bankUser;
	}

	public static Customer validCustomer(int custID) {
		for (Customer customer : Bank.getCustomers()){
			if (customer.getId() == custID) {
				return customer;
			}
		}
		return null;
	}
	
	public static Teller validTeller(int tellerID) {
		for (Teller teller : Bank.getTellers()){
			if (teller.getId() == tellerID) {
				return teller;
			}
		}
		return null;
	}
	
	
	public static int getValidInt() {
		Scanner sc = new Scanner(System.in);
		boolean intNotValid = true;
		int validInt = 0;
		while (intNotValid) { 
			while (!sc.hasNextInt()) { 
				System.out.println("Please enter a number!");
				sc.next(); 
			}
			validInt = sc.nextInt();
			if (validInt >= 0) {
				intNotValid = false;
			}
			else {
				System.out.println("Please enter a positive number!");
			}
		}
		return validInt;
	}
	

	
	public static void startApp() {
		while(true) {
			String choice = Bank.bankStartMenu();
			if (choice.equals("Customer")) {
				System.out.println("Please enter your customer number:");
				Customer customer = Bank.validCustomer(Bank.getValidInt());
				if (customer != null) {
					customer.customerLogon();
				} else {
					System.out.println("A customer with that number doesn't exist");
					Bank.startApp();
				}
			}
			else if (choice.equals("Teller")) {
				System.out.println("Please enter your teller number:");
				Teller teller = Bank.validTeller(Bank.getValidInt());
				if ( teller != null) {
					teller.tellerLogon();
				} else {
					System.out.println("A teller with that number doesn't exist");
					Bank.startApp();
				}
			}
		}
	}
	
		
	
	public static Account getAccount(int accountNum) {
		Account locatedAcc = null;
		for (Customer customer: Bank.getCustomers()) {
			if (customer.getAccountNumbers().contains(accountNum)) {
				locatedAcc = customer.getAccount(accountNum);
			}
		}
		return locatedAcc;
	}


	public static Account findAccount(int accountNum) {
		Account locatedAcc = null;
		for (Customer customer: Bank.getCustomers()) {
			if (customer.getAccountNumbers().contains(accountNum)) {
				locatedAcc = customer.getAccount(accountNum);
			}
		}
		return locatedAcc;
	}	
	
	
	
	public void BankMenu() { 
		Scanner sc = new Scanner(System.in);
		System.out.println("Are you a Customer or Teller?");
		System.out.println("1 - Customer");
		System.out.println("2 - Teller");
		System.out.println("0 - Exit");
		char bankMenu = sc.next().toCharArray()[0];
		while (bankMenu != '0' && bankMenu != '1' && bankMenu != '2'){
			System.out.println("Are you a Customer or Teller?");
			System.out.println("1 - Customer");
			System.out.println("2 - Teller");
			System.out.println("0 - Exit");
		}
		if (bankMenu == '1') {
			//Customer Menu
		}
		else if (bankMenu == '2') {
			//Teller Menu
		}
		else {
			//Quit application
		}
	}

	public static int addTeller() {
		Teller newTeller = new Teller();
		tellers.add(newTeller);
		return newTeller.getId();
	}
	
	public void removeTeller(Teller teller) {
		tellers.remove(teller);
	}
	
	public void listTellers() {
		for (Teller teller : tellers) {
			System.out.println("Teller id: " +teller.getId());			
		}
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		Bank.name = name;
	}


	public static ArrayList<Customer> getCustomers() {
		return Bank.customers;
	}


	public void setCustomers(ArrayList<Customer> customers) {
		Bank.customers = customers;
	}


	public static ArrayList<Teller> getTellers() {
		return tellers;
	}


	public void setTellers(ArrayList<Teller> tellers) {
		Bank.tellers = tellers;
	}
	


}
