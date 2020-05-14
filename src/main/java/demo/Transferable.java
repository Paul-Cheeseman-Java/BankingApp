package demo;

public interface Transferable {

	public boolean transferCreditTo(Transferable txfrObj, double amount);
	
	public boolean recieveTransferedCredit(double amount);
	
}
