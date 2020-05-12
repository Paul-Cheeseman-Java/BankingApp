package demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Current extends Account implements Transferable {

	private double overdraft;	//Default is 0.0
	
	public Current(String name, double balance, double overdraft) {
		super(name, balance);
		setOverdraft(overdraft);
	}
	
	public Current(String name, double balance) {
		super(name, balance);
	}

	
	public Current() {

	}
		

	public static String overdraftLineFormat(double val){
		return String.format("%" + 7 + "s", val);
	}

	
	@Override
	public void custOpenAccount() { 
		this.setAccountName(this.promptEnterAccountName());
	}
	
	@Override
	public void custUpdateAccount() { 
		this.setAccountName(this.promptEnterAccountName());
	}

	
	@Override
	public void tellerOpenAccount() { 
		this.setAccountName(this.promptEnterAccountName());
		this.addFunds(this.promptEnterAccountBalance());
		this.promptEnterAccountOverdraftIncrease();
	}
	
	//tellerUpdateMenu (need a customer one??)
	//What would you like to update
	//Account name
	//Credit limit?
	
	@Override
	public void tellerUpdateAccount() { 
		this.setAccountName(this.promptEnterAccountName());
		/*
		double requestedUpdate = this.promptEnterAccountOverdraft();
		if (requestedUpdate > this.getOverdraft()) {
			this.increaseOverdraftLimit(requestedUpdate);
		} else if (requestedUpdate < this.getOverdraft()) {
			this.reduceOverdraftLimit(requestedUpdate);
		}
		*/
		
	}

	
	
	
	
	@Override
	public void getStatement(){
		System.out.println("");
		System.out.println("  Transactions for Account: " + Account.getFormattedAccountNumber(this.getAccountNumber()));
		System.out.println("              Account Type: " +this.getClass().getSimpleName());
		System.out.println("Customer Account Reference: " +this.getAccountName());
		System.out.println("---------------------------------------------------------------|");
		System.out.println("         Current Overdraft: " +	Current.overdraftLineFormat(this.getOverdraft()) +"                            |");           
		System.out.println("---------------------------------------------------------------|");
		System.out.println("   Date    |     Time   |    Type   |    Amount   |  Balance   |");
		System.out.println("---------------------------------------------------------------|");
		this.listTransactions();
	}
	
	
		
	public boolean transferCreditTo(Transferable txfrObj, double amount) {
		if (fundsAvailable(amount)) {
			if (txfrObj.recieveTransferedCredit(amount)) {
				this.removeFunds(amount);
				return true;
			}
			else {
				return false;
			}
			
		}
		else {
			return false;			
		}
	}
	
	public boolean transferDebitTo(Transferable txfrObj, double amount) {
		if (txfrObj.recieveTransferedDebit(amount)) {
			this.addFunds(amount);
			return true;
		}
		else {
			return false;
		}
	}	
	
	
	public boolean recieveTransferedCredit(double amount) {
		return this.addFunds(amount);
	}
	
	public boolean recieveTransferedDebit(double amount) {
		return this.removeFunds(amount);
	}
	
	
	
	@Override
	public double getAvailableFunds() {
		return getOverdraft() + getBalance();			
	}
	
	private boolean inOverdraft() {
		return (this.getAvailableFunds() - this.getBalance()) < getOverdraft();
	}

	@Override
	public boolean fundsAvailable(double amount) {
		if ((this.getBalance() + getOverdraft()) >= amount) {
			return true;			
		}
		else {
			System.out.println(getInsufficientFundsMsg());
			return false;			
		}
	}
	
	@Override
	public boolean removeFunds(double amount) {
		if (fundsAvailable(amount)) {
			this.debitBalance(amount);
			if (inOverdraft()) {
				System.out.println(this.getInOverdraftMsg());
			}
			return true;
		} 
		else {
			return false;
		}
	}
	
	
	public void promptEnterAccountOverdraftIncrease() {
		System.out.println("Enter amount of overdraft increase:");
		this.increaseOverdraftLimit(this.getValidAmount());
	}
	
	public void promptEnterAccountOverdraftdecrease() {
		System.out.println("Enter amount of overdraft decrease:");
		this.reduceOverdraftLimit(this.getValidAmount());
	}

	
	

	
	private String getInOverdraftMsg() {
		return "You are now in your overdraft on your " +getAccountName()+ " current account with £ " + getAvailableFunds() + " remaining";
	}
	
	private String getReducedOverdraftMsg() {
		return "Your overdraft for your " +getAccountName()+ " current account has been reduced to " +getOverdraft();
	}
	
	private String getIncreasedOverdraftMsg() {
		return "Your overdraft for your " +getAccountName()+ " current account has been increased to " +getOverdraft();
	}
	
	private String getFailedReductionOverdraftMsg() {
		return "You do not have the credit in your " +getAccountName()+ " current account to reduce your overdraft by that amount";
	}
	
	private String getOverdraftReductionTooLargeMsg() {
		return "The overdraft for your " +getAccountName()+ " current account is " +overdraft+ " so requested reduction is too large";
	}
	
	
	public boolean reduceOverdraftLimit(double reductionAmount) {
		if (reductionAmount > this.overdraft) {
			System.out.println(this.getOverdraftReductionTooLargeMsg());
			return false;
		}
		//Check that the overdraft has the space to be reduced
		else if ((this.getBalance() + (this.overdraft - reductionAmount)) > 0) {
			this.setOverdraft(this.overdraft - reductionAmount);
			System.out.println(getReducedOverdraftMsg());
			return true;			
		}
		else {
			System.out.println(getFailedReductionOverdraftMsg());
			return false;
		}
	}
	
	
	public boolean increaseOverdraftLimit(double newLimit) {
		this.setOverdraft(newLimit);
		System.out.println(getIncreasedOverdraftMsg());
		return true;
	}
		

	public double getOverdraft() {
		return overdraft;
	}

	private void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

}
