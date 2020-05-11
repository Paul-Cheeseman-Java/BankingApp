package demo;


public class BankingApp {

	//Should all general msgs be static?
	//Have I been consistent with 'this'?
	//Do all comparisons need to be changed to Big Int?
	//Consistent with use of decimal point for doubles?
	//Consistent in debit/credit/deposit/withdrawal
	
	public static void main(String[] args) {

		Bank BankOCheese = new Bank("BankOCheese");
		Teller tel = new Teller(BankOCheese.getCustomers());

		Customer bod1 = new Customer("bod1");
		tel.addCustomer(bod1);
		
		//System.out.println("CustID is...: " +tel.promptEnterCustomerID());
		//Customer testBod = tel.obtainValidCustomer();
		
		bod1.openAccount();
		tel.openAccount();
		System.out.println("Bods Accounts........:");
		for (Account account : bod1.listAccounts()) {
			System.out.println("Account: " +account.getAccountName());
		}
		
		/*
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
		 * 		
		 */
	}
	

}
