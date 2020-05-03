package demo;

import java.util.ArrayList;

public class Account implements Comparable<Account> {
	
	private String name;
	private int accountNumber;
	private double balance;
	private ArrayList<Txn> txns;
	
	private static int accountNumberGenerator = 1;
	

	public static String getFormattedAccountNumber(int accountNum){
		return String.format("%08d", accountNum);
	}

	public Account(String name) {
		accountNumberGenerator += 1;
		setAccountName(name);
		setTransactions(new ArrayList<Txn>());
		setAccountNumber(accountNumberGenerator);
	}
	
	public Account(String name, double balance) {
		accountNumberGenerator += 1;
		setAccountName(name);
		setBalance(balance);
		setTransactions(new ArrayList<Txn>());
		setAccountNumber(accountNumberGenerator);
	}
	
	public double getAvailableFunds() {
		return balance;			
	}
	
	public boolean fundsAvailable(double amount) {
		if (this.balance >= amount) {
			return true;
		}
		else {
			System.out.println(this.getInsufficientFundsMsg());
			return false;
		}
	}
	
		
	public boolean removeFunds(double amount) {
		if (fundsAvailable(amount)) {
			this.debitBalance(amount);
			return true;
		} 
		else {
			return false;
		}
	}
	
	
	public boolean addFunds(double amount) {
		this.creditBalance(amount);
		return true;
	}
	
	
	public String getInsufficientFundsMsg() {
		return "You don't have sufficent funds in your " +name+ " account";
	}

		
	public void creditBalance(double amount) {
		this.balance += amount;
		
		//Put in a seperate method for adding txns, that way Transferables can override and add a/c 
		//they're interacting with instead of own a/c as per default
		
		//Also instead of Account No in statement have 'Other Acc' also maybe blank 
		//if txn.account = this.account
		
		
		this.getTransactions().add(new Txn(amount, "Credit", this.getBalance(), this.getAccountNumber()));
		System.out.println(this.getCreditMadeMsg(amount));
	} 
	
	
	public void debitBalance(double amount) {
		this.balance -= amount;
		this.getTransactions().add(new Txn(amount, "Debit", this.getBalance(), this.getAccountNumber()));
		System.out.println(this.getDebitMadeMsg(amount));
	} 

	public String getDebitMadeMsg(double amount) {
		return "You have removed  �" +amount+ " from your " +name+ " account";
	}
	
	public String getCreditMadeMsg(double amount) {
		return "You have added �" +amount+ " to your " +name+ " account";
	}
	
	public void setTransactions(ArrayList<Txn> txns){
		this.txns = txns;  
	}
	
	
	public ArrayList<Txn> getTransactions(){
		return txns;
	}


	public void listTransactions(){
		for (Txn txn : txns) {
			String strAmount = String.valueOf(txn.getAmount());
			String formatOffset = "";
			if (strAmount.length() < 4) {
				
				formatOffset = "        ";
			}
			else if (strAmount.length() < 5) {
				formatOffset = "       ";
			}
			else if (strAmount.length() < 6) {
				formatOffset = "      ";
			}
			else if (strAmount.length() < 7) {
				formatOffset = "     ";
			}
			else if (strAmount.length() < 8) {
				formatOffset = "    ";
			}
			else if (strAmount.length() < 9) {
				formatOffset = "   ";
			}
			if (txn.getType().equals("Debit")) {
				System.out.println(txn.getStrDate() + "   " +txn.getStrTime()+ "     Debit" +formatOffset+  txn.getAmount()+ "     " +Account.getFormattedAccountNumber(txn.getAccountNumber())+ "     " +txn.getBalance());
			}
			else {
				System.out.println(txn.getStrDate() + "   " +txn.getStrTime()+ "    " +txn.getType()+  formatOffset +txn.getAmount()+ "     " +Account.getFormattedAccountNumber(txn.getAccountNumber())+ "     " +txn.getBalance());
			}
		}
	}
	
	public void getStatement(){
		System.out.println("Transactions for Account: " + Account.getFormattedAccountNumber(this.getAccountNumber()));
		System.out.println("Customer Account Reference: " +this.getAccountName());
		System.out.println("   Date        Time       Type      Amount    Account No.  Balance");

		this.listTransactions();
	}

	
	public String getAccountName() {
		return name;
	}

	public void setAccountName(String name) {
		this.name = name;
	}


	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int compareTo(Account account) {  
		return this.name.compareTo(account.getAccountName());  
	}

	public int getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(int accountNum) {
		this.accountNumber = accountNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}	

