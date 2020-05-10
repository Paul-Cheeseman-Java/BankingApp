package demo;

import java.util.ArrayList;

public class Customer implements Updateable {
	
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
		System.out.println("Which type of account would you like to open?");
		String accType = Account.selectAccountTypeMenu();
		if (accType == "Current") {
			Current newAcc = new Current();
			newAcc.setAccountName(newAcc.promptEnterAccountName());
			this.addAccount(newAcc);
		} else if (accType == "Credit") {
			Credit newAcc = new Credit();
			newAcc.setAccountName(newAcc.promptEnterAccountName());
			this.addAccount(newAcc);
		} else if (accType == "Saving") {
			Saving newAcc = new Saving();
			newAcc.setAccountName(newAcc.promptEnterAccountName());
			this.addAccount(newAcc);
		}
	}

	public void closeAccount() {
		Account accToClose = Account.selectAccountMenu(this.getAccounts());
		if (this.getAccount(accToClose.getAccountNumber()) != null) {
			if (accToClose.closeAccount()) {
				this.removeAccount(accToClose.getAccountNumber());
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
