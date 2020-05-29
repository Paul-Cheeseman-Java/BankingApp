package demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;


public class Saving extends Account {

	private static double dailyDepositLimit = 40.0;
	private static double dailyWithdrawalLimit = 40.0;
	
	private LocalDate lastTxnDate = LocalDate.now();
	private double dailyDepositTotal;
	private double dailyWithdrawalTotal;
	
	
	public Saving(String name, double balance, double overdraft) {
		super(name, balance);
	}
	
	public Saving(String name, double balance) {
		super(name, balance);
	}
	
	public Saving() {

	}
	
	
	@Override
	public void custOpenAccount() { 
		this.setAccountName(this.promptEnterAccountName());
	}
	

	@Override
	public String custUpdateMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do?");
		System.out.println("1 - Update Account Name");
		System.out.println("2 - Add Funds");
		System.out.println("3 - Remove Funds");
		System.out.println("0 - Exit");
		char actionMenu = sc.next().toCharArray()[0];
		while (actionMenu != '1' && actionMenu != '2' && actionMenu != '3' && actionMenu != '4' 
				&& actionMenu != '5'  && actionMenu != '0'){
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
		sc.close();
		return actType;
	}
	
	@Override
	public void custUpdateAccount() { 
		String choice = this.tellerUpdateMenu();
		if (choice.equals("Update Name")) {
			this.setAccountName(this.promptEnterAccountName());
		} else if (choice.equals("Add Funds")) {
			this.promptEnterAccountAddFunds();
		} else if (choice.equals("Remove Funds")) {
			this.promptEnterAccountRemoveFunds();
		}
	}

	
	@Override
	public void tellerOpenAccount() { 
		this.setAccountName(this.promptEnterAccountName());
		this.addFunds(this.promptEnterAccountBalance());
	}
	

	@Override
	public String tellerUpdateMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do?");
		System.out.println("1 - Update Account Name");
		System.out.println("2 - Add Funds");
		System.out.println("3 - Remove Funds");
		System.out.println("0 - Exit");
		char actionMenu = sc.next().toCharArray()[0];
		while (actionMenu != '1' && actionMenu != '2' && actionMenu != '3' && actionMenu != '4' 
				&& actionMenu != '5'  && actionMenu != '0'){
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
		sc.close();
		return actType;
	}
	
	@Override
	public void tellerUpdateAccount() { 
		String choice = this.tellerUpdateMenu();
		if (choice.equals("Update Name")) {
			this.setAccountName(this.promptEnterAccountName());
		} else if (choice.equals("Add Funds")) {
			this.promptEnterAccountAddFunds();
		} else if (choice.equals("Remove Funds")) {
			this.promptEnterAccountRemoveFunds();
		} 
	}


	
	@Override
	public boolean removeFunds(double amount) {
		if (fundsAvailable(amount)) {
			if (addToDailyWithdrawalTotal(amount)) {
				this.debitBalance(amount);
				return true;
			} else {
				System.out.println(getDailyWithdrawalLimitHitMsg(amount));
				return false;
			}
		} 
		else {
			return false;
		}
	}

	
	@Override
	public boolean addFunds(double amount) {
		if (addToDailyDepositTotal(amount)) {
			this.creditBalance(amount);
			return true;
		} else {
			System.out.println(getDailyDepositHitMsg(amount));
			return false;
		}
	}


	public boolean withinDailyDepositLimit(double amount) {
		//Changing to Big Int for comparison 
		//https://stackoverflow.com/questions/25160375/comparing-double-values-for-equality-in-java		
		//https://howtodoinjava.com/java/basics/correctly-compare-float-double/
		BigDecimal amountBigDec = new BigDecimal(amount);
		BigDecimal dailyDepositLimitBigDec = new BigDecimal(Saving.dailyDepositLimit);
		if (amountBigDec.compareTo(dailyDepositLimitBigDec) < 1) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean addToDailyDepositTotal(double amount){
		if (this.getLastTxnDate().isEqual(LocalDate.now())) {
			if (this.withinDailyDepositLimit(this.getDailyDepositTotal() + amount)) {
				this.setDailyDepositTotal(this.getDailyDepositTotal() + amount);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			this.setLastTxnDate(LocalDate.now());
			this.setDailyDepositTotal(amount);
			return true;
		}
	}
	
	
	public boolean withinDailyWithdrawalLimit(double amount) {
		BigDecimal amountBigDec = new BigDecimal(amount);
		BigDecimal dailyWithdrawalLimitBigDec = new BigDecimal(Saving.dailyWithdrawalLimit);
		if (amountBigDec.compareTo(dailyWithdrawalLimitBigDec) < 1) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean addToDailyWithdrawalTotal(double amount){
		if (this.getLastTxnDate().isEqual(LocalDate.now())) {
			if (this.withinDailyWithdrawalLimit(this.getDailyWithdrawalTotal() + amount)) {
				this.setDailyWithdrawalTotal(this.getDailyWithdrawalTotal() + amount);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			this.setLastTxnDate(LocalDate.now());
			this.setDailyWithdrawalTotal(amount);
			return true;
		}
	}

	public String getDailyWithdrawalLimitHitMsg(double amount) {
		return "Unable to withdraw £" + amount + " because it will breach the savings account daily withdrawal limit";
	}

	public String getDailyDepositHitMsg(double amount) {
		return "Unable to deposit £" + amount + " because it will breach the savings account daily deposit limit";
	}
	
	
	public static double getDailyDepositLimit() {
		return dailyDepositLimit;
	}

	public static void setDailyDepositLimit(double dailyDepositLimit) {
		Saving.dailyDepositLimit = dailyDepositLimit;
	}

	public static double getDailyWithdrawalLimit() {
		return dailyWithdrawalLimit;
	}

	public static void setDailyWithdrawalLimit(double dailyWithdrawalLimit) {
		Saving.dailyWithdrawalLimit = dailyWithdrawalLimit;
	}

	public LocalDate getLastTxnDate() {
		return lastTxnDate;
	}

	public void setLastTxnDate(LocalDate lastTxnDate) {
		this.lastTxnDate = lastTxnDate;
	}

	public double getDailyDepositTotal() {
		return dailyDepositTotal;
	}

	public void setDailyDepositTotal(double dailyDepositTotal) {
		this.dailyDepositTotal = dailyDepositTotal;
	}

	public double getDailyWithdrawalTotal() {
		return dailyWithdrawalTotal;
	}

	public void setDailyWithdrawalTotal(double dailyWithdrawalTotal) {
		this.dailyWithdrawalTotal = dailyWithdrawalTotal;
	}
	

}
