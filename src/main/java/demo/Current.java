package demo;


public class Current extends Account {

	private double overdraft;	//Default is 0.0
	
	public Current(String name, double balance, double overdraft) {
		super(name, balance);
		setOverdraft(overdraft);
	}
	
	public Current(String name, double balance) {
		super(name, balance);
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
		return (this.getBalance() + getOverdraft()) > amount;
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
			System.out.println(this.getInsufficientFundsMsg());
			return false;
		}
	}
	
	

	private String getInOverdraftMsg() {
		return "You are now in your overdraft on your " +getAccountName()+ " current account with £ " + getAvailableFunds() + " remaining";
	}
	
	private String getInsufficientFundsMsg() {
		return "You don't have sufficent funds in your " +getAccountName()+ " current account";
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
