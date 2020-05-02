package demo;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Saving extends Account {

	private static double dailyDepositLimit = 50.0;
	private static double dailyWithdrawlLimit = 50.0;
	
	private LocalDate lastTxnDate = LocalDate.now();
	private double dailyDepositTotal;
	private double dailywithdrawlTotal;
	
	
	public Saving(String name, double balance, double overdraft) {
		super(name, balance);
	}
	
	public Saving(String name, double balance) {
		super(name, balance);
	}
	
	
	
	@Override
	public boolean removeFunds(double amount) {
		if (fundsAvailable(amount)) {
			if (addToDailyWithdrawlTotal(amount)) {
				this.debitBalance(amount);
				return true;
			} else {
				System.out.println(getDailyWithDrawlLimitHitMsg(amount));
				return false;
			}
		} 
		else {
			System.out.println(getInsufficientFundsMsg());
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
	
	
	public boolean withinDailyWithdrawlLimit(double amount) {
		BigDecimal amountBigDec = new BigDecimal(amount);
		BigDecimal dailyWithdrawlLimitBigDec = new BigDecimal(Saving.dailyWithdrawlLimit);
		if (amountBigDec.compareTo(dailyWithdrawlLimitBigDec) < 1) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean addToDailyWithdrawlTotal(double amount){
		if (this.getLastTxnDate().isEqual(LocalDate.now())) {
			System.out.println("Trigger 1");
			if (this.withinDailyWithdrawlLimit(this.getDailyWithdrawlTotal() + amount)) {
				this.setDailyWithdrawlTotal(this.getDailyWithdrawlTotal() + amount);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			System.out.println("Trigger 2");
			this.setLastTxnDate(LocalDate.now());
			this.setDailyWithdrawlTotal(amount);
			return true;
		}
	}

	public String getDailyWithDrawlLimitHitMsg(double amount) {
		return "Unable to withdraw £" + amount + " because it will breach the daily withdrawl limit";
	}

	public String getDailyDepositHitMsg(double amount) {
		return "Unable to withdraw £" + amount + " because it will breach the daily deposit limit";
	}
	
	
	public static double getDailyDepositLimit() {
		return dailyDepositLimit;
	}

	public static void setDailyDepositLimit(double dailyDepositLimit) {
		Saving.dailyDepositLimit = dailyDepositLimit;
	}

	public static double getDailyWithdrawlLimit() {
		return dailyWithdrawlLimit;
	}

	public static void setDailyWithdrawlLimit(double dailywithdrawlLimit) {
		Saving.dailyWithdrawlLimit = dailywithdrawlLimit;
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

	public double getDailyWithdrawlTotal() {
		return dailywithdrawlTotal;
	}

	public void setDailyWithdrawlTotal(double dailywithdrawlTotal) {
		this.dailywithdrawlTotal = dailywithdrawlTotal;
	}
	

}
