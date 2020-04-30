package demo;

import java.util.ArrayList;

public class Bank {
	
	private String name;
	private ArrayList<Customer> customers;
	
	/* HAVE I BEEN CONSISTENT THROUGH ALL CLASSES WITH USE OF this ANNOTATION???? */
	
	
	public void listCustomers() {
		
	}

	public void addCustomer(Customer customer) {
		this.customers.add(customer);
	}
	
	public void removeCustomer(Customer customer) {
		this.customers.remove(customer);
	}
	
	public void updateCustomerName(Customer customer, String newName) {
		this.customers.remove(customer);
	}
	
	public Customer getCustomer(String id) {
		/*
		for (Accounting account : accounts) {
			if (name.equals(account.getAccountName())) {
				return account;
			}
		}
		*/
		return null;
	}
	
	//Add Customer
	//Remove Customer
	//List Customer
	
	//Create new account types
		//set name
		//set interest
		//set limits?

}
