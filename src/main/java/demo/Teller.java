package demo;

import java.util.ArrayList;
import java.util.Scanner;

public class Teller {
	
	private static int idNumber = 1; 

	private int id;
	private ArrayList<Customer> customers;
	
	public Teller(ArrayList<Customer> bankCustomers) {
		setId(idNumber);
		idNumber += 1;
		this.customers = bankCustomers;
	}
	
	public void addCustomer(Customer customer) {
		this.customers.add(customer);
		System.out.println("A customer with an id of: " + customer.getId() + " has been created");
	}
	
	public void removeCustomer() {
		Customer customer = this.obtainValidCustomer();
		if (this.customerRemovable(customer.getId())) {
			this.customers.remove(customer);
			System.out.println("Customer removed");
		} else {
			System.out.println("The customer still has accounts open, cannot be removed");
		}
	}


	public static String tellerLoginMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do?");
		System.out.println("1 - Create/Update/Remove a customer");
		System.out.println("2 - Open/Update/Close a customers account");
		System.out.println("3 - Create a new teller account");
		System.out.println("0 - Exit");
		char actionMenu = sc.next().toCharArray()[0];
		while (actionMenu != '1' && actionMenu != '2' && actionMenu != '3' && actionMenu != '0'){
			System.out.println("Please select a valid option:");
			System.out.println("1 - Create/Update/Remove a customer");
			System.out.println("2 - Open/Update/Close a customers account");
			System.out.println("3 - Create a new teller account");
			System.out.println("0 - Exit");
			actionMenu = sc.next().toCharArray()[0];
		}
		String actType = "";
		if (actionMenu == '1') {
			actType = "Customer Details";
		}
		else if (actionMenu == '2') {
			actType = "Customer Account";
		}
		else if (actionMenu == '3') {
			actType = "New Teller";
		}
		else if (actionMenu == '0') {
			actType = "Exit";
		}
		return actType;
	}

	
	public void tellerLogon() {
		System.out.println("Welcome Teller (id: " +this.getId() + ")");
		String choice = Teller.tellerLoginMenu();
		if (choice.equals("Customer Details")) {
			this.tellerCustomerManagementMenu();
		} else if (choice.equals("Customer Account")) {
			this.tellerCustomerAccountMenu();
		} else if (choice.equals("New Teller")) {
			System.out.println("A teller with the id of " +Bank.addTeller() + " has been created");
			this.tellerLogon();
		} else if (choice.equals("Exit")) {
			Bank.startApp();
		}
	}
	
	
	public void tellerCustomerAccountMenu() {
		String choice = Account.actionMenu();
		if (choice.equals("Open")) {
			this.openAccount();
		} else if (choice.equals("Update")) {
			this.updateAccount();
		} else if (choice.equals("Close")) {
			this.closeAccount();
		} else if (choice.equals("Statement")) {
			this.statementForAccount();
		} else if (choice.equals("Exit")) {
			this.tellerLogon();
		} 
	}
	
	
	
	public static String tellerCustomerUpdateMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do?");
		System.out.println("1 - Add a customer");
		System.out.println("2 - Update a customers name");
		System.out.println("3 - Remove a customer");
		System.out.println("0 - Exit");
		char actionMenu = sc.next().toCharArray()[0];
		while (actionMenu != '1' && actionMenu != '2' && actionMenu != '3' && actionMenu != '0'){
			System.out.println("Please select a valid option:");
			System.out.println("1 - Add a customer");
			System.out.println("2 - Update a customers name");
			System.out.println("3 - Remove a customer");
			System.out.println("0 - Exit");
			actionMenu = sc.next().toCharArray()[0];
		}
		String actType = "";
		if (actionMenu == '1') {
			actType = "Add Customer";
		}
		else if (actionMenu == '2') {
			actType = "Update Customer";
		}
		else if (actionMenu == '3') {
			actType = "Remove Customer";
		}
		else if (actionMenu == '0') {
			actType = "Exit";
		}
		return actType;
	}
	
	
	public void tellerCustomerManagementMenu() {
		String choice = Teller.tellerCustomerUpdateMenu();
		if (choice.equals("Add Customer")) {
			this.addNewCustomer();
		} else if (choice.equals("Update Customer")) {
			this.updateCustomer();
		} else if (choice.equals("Remove Customer")) {
			this.removeCustomer();
		} else if (choice.equals("Exit")) {
			this.tellerLogon();
		} 
	}
	
	public void addNewCustomer() {
		this.addCustomer(new Customer(this.promptEnterCustomerName()));
		this.tellerLogon();
	}
	
	public void updateCustomer() {
		Customer customer = this.obtainValidCustomer();
		customer.setName(this.promptEnterCustomerName());
		this.tellerLogon();
	}
	


	
	
	public String promptEnterCustomerName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter customer name (2 chars minimum)");
		String custName = sc.nextLine();
		while (!this.validCustomerName(custName)){
			custName = this.promptEnterCustomerName();
		}
		return custName;
	}
	
	
	public boolean validCustomerName(String str) {
		if (str.length() >= 2) {
			//Check for all blanks
			char[] charArray = str.toCharArray();
			for (char c : charArray) {
				if (c != ' ') {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	
	
	public int promptEnterCustomerID() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Customer ID:");
		while (!sc.hasNextInt()) {
	        System.out.println("Enter Customer ID (Must be numeric)");
	        sc.next();
	    }
	    return sc.nextInt();
	}

	
	public boolean customerExist(int id) {
		return (this.getCustomer(id) != null);
	}
	


	
	public Customer getCustomer(int id) {
		for (Customer customer : this.getCustomers()) {
			if (customer.getId()==id) {
				return customer;
			}
		}
		return null;
	}

	
	public boolean customerRemovable(int id) {
		 return this.getCustomer(id).amountOfAccounts() == 0;
	}
	
	
	
	public Customer obtainValidCustomer() {
		int valCust = this.promptEnterCustomerID();
		while (!this.customerExist(valCust)) {
			System.out.println("Please enter the id of an existing customer");
			valCust = this.promptEnterCustomerID();
		}
		return this.getCustomer(valCust);
	}
	

	
	public void openAccount() { 
		Customer customer = this.obtainValidCustomer();
		System.out.println("Which account would you like to open?");
		String accType = Account.selectAccountTypeMenu();
		Account newAcc = GetAccountFactory.getAccount(accType);
		if (newAcc == null) {
			this.tellerLogon();
		}
		newAcc.tellerOpenAccount();
		customer.addAccount(newAcc);
		System.out.println("New " + accType + " account called " +newAcc.getAccountName() +" opened");
		this.tellerLogon();
	}

	
	public void closeAccount() {
		Customer customer = this.obtainValidCustomer();
		System.out.println("Which account would you like to close?");
		Account accToClose = Account.selectAccountMenu(customer.getAccounts());
		if (accToClose == null) {
			this.tellerLogon();
		}
		
		if (customer.getAccount(accToClose.getAccountNumber()) != null) {
			if (accToClose.closeAccount()) {
				customer.removeAccount(accToClose.getAccountNumber());
			} else {
				customer.closeAccount();
				this.tellerLogon();
			}
		}
		else {
			System.out.println("Sorry, that account does not exist");
			this.tellerLogon();
		}
	}
	
	
	public void updateAccount() {
		Customer customer = this.obtainValidCustomer();
		System.out.println("Which account would you like to update?");
		Account accToUpdate = Account.selectAccountMenu(customer.getAccounts());
		if (accToUpdate == null) {
			this.tellerLogon();
		}
		
		else {
			accToUpdate.tellerUpdateAccount();
			this.tellerLogon();
		}
	}	
	
	public void statementForAccount() {
		Customer customer = this.obtainValidCustomer();
		System.out.println("Which account statement would you like to see?");
		Account accStmt = Account.selectAccountMenu(customer.getAccounts());
		
		if (accStmt == null) {
			this.tellerLogon();
		}
		
		else {
			accStmt.getStatement();
			this.tellerLogon();
		}
	}	
	

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	
}
