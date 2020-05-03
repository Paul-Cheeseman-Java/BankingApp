package demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Txn implements Comparable<Txn> {

	private LocalDateTime date;
	private double amount;
	private String type;
	private int accountNumber;
	private double balance;
	

	public Txn(double amount, String type, double balance, int accountNumber) {
		this.setDate(LocalDateTime.now());
		this.setType(type);
		this.setAmount(amount);
		this.setBalance(balance);
		this.setAccountNumber(accountNumber);
	}
	
	
	public int compareTo(Txn transaction) {  
		return date.compareTo(transaction.date);  
	}


	public String getStrDate() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(dateFormatter);
	}

	public String getStrTime() {
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return date.format(timeFormatter);
	}
	

	public LocalDateTime getDate() {
		return date;
	}
	
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
		
	
}
