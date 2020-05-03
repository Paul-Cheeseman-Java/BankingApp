package demo;


public class BankingApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Should all general msgs be static?
		//Have I been consistent with 'this'?
		//Do all comparisons need to be changed to Big Int?
		//Consistent with use of decimal point for doubles?

		Credit test  = new Credit("Credit", 100);
		//System.out.println("Balance: £" + test.getBalance());
		//System.out.println("Credit Limit: £" + test.getCreditLimit());
		
		System.out.println("0: " +test.paymentWithinCreditOwed(10));
		System.out.println("1: " +test.paymentWithinCreditOwed(100));
		System.out.println("2: " +test.paymentWithinCreditOwed(101));
		
		test.removeFunds(100.1);

		
	}
	

}
