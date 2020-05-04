package demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Account implements Comparable<Account> {
	
	private String name;
	private int accountNumber;
	private double balance;
	private ArrayList<Txn> txns;
	
	private static int accountNumberGenerator;
	

	public static String getFormattedAccountNumber(int accountNum){
		return String.format("%08d", accountNum);
	}
	public static String statementNumFormat(double statementNum){
		return String.format("%" + 8 + "s", statementNum);
	}

	public boolean isTransferable() {
		return this instanceof Transferable;
	}
	
	public boolean isCurrent() {
		return this instanceof Current;
	}
	
	public boolean isCredit() {
		return this instanceof Credit;
	}
	
	
	public boolean changeOverdraftTo(double amount) {
		if(this.isCurrent()) {
			Current currentAccount = (Current)this;
			currentAccount.increaseOverdraftLimit(amount);
			return true;
		}
		else {
			System.out.println(noOverdraftOnAccountTypeMsg());
			return false;
		}
	}

	public String noOverdraftOnAccountTypeMsg() {
		return "You cannot have an overdraft on this account type";
	}

	
	public boolean increaseCreditLimitTo(double amount) {
		if(this.isCredit()) {
			Credit creditAccount = (Credit)this;
			creditAccount.increaseCreditLimit(amount);
			return true;
		}
		else {
			System.out.println(noCreditOnAccountTypeMsg());
			return false;
		}
	}
	
	
	public boolean reduceCreditLimitTo(double amount) {
		if(this.isCredit()) {
			Credit creditAccount = (Credit)this;
			creditAccount.reduceCreditLimit(amount);
			return true;
		}
		else {
			System.out.println(noCreditOnAccountTypeMsg());
			return false;
		}
	}
	
	
	public String noCreditOnAccountTypeMsg() {
		return "You cannot have a credit limit on this account type";
	}

	
	
	
	
	public boolean makeTransferTo(Account account, String type, double amount) {
		
		if(this.isTransferable() && account.isTransferable()) {
		
			Transferable transferTo = (Transferable) account;
			Transferable transferFrom = (Transferable) this;
			
			
			if (type.contentEquals("Credit")) {
				transferFrom.transferCreditTo(transferTo, amount);
			}
			else if (type.contentEquals("Debit")) {
				transferFrom.transferDebitTo(transferTo, amount);
			}
			return true;
			
			
			
		}
		else {
			System.out.println("Money cannot be transfered between these account types");
			return false;
		}
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


	public void addTxn(double amount, String type) {
		this.getTransactions().add(new Txn(amount, type, this.getBalance()));
	}
	
	public void creditBalance(double amount) {
		this.balance += amount;
		this.addTxn(amount, "Credit");
		System.out.println(this.getCreditMadeMsg(amount));
	} 
	
	
	public void debitBalance(double amount) {
		this.balance -= amount;
		this.addTxn(amount, "Debit");
		System.out.println(this.getDebitMadeMsg(amount));
	} 

	public String getDebitMadeMsg(double amount) {
		return "You have removed  £" +amount+ " from your " +name+ " account";
	}
	
	public String getCreditMadeMsg(double amount) {
		return "You have added £" +amount+ " to your " +name+ " account";
	}
	
	public void setTransactions(ArrayList<Txn> txns){
		this.txns = txns;  
	}
	
	
	public ArrayList<Txn> getTransactions(){
		return txns;
	}


	public void listTransactions(){
		for (Txn txn : txns) {
			if (txn.getType().equals("Debit")) {
				System.out.println(txn.getStrDate() + " |  " +txn.getStrTime()+ "  |   Debit   |   "  +Account.statementNumFormat(txn.getAmount())+ "  |   " +Account.statementNumFormat(Account.round(txn.getBalance(), 2)) + " |");
			}
			else {
				System.out.println(txn.getStrDate() + " |  " +txn.getStrTime()+ "  |  Credit   |   "  +Account.statementNumFormat(txn.getAmount())+ "  |   " +Account.statementNumFormat(Account.round(txn.getBalance(), 2)) + " |");
			}
		}
	}
	
	public void getStatement(){
		System.out.println("");
		System.out.println("  Transactions for Account: " + Account.getFormattedAccountNumber(this.getAccountNumber()));
		System.out.println("              Account Type: " +this.getClass().getSimpleName());
		System.out.println("Customer Account Reference: " +this.getAccountName());
		System.out.println("---------------------------------------------------------------|");
		System.out.println("   Date    |     Time   |    Type   |    Amount   |  Balance   |");
		System.out.println("---------------------------------------------------------------|");
		this.listTransactions();
	}

	
	/*
	 * Taken from:
	 * https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places#2808648
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
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

