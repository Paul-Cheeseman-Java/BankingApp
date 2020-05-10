package demo;


public class BankingApp {

	//Should all general msgs be static?
	//Have I been consistent with 'this'?
	//Do all comparisons need to be changed to Big Int?
	//Consistent with use of decimal point for doubles?
	//Consistent in debit/credit/deposit/withdrawal
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer bod = new Customer("Bod");

		Saving testSaving = new Saving("New Saving", 30);
		Current current = new Current("Current", 10);
		Credit credit = new Credit("Credit", 20);
		Saving saving = new Saving("Saving", 30);

		bod.addAccount(current);
		bod.addAccount(credit);
		bod.addAccount(saving);
		bod.addAccount(testSaving);
		bod.getAccount(3).removeFunds(20);
		
		bod.cutomerMenu();
		//System.out.println(bod.getAccount(1).selectAccountMenu(bod.getAccounts()));		
	}
	

}
