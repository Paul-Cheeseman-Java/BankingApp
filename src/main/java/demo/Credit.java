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
	

	public char tellerUpdateMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("1 - Account Name");
		System.out.println("2 - Increase Credit Limit");
		System.out.println("3 - Reduce Credit Limit");
		System.out.println("E - Exit");
		char updateMenu = sc.next().toCharArray()[0];
		while (updateMenu != '1' && updateMenu != '2' && updateMenu != '3' && 
				updateMenu != 'E' && updateMenu != 'e'){
			System.out.println("Please setect a valid option:");
			System.out.println("1 - Account Name");
			System.out.println("2 - Increase Credit Limit");
			System.out.println("3 - Reduce Credit Limit");
			System.out.println("E - Exit");
			updateMenu = sc.next().toCharArray()[0];
		}
		return updateMenu;
	}

	
	
	@Override
	public void tellerUpdateAccount() { 
		char choice = this.tellerUpdateMenu();
		if (choice == '1') {
			this.setAccountName(this.promptEnterAccountName());
		} else if (choice == '2') {
			this.promptEnterCreditLimitIncrease();
		} else if (choice == '3') {
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
