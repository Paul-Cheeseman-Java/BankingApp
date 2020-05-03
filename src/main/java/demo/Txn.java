package demo;

import java.time.LocalDateTime;

public class Txn implements Comparable<Txn> {

	private LocalDateTime date;
	private double amount;
	private String type;
	
	public Txn(double amount, String type) {
		this.date = LocalDateTime.now();
		this.type = type;
		this.amount = amount;
		//this.transferType - 'Internal, Credit/Debit-In, Credit/Debit-Out'  
	}

	
	public int compareTo(Txn transaction) {  
		return date.compareTo(transaction.date);  
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


	@Override
	public String toString() {
		return date + " - " + type + " - " + amount;
	}

	
}
