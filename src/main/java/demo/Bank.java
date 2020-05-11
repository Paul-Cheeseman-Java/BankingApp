package demo;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	
	private String name;
	private ArrayList<Customer> customers;
	private ArrayList<Teller> tellers;	
	/* HAVE I BEEN CONSISTENT THROUGH ALL CLASSES WITH USE OF this ANNOTATION???? */
	
	//Logon screen - Welcome etc, are you a Teller or Customer
	//Teller and Customer have own menus
	
	public Bank(String name) {
		this.setName(name);
		this.setCustomers(new ArrayList<Customer>());
		this.setTellers(new ArrayList<Teller>());
	}
	
	
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

	public void addTeller(ArrayList<Customer> customers) {
		Teller newTeller = new Teller(customers);
		tellers.add(newTeller);
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
		this.name = name;
	}


	public ArrayList<Customer> getCustomers() {
		return customers;
	}


	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}


	public ArrayList<Teller> getTellers() {
		return tellers;
	}


	public void setTellers(ArrayList<Teller> tellers) {
		this.tellers = tellers;
	}
	

	


}
