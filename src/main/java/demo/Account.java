package demo;

import java.math.BigDecimal;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Account implements Comparable<Account> {
	
	private String name;
	private int accountNumber;
	private double balance;
	private ArrayList<Txn> txns;
	
	private static int accountNumberGenerator;

	public Account() {
		accountNumberGenerator += 1;
		setAccountName(name);
		setTransactions(new ArrayList<Txn>());
		setAccountNumber(accountNumberGenerator);
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

	
	abstract void custOpenAccount();	
	
	//No need to be abstract at the moment as customers have same options on all accounts
	abstract void custUpdateAccount();	
	
	abstract void tellerOpenAccount();
	
	abstract void tellerUpdateAccount();
	
	
	
	public static String actionMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do?");
		System.out.println("1 - Open an account");
		System.out.println("2 - Update an account");
		System.out.println("3 - Close an account");
		System.out.println("4 - View a statement for an account");
		System.out.println("0 - Exit");
		char actionMenu = sc.next().toCharArray()[0];
		while (actionMenu != '1' && actionMenu != '2' && actionMenu != '3' && actionMenu != '4' && actionMenu != '0'){
			System.out.println("Please select a valid option:");
			System.out.println("1 - Open an account");
			System.out.println("2 - Update an account");
			System.out.println("3 - Close an account");
			System.out.println("4 - View a statement for an account");
			System.out.println("0 - Exit");
			actionMenu = sc.next().toCharArray()[0];
		}
		String actType = "";
		if (actionMenu == '1') {
			actType = "Open";
		}
		else if (actionMenu == '2') {
			actType = "Update";
		}
		else if (actionMenu == '3') {
			actType = "Close";
		}
		else if (actionMenu == '4') {
			actType = "Statement";
		}
		else if (actionMenu == '0') {
			actType = "Exit";
		}
		return actType;
	}
	

	public String custUpdateMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do?");
		System.out.println("1 - Update Account Name");
		System.out.println("2 - Add Funds");
		System.out.println("3 - Remove Funds");
		System.out.println("0 - Exit");
		char actionMenu = sc.next().toCharArray()[0];
		while (actionMenu != '1' && actionMenu != '2' && actionMenu != '3' && actionMenu != '0'){
			System.out.println("Please select a valid option:");
			System.out.println("1 - Update Account Name");
			System.out.println("2 - Add Funds");
			System.out.println("3 - Remove Funds");
			System.out.println("0 - Exit");
			actionMenu = sc.next().toCharArray()[0];
		}
		String actType = "";
		if (actionMenu == '1') {
			actType = "Update Name";
		}
		else if (actionMenu == '2') {
			actType = "Add Funds";
		}
		else if (actionMenu == '3') {
			actType = "Remove Funds";
		}
		else if (actionMenu == '0') {
			actType = "Exit";
		}
		return actType;
	}


	public String tellerUpdateMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do?");
		System.out.println("1 - Update Account Name");
		System.out.println("2 - Add Funds");
		System.out.println("3 - Remove Funds");
		System.out.println("0 - Exit");
		char actionMenu = sc.next().toCharArray()[0];
		while (actionMenu != '1' && actionMenu != '2' && actionMenu != '3' && actionMenu != '0'){
			System.out.println("Please select a valid option:");
			System.out.println("1 - Update Account Name");
			System.out.println("2 - Add Funds");
			System.out.println("3 - Remove Funds");
			System.out.println("0 - Exit");
			actionMenu = sc.next().toCharArray()[0];
		}
		String actType = "";
		if (actionMenu == '1') {
			actType = "Update Name";
		}
		else if (actionMenu == '2') {
			actType = "Add Funds";
		}
		else if (actionMenu == '3') {
			actType = "Remove Funds";
		}
		else if (actionMenu == '0') {
			actType = "Exit";
		}
		return actType;
	}

	
	public static String selectAccountTypeMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("1 - Basic account");
		System.out.println("2 - Current account");
		System.out.println("3 - Credit account");
		System.out.println("4 - Saving account");

		System.out.println("E - Exit");
		char whichAccountMenu = sc.next().toCharArray()[0];
		while (whichAccountMenu != '1' && whichAccountMenu != '2' && whichAccountMenu != '3' && 
				whichAccountMenu != '4' && whichAccountMenu != 'E' && whichAccountMenu != 'e'){
			System.out.println("Please setect a valid option:");
			System.out.println("1 - Basic account");
			System.out.println("2 - Current account");
			System.out.println("3 - Credit account");
			System.out.println("4 - Saving account");
			System.out.println("E - Exit");
			whichAccountMenu = sc.next().toCharArray()[0];
		}
		String accType = "";
		if (whichAccountMenu == '1') {
			accType = "Basic";
		}
		else if (whichAccountMenu == '2') {
			accType = "Current";
		}
		else if (whichAccountMenu == '3') {
			accType = "Credit";
		}
		else if (whichAccountMenu == '4') {
			accType = "Saving";
		}
		return accType;
	}
	
	

	
	

	public static Account selectAccountMenu(ArrayList<Account> accountList) {
		Scanner sc = new Scanner(System.in);
		int menuNum = 0;
		Collections.sort(accountList);
		System.out.println("Select the account:");
		for (Account account : accountList) {
			menuNum += 1;
			System.out.println(menuNum + " - Account Number: " +account.getAccountNumber() + " - Account name: " +account.getAccountName());
		}
		System.out.println("0 - Exit");
		if (sc.hasNextInt()){
			int input = sc.nextInt();
			//System.out.println("Value: " + input);
			if(input <= menuNum && input > 0) {
				System.out.println("Selected Account: " + accountList.get((input-1)).getAccountNumber());
				return accountList.get((input-1));
			}
			else if (input == 0) {
				return null;			
			}
		}
		Account.selectAccountMenu(accountList);
		//Irrelevant return statement
		return null;
	}

	
	
	public boolean validAccountName(String str) {
		if (str.length() >= 5) {
			//Check for all blanks
			char[] charArray = str.toCharArray();
			for (char c : charArray) {
				if (c != ' ') {
					return true;
				}
			}
		}
		return false;
	}
		

	public String promptEnterAccountName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter account name (5 chars minimum)");
		String accName = sc.nextLine();
		while (!this.validAccountName(accName)){
			accName = this.promptEnterAccountName();
		}
		return accName;
	}
	

	public void promptEnterAccountAddFunds() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter amount of funds to be added");
		double addVal = this.getValidAmount();
		this.addFunds(addVal);
	}
	
	
	public void promptEnterAccountRemoveFunds() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter amount of funds to be removed");
		double addVal = this.getValidAmount();
		this.removeFunds(addVal);
	}
	
	
	
	public double getValidAmount() {
		Scanner sc = new Scanner(System.in);
		boolean valueNotValid = true;
		double validAmount = 0.0;
		while (valueNotValid) { 
			while (!sc.hasNextDouble()) { 
				System.out.println("Please enter a number!");
				sc.next(); 
			}
			validAmount = sc.nextDouble();
			if (validAmount >= 0) {
				valueNotValid = false;
			}
			else {
				System.out.println("Please enter a positive number!");
			}
		}
		return validAmount;
	}
	
	
	public int getValidInt() {
		Scanner sc = new Scanner(System.in);
		boolean intNotValid = true;
		int validInt = 0;
		while (intNotValid) { 
			while (!sc.hasNextInt()) { 
				System.out.println("Please enter a number!");
				sc.next(); 
			}
			validInt = sc.nextInt();
			if (validInt >= 0) {
				intNotValid = false;
			}
			else {
				System.out.println("Please enter a positive number!");
			}
		}
		return validInt;
	}
	
	

	//https://stackoverflow.com/questions/3543729/how-to-check-that-a-string-is-parseable-to-a-double
	public double promptEnterAccountBalance() {
		System.out.println("Enter Account Balance");
		return this.getValidAmount();
	}
	
	
	public boolean isCloseable() {
		return this.getBalance() == 0.0;
	}
	
	public boolean closeAccount() {
		if (isCloseable()) {
			System.out.println(this.accountClosedMsg());
			return true;
		}
		else {
			System.out.println(this.accountNotClosedMsg());
			return false;
		}
		
	}
	
	public String accountClosedMsg() {
		return "The " + this.getClass().getSimpleName() + " account " + this.getAccountName() + " has been closed";
	}
	
	public String accountNotClosedMsg() {
		return "Unable to close the " + this.getClass().getSimpleName() + " account " + this.getAccountName() + " because the balance is not 0";
	}
	
	
	public static String getFormattedAccountNumber(int accountNum){
		return String.format("%08d", accountNum);
	}
	public static String statementNumFormat(double statementNum){
		return String.format("%" + 8 + "s", statementNum);
	}

	public boolean isTransferable() {
		return this instanceof Transferable;
	}
	
	
	public boolean makeTransferTo(Account account, String type, double amount) {
		if(this.isTransferable() && account.isTransferable()) {
			Transferable transferTo = (Transferable) account;
			Transferable transferFrom = (Transferable) this;
			if (type.contentEquals("Credit")) {
				transferFrom.transferCreditTo(transferTo, amount);
			}
			return true;
		}
		else {
			System.out.println("Money cannot be transfered between these account types");
			return false;
		}
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
		Collections.sort(txns);
		return txns;
	}


	public void listTransactions(){
		for (Txn txn : this.getTransactions()) {
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

