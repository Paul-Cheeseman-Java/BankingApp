package demo;

public class Basic extends Account {


	@Override
	public void custOpenAccount() { 
		this.setAccountName(this.promptEnterAccountName());
	}
	
	@Override
	public void custUpdateAccount() { 
		this.setAccountName(this.promptEnterAccountName());
	}

	
	@Override
	public void tellerOpenAccount() { 
		this.setAccountName(this.promptEnterAccountName());
		this.addFunds(this.promptEnterAccountBalance());
	}
	
	@Override
	public void tellerUpdateAccount() { 
		this.setAccountName(this.promptEnterAccountName());
	}
	

}
