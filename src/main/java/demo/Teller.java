package demo;

import java.util.ArrayList;

public class Teller implements Updateable {
	
	private static int idNumber = 1; 
	
	private int id;
	private String name;
	
	
	public Teller(String name) {
		setId(idNumber);
		idNumber += 1;
	}
	
	public void openAccount() {}
	public void updateAccount() {}
	public void closeAccount() {}
	/*
	 	Open - account
	 	Update - account
	 	Close - account
		
	//getCustomer (from a logon screen)
	//add, remove, update customer (go to customer menu?)
	
	

	public void openAccount() { 
		System.out.println("Which type of account would you like to open?");
		
		//getCustomer
		String accType = Account.selectAccountTypeMenu();
		
		if (accType == "Current") {
			Current newAcc = new Current();
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
	
	public static int getIdNumber() {
		return idNumber;
	}


	public static void setIdNumber(int idNumber) {
		Teller.idNumber = idNumber;
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


	
}
