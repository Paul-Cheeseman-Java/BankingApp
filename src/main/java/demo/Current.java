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
	
	
	public void promptEnterTransferFunds() {
		System.out.println("Enter the account number you wish to transfer funds to:");
		int accountNum = this.getValidInt();
		if (Bank.findAccount(accountNum) !=null) {
			Account transferTo = Bank.findAccount(accountNum);
			if (transferTo instanceof Transferable) {
				Transferable transferable = (Transferable)transferTo; 
				System.out.println("Enter the amount of the payment you'd like to make");
				this.transferCreditTo(transferable, this.getValidAmount());			
			}
		}
		else {
			System.out.println("That is not a valid bank account number");
		}

	}
	
	
	
	

	@Override
	public String custUpdateMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do?");
		System.out.println("1 - Update Account Name");
		System.out.println("2 - Add Funds");
		System.out.println("3 - Remove Funds");
		System.out.println("4 - Transfer Funds");
		System.out.println("0 - Exit");
		char actionMenu = sc.next().toCharArray()[0];
		while (actionMenu != '1' && actionMenu != '2' && actionMenu != '3' 
				&& actionMenu != '4'  && actionMenu != '0'){
			System.out.println("1 - Update Account Name");
			System.out.println("2 - Add Funds");
			System.out.println("3 - Remove Funds");
			System.out.println("4 - Transfer Funds");
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
			actType = "Transfer Funds";
		}
		else if (actionMenu == '0') {
			actType = "Exit";
		}
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
		} else if (choice.equals("Transfer Funds")) {
			this.promptEnterTransferFunds();
		}
	}

	
	@Override
	public void tellerOpenAccount() { 
		this.setAccountName(this.promptEnterAccountName());
		this.addFunds(this.promptEnterAccountBalance());
		this.promptEnterAccountOverdraftIncrease();
	}
	
	@Override
	public String tellerUpdateMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do?");
		System.out.println("1 - Update Account Name");
		System.out.println("2 - Add Funds");
		System.out.println("3 - Remove Funds");
		System.out.println("4 - Transfer Funds");
		System.out.println("5 - Increase Overdraft");
		System.out.println("6 - Reduce Overdraft");
		System.out.println("0 - Exit");
		char actionMenu = sc.next().toCharArray()[0];
		while (actionMenu != '1' && actionMenu != '2' && actionMenu != '3' && actionMenu != '4' 
				&& actionMenu != '5' && actionMenu != '6'  && actionMenu != '0'){
			System.out.println("1 - Update Account Name");
			System.out.println("2 - Add Funds");
			System.out.println("3 - Remove Funds");
			System.out.println("4 - Increase Overdraft");
			System.out.println("5 - Reduce Overdraft");
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
			actType = "Transfer Funds";
		}
		else if (actionMenu == '5') {
			actType = "Increase OD";
		}
		else if (actionMenu == '6') {
			actType = "Decrease OD";
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
		} else if (choice.equals("Increase OD")) {
			this.promptEnterAccountOverdraftIncrease();
		} else if (choice.equals("Decrease OD")) {
			this.promptEnterAccountOverdraftdecrease();
		} else if (choice.equals("Transfer Funds")) {
			this.promptEnterTransferFunds();
		}
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
		return (this.getBalance() < 0);
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
		return "You are now in your overdraft on your " +getAccountName()+ " current account with overdraft credit of £ " + getAvailableFunds() + " remaining";
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
