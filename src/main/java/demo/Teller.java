package demo;

import java.util.ArrayList;
import java.util.Scanner;

public class Teller {
	
	private static int idNumber = 1; 
	
	private int id;
	private ArrayList<Customer> customers;
	
	public Teller(ArrayList<Customer> customers) {
		setId(idNumber);
		idNumber += 1;
		this.customers = customers;
	}
	
	public void addCustomer(Customer customer) {
		this.customers.add(customer);
	}
	
	public void removeCustomer(Customer customer) {
		if (this.customerRemovable(customer.getId())) {
			this.customers.remove(customer);			
		} else {
			System.out.println("The customer still has accounts open, cannot be removed");
		}
	}

	
	public void tellerLogon() {
		System.out.println("Welcome Teller (id: " +this.getId() + ")");
		String choice = Account.actionMenu();
		if (choice.equals("Open")) {
			this.openAccount();
		} else if (choice.equals("Update")) {
			this.updateAccount();
		} else if (choice.equals("Close")) {
			this.closeAccount();
		} else if (choice.equals("Statement")) {
			this.statementForAccount();
		} 
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
		newAcc.tellerOpenAccount();
		customer.addAccount(newAcc);
		System.out.println("New " + accType + " account called " +newAcc.getAccountName() +" opened");
	}

	
	public void closeAccount() {
		Customer customer = this.obtainValidCustomer();
		System.out.println("Which account would you like to close?");
		Account accToClose = Account.selectAccountMenu(customer.getAccounts());
		if (customer.getAccount(accToClose.getAccountNumber()) != null) {
			if (accToClose.closeAccount()) {
				customer.removeAccount(accToClose.getAccountNumber());
			} else {
				customer.closeAccount();
			}
		}
		else {
			System.out.println("Sorry, that account does not exist");
		}
	}
	
	
	public void updateAccount() {
		Customer customer = this.obtainValidCustomer();
		System.out.println("Which account would you like to update?");
		Account accToUpdate = Account.selectAccountMenu(customer.getAccounts());
		if (customer.getAccount(accToUpdate.getAccountNumber()) != null) {
			accToUpdate.tellerUpdateAccount();
		}
		else {
			System.out.println("Sorry, that account does not exist");
		}
	}	
	
	public void statementForAccount() {
		Customer customer = this.obtainValidCustomer();
		System.out.println("Which account statement would you like to see?");
		Account accStmt = Account.selectAccountMenu(customer.getAccounts());
		if (customer.getAccount(accStmt.getAccountNumber()) != null) {
			accStmt.getStatement();
		}
		else {
			System.out.println("Sorry, that account does not exist");
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
