package demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Customer {
	
	private static int idNumber = 1; 
	
	private int id;
	private String name;
	private ArrayList<Account> accounts;
	
	
	public Customer(String name) {
		this.setId(idNumber);
		this.setAccounts(new ArrayList<Account>());
		Customer.idNumber += 1;
		this.setName(name);
	}
	
	
	public void customerLogon() {
		System.out.println("Welcome " +this.getName() + " (id: " +this.getId() + ")");
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
	
	
	
	public ArrayList<Account> listAccounts() {
		return accounts;
	}
	
	
	public Set<Integer> getAccountNumbers() {
		Set<Integer> accNums = new HashSet<Integer>();
		for (Account account : this.getAccounts()) {
			accNums.add(account.getAccountNumber());
		}
		return accNums;
	}
	
	
	public int amountOfAccounts() {
		return this.getAccounts().size();
	}
		
	
	
	public ArrayList<Account> listAccounts(String accountType) {
		ArrayList<Account> specificTypeList = new ArrayList<Account>();
		for (Account account : accounts){
			if (account.getClass().getSimpleName().equals(accountType)) {
				specificTypeList.add(account);				
			}
		}
		return accounts;
	}
	
	public void openAccount() { 
		System.out.println("Which account would you like to open?");
		String accType = Account.selectAccountTypeMenu();
		Account newAcc = GetAccountFactory.getAccount(accType);
		newAcc.custOpenAccount();
		this.addAccount(newAcc);
		System.out.println("New " + accType + " account called " +newAcc.getAccountName() +" opened");

	}
	

	public void updateAccount() {
		System.out.println("Which account would you like to update?");
		Account accToUpdate = Account.selectAccountMenu(this.getAccounts());
		if (this.getAccount(accToUpdate.getAccountNumber()) != null) {
			accToUpdate.custUpdateAccount();
		}
		else {
			System.out.println("Sorry, that account does not exist");
		}
	}
	
	
	public void closeAccount() {
		System.out.println("Which account would you like to close?");
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
	
	public void statementForAccount() {
		System.out.println("Which account statement would you like to see?");
		Account accStmt = Account.selectAccountMenu(this.getAccounts());
		if (this.getAccount(accStmt.getAccountNumber()) != null) {
			accStmt.getStatement();
		}
		else {
			System.out.println("Sorry, that account does not exist");
		}
	}
	
	
	
	
	
	public void addAccount(Account account) {
		accounts.add(account);
	}
	
	
	public boolean haveAccountType(String accountType) {
		for (Account account : accounts){
			if (account.getClass().equals(accountType)) {
				return true;				
			}
		}
		return false;
	}
	
	
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
