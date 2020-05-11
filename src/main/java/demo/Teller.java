package demo;

import java.util.ArrayList;
import java.util.Scanner;

public class Teller implements Updateable {
	
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
				System.out.println("Returned Cust Name: " +customer.getName());
				return customer;
			}
		}
		return null;
	}

	
	public boolean customerRemovable(int id) {
		 return this.getCustomer(id).amountOfAccounts() == 0;
	}
	
	
	//public void openAccount() {}
	public void updateAccount() {}
	public void closeAccount() {}

	
	public Customer obtainValidCustomer() {
		int valCust = this.promptEnterCustomerID();
		while (!this.customerExist(valCust)) {
			System.out.println("Please enter the id of an existing customer");
			valCust = this.promptEnterCustomerID();
		}
		return this.getCustomer(valCust);
	}
	

	
	public void openAccount() {
		//Just need to point to the returned variable
		Customer customer = this.obtainValidCustomer();
	
		System.out.println("Which type of account would you like to open?");

		String accType = Account.selectAccountTypeMenu();
		
		if (accType == "Current") {
			Current newAcc = new Current();
			newAcc.setAccountName(newAcc.promptEnterAccountName());
			customer.addAccount(newAcc);
		} else if (accType == "Credit") {
			Credit newAcc = new Credit();
			newAcc.setAccountName(newAcc.promptEnterAccountName());
			customer.addAccount(newAcc);
		} else if (accType == "Saving") {
			Saving newAcc = new Saving();
			newAcc.setAccountName(newAcc.promptEnterAccountName());
			customer.addAccount(newAcc);
		}
		System.out.println("New " +accType+ " account opened");
	}
	
	/*
	public void closeAccount() {
		Account accToClose = Account.selectAccountMenu(this.getAccounts());
		if (this.getAccount(accToClose.getAccountNumber()) != null) {
			if (accToClose.closeAccount()) {
				this.removeAccount(accToClose.getAccountNumber());
			} else {
				this.closeAccount();
			}
		}
		else {
			System.out.println("Sorry, that account does not exist");
		}
	}
	
	
	public void updateAccount() {
		Account accToUpdate = Account.selectAccountMenu(this.getAccounts());
		if (this.getAccount(accToUpdate.getAccountNumber()) != null) {
			accToUpdate.setAccountName(accToUpdate.promptEnterAccountName());
		}
		else {
			System.out.println("Sorry, that account does not exist");
		}
	}
	*/
	
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
