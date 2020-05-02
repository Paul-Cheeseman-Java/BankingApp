package demo;

import java.math.BigDecimal;

public class Credit extends Account {

	private double creditLimit;
	
	public Credit(String name, double creditLimit) {
		super(name);
		setCreditLimit(creditLimit);
		setBalance(creditLimit);
	}
	
	public Credit(String name) {
		super(name);
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
			//The removeFunds() in Account has a message if not enough funds
			this.setCreditLimit(this.getCreditLimit() - amount);
		}
	}
	
	
	@Override
	public boolean fundsAvailable(double amount) {
		
		//Changing to Big Int for comparison 
		//https://stackoverflow.com/questions/25160375/comparing-double-values-for-equality-in-java		
		//https://howtodoinjava.com/java/basics/correctly-compare-float-double/
		BigDecimal amountBigDec = new BigDecimal(amount);
		BigDecimal creditLimitBigDec = new BigDecimal(this.getCreditLimit());
		BigDecimal balanceBigDec = new BigDecimal(this.getBalance());
	
		if (amountBigDec.compareTo(balanceBigDec) < 1) {
			return true;
		}
		else {
			return false;
		}
	}

	
	public boolean paymentWithinCreditOwed(double amount){
		BigDecimal amountBigDec = new BigDecimal(amount);
		BigDecimal balanceBigDec = new BigDecimal(this.getBalance());
		if (amountBigDec.compareTo(balanceBigDec) < 1) {
			return true;
		} else {
			return false;
		}
		
		
	}
	

	
	
	//Paying off credit used
	@Override
	public boolean addFunds(double amount) {
		if (this.paymentWithinCreditOwed(amount)) {
			this.creditBalance(amount);
			//update points
			return true;
		}
		else {
			return false;
		}
	}
	
	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}


}
