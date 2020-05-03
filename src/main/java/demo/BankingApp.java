package demo;


public class BankingApp {

	private static Transferable credit;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Should all general msgs be static?
		//Have I been consistent with 'this'?
		//Do all comparisons need to be changed to Big Int?
		//Consistent with use of decimal point for doubles?
		//Consistent in debit/credit/deposit/withdrawl

		Credit credit  = new Credit("Credit Test", 100);
		credit.removeFunds(90);
		System.out.println("Available Credit: " +credit.getAvailableFunds());

		Current current  = new Current("Current Test", 50);
		System.out.println("Current balance: " +current.getAvailableFunds());

		credit.transferCreditTo(current, 15);
		System.out.println("Available Credit: " +credit.getAvailableFunds());
		System.out.println("Current balance: " +current.getAvailableFunds());
		
		
	}
	

}
