package demo;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Credit extends Account implements Transferable {

	private double creditLimit;
	
	public Credit(String name, double creditLimit) {
		super(name);
		setCreditLimit(creditLimit);
		setBalance(creditLimit);
	}
	
	public Credit(String name) {
		super(name);
	}

	public Credit() {

	}
	
	
	@Override
	public void custOpenAccount() { 
		this.setAccountName(this.promptEnterAccountName());
		System.out.println("Needs setting up - Credit custOpen");
	}
	
	@Override
	public void custUpdateAccount() { 
		this.setAccountName(this.promptEnterAccountName());
	}

	
	@Override
	public void tellerOpenAccount() { 
		this.setAccountName(this.promptEnterAccountName());
		this.promptEnterCreditLimitIncrease();
	}
	

	
	@Override
	public String tellerUpdateMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do?");
		System.out.println("1 - Update Account Name");
		System.out.println("2 - Add Funds");
		System.out.println("3 - Remove Funds");
		System.out.println("4 - Increase Credit Limit");
		System.out.println("5 - Reduce Credit Limit");
		System.out.println("0 - Exit");
		char actionMenu = sc.next().toCharArray()[0];
		while (actionMenu != '1' && actionMenu != '2' && actionMenu != '3' && actionMenu != '4' 
				&& actionMenu != '5'  && actionMenu != '0'){
			System.out.println("Please select a valid option:");
			System.out.println("1 - Update Account Name");
			System.out.println("2 - Add Funds");
			System.out.println("3 - Remove Funds");
			System.out.println("4 - Increase Credit Limit");
			System.out.println("5 - Reduce Credit Limit");
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
		else if (actionMenu == '4') {
			actType = "Increase CL";
		}
		else if (actionMenu == '5') {
			actType = "Decrease CL";
		}
		else if (actionMenu == '0') {
			actType = "Exit";
		}
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
		} else if (choice.equals("Increase CL")) {
			this.promptEnterCreditLimitIncrease();
		} else if (choice.equals("Decrease CL")) {
			this.promptEnterCreditLimitDecrease();
		}
	}

	

	public void promptEnterCreditLimitIncrease() {
		System.out.println("Enter new credit limit");
		this.increaseCreditLimit(this.getValidAmount());		
	}

	
	public void promptEnterCreditLimitDecrease() {
		System.out.println("Enter new credit limit");
		this.reduceCreditLimit(this.getValidAmount());		
	}
	
	
	
	
	
	public boolean transferCreditTo(Transferable txfrObj, double amount) {
		if (this.fundsAvailable(amount)) {
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

	
	public static String creditLineFormat(double val){
		return String.format("%" + 7 + "s", val);
	}
	
	@Override
	public void getStatement(){
		System.out.println("");
		System.out.println("  Transactions for Account: " + Account.getFormattedAccountNumber(this.getAccountNumber()));
		System.out.println("              Account Type: " +this.getClass().getSimpleName());
		System.out.println("Customer Account Reference: " +this.getAccountName());
		System.out.println("---------------------------------------------------------------|");
		System.out.println("              Credit Limit: " +Credit.creditLineFormat(this.getCreditLimit()) +"                            |");           
		System.out.println("---------------------------------------------------------------|");
		System.out.println("   Date    |     Time   |    Type   |    Amount   |  Balance   |");
		System.out.println("---------------------------------------------------------------|");
		this.listTransactions();
	}
	
	

	public void increaseCreditLimit(double amount) {
		this.setBalance(this.getBalance() + amount);
		this.setCreditLimit(this.getCreditLimit() + amount);
		System.out.println(getIncreasedCreditLimitMsg());
	}
	
	public String getIncreasedCreditLimitMsg() {
		return "Credit limit increased to " +this.getCreditLimit();
	}
	
	public void reduceCreditLimit(double amount) {
		if (fundsAvailable(amount)) {
			this.setCreditLimit(this.getCreditLimit() - amount);
		}
	}
	

	
	
	@Override
	public boolean fundsAvailable(double amount) {
		//Changing to Big Int for comparison 
		//https://stackoverflow.com/questions/25160375/comparing-double-values-for-equality-in-java		
		//https://howtodoinjava.com/java/basics/correctly-compare-float-double/
		BigDecimal amountBigDec = new BigDecimal(amount);
		BigDecimal balanceBigDec = new BigDecimal(this.getBalance());
		if (amountBigDec.compareTo(balanceBigDec) < 1) {
			return true;
		}
		else {
			System.out.println(this.getInsufficientFundsMsg());
			return false;
		}
	}
	
	public boolean paymentWithinCreditOwed(double amount){
		BigDecimal amountBigDec = new BigDecimal(amount);
		BigDecimal balanceBigDec = new BigDecimal(this.getBalance());
		BigDecimal creditLimitBigDec = new BigDecimal(this.getCreditLimit());
		if (amountBigDec.compareTo(creditLimitBigDec.subtract(balanceBigDec)) < 1) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	@Override
	//Paying off credit used
	public boolean addFunds(double amount) {
		if (this.paymentWithinCreditOwed(amount)) {
			this.creditBalance(amount);
			//update points
			return true;
		}
		else {

			System.out.println(this.getPaymentLargerThanCreditOwedMsg(amount));
			
			return false;
		}
	}
	
	public String getPaymentLargerThanCreditOwedMsg(double amount) {
		return "Payment Rejected - £" +amount+ " exceeds the outstanding credit balance on the account";
	}
	
	
	
	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}


}
