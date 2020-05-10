package demo;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	
	private String name;
	private ArrayList<Customer> customers;
	
	/* HAVE I BEEN CONSISTENT THROUGH ALL CLASSES WITH USE OF this ANNOTATION???? */
	
	//Logon screen - Welcome etc, are you a Teller or Customer
	//Teller and Customer have own menus
	
	
	
	public void listCustomers() {
		
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
	
	
	
	public void addCustomer(Customer customer) {
		this.customers.add(customer);
	}
	
	public void removeCustomer(Customer customer) {
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
	


}
