package demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Customer {
	
	private static int idNumber = 1; 
	
	private int id;
	private String name;
	private ArrayList<Account> accounts;
	
	
	public Customer(String name) {
		setId(idNumber);
		setAccounts(new ArrayList<Account>());
		idNumber += 1;
	}
	
	
	public ArrayList<Account> listAccounts() {
		return accounts;
	}

	
	public boolean haveAccountType(String accountType) {
		for (Account account : accounts){
			if (account.getClass().equals(accountType)) {
				return true;				
			}
		}
		return false;
	}
	
	//put in credit account class (and specific elements)
	//then look to implement the transferable interface!!!!
	//then put in saving class
	//look into test suite

	//possible db and web version??? API could enable "customers to connect with account number???"
	
	
	public Account getAccount(int accountNum) {
		for (Account account : accounts){
			if (account.getAccountNumber() == accountNum) {
				return account;				
			}
		}
		return null;
	}


	public boolean renameAccount(int accountNum, String newName) {
		Account accToChange = getAccount(accountNum); 
		if (accToChange !=null) {
			accToChange.setAccountName(newName);
			return true;
		}
		else {
			return false;
		}
	}


	public boolean removeAccount(int accountNum) {
		Account accToRemove = getAccount(accountNum); 
		if (accToRemove !=null) {
			accounts.remove(accToRemove);
			return true;
		}
		else {
			return false;
		}
	}
	
	public static String getFormattedCustomerNumber(int custNum){
		return String.format("%06d", custNum);
	}

	
	public static int getIdNumber() {
		return idNumber;
	}


	public static void setIdNumber(int idNumber) {
		Customer.idNumber = idNumber;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<Account> getAccounts() {
		return accounts;
	}


	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
	
}
