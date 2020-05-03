package demo;

public interface Transferable {

	public boolean transferCreditTo(Transferable txfrObj, double amount);
	public boolean transferDebitTo(Transferable txfrObj, double amount);
	
	public boolean recieveTransferedCredit(double amount);
	public boolean recieveTransferedDebit(double amount);

}
