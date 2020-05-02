package demo;


public class BankingApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Should all general msgs be static?
		//Have I been consistent with 'this'?
		//Do all comparisons need to be changed to Big Int?


		Credit test  = new Credit("Credit", 100);
		System.out.println("Balance: £" + test.getBalance());
		System.out.println("Credit Limit: £" + test.getCreditLimit());
		
		test.removeFunds(100.1);

		
	}
	

}
