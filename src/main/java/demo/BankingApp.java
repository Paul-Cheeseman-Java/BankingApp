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
		Current current1  = new Current("Current Test", 100);
		Saving test = new Saving("test", 40);
		Credit credit  = new Credit("Credit Test", 100);
		Current current  = new Current("Current Test", 100);

		current.addFunds(1);
		current.removeFunds(1.0);
		current.addFunds(22.00);		
		current.removeFunds(22.00);		
		current.addFunds(333.00);			
		current.removeFunds(333.00);			
		current.addFunds(4444.00);			
		current.removeFunds(4444.00);			
		current.addFunds(55555.00);			
		current.removeFunds(55555.00);			
		current.addFunds(666666.00);			
		current.removeFunds(666666.00);			
		
		credit.transferDebitTo(current, 20.0);
		current.addFunds(30);
		credit.transferCreditTo(current, 20.0);
		current.removeFunds(15);
		current.transferCreditTo(credit, 7);
		current.getStatement();
	}
	

}
