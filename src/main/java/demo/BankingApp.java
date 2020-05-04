package demo;


public class BankingApp {

	//Should all general msgs be static?
	//Have I been consistent with 'this'?
	//Do all comparisons need to be changed to Big Int?
	//Consistent with use of decimal point for doubles?
	//Consistent in debit/credit/deposit/withdrawl
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer bod = new Customer("Bod");

		Current current = new Current("Current", 100);
		Credit credit = new Credit("Credit", 100);
		Saving saving = new Saving("Saving", 1);

		bod.addAccounts(current);
		bod.addAccounts(credit);
		bod.addAccounts(saving);

		for (Account account : bod.listAccounts()) {
			System.out.println("Account: " +Customer.getFormattedCustomerNumber(account.getAccountNumber()) + ", Class Type: " +account.getClass().getSimpleName());
		}
				
	
		bod.getAccount(2).makeTransferTo(current, "Credit", 20.00);
		bod.getAccount(1).makeTransferTo(credit, "Credit", 90.00);
		bod.getAccount(1).removeFunds(1000);
		//isTransferable
		//Transfer


		
	}
	

}
