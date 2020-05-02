package demo;


public class BankingApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Should all general msgs be static?
		//Have I been consistent with 'this'?

		Saving test = new Saving("Testacc", 20.00);
		System.out.println("Last txn date" +test.getLastTxnDate());

		test.removeFunds(5.0);	
		System.out.println("Balance: " +test.getBalance());
		test.removeFunds(15.0);	
		System.out.println("Balance: " +test.getBalance());
		System.out.println("Removing that 1p");
		test.removeFunds(0.01);	
		System.out.println("Balance: " +test.getBalance());

		System.out.println("Setting last date to previous day");
		test.setLastTxnDate(test.getLastTxnDate().minusDays(1));
		System.out.println("Last txn date" +test.getLastTxnDate());
		System.out.println("Removing that 1p (Shouldn't be able to)");
		test.removeFunds(00.01);	
		System.out.println("-------------------------");
		test.addFunds(10.0);	
		test.removeFunds(00.01);
	}
	

}
