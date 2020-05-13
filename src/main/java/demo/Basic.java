package demo;

import java.util.Scanner;

public class Basic extends Account {


	@Override
	public void custOpenAccount() { 
		this.setAccountName(this.promptEnterAccountName());
	}
	

	@Override
	public String custUpdateMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do?");
		System.out.println("1 - Update Account Name");
		System.out.println("2 - Add Funds");
		System.out.println("3 - Remove Funds");
		System.out.println("0 - Exit");
		char actionMenu = sc.next().toCharArray()[0];
		while (actionMenu != '1' && actionMenu != '2' && actionMenu != '3' && actionMenu != '4' 
				&& actionMenu != '5'  && actionMenu != '0'){
			System.out.println("1 - Update Account Name");
			System.out.println("2 - Add Funds");
			System.out.println("3 - Remove Funds");
			System.out.println("0 - Exit");
			actionMenu = sc.next().toCharArray()[0];
		}
		String actType = "";
		if (actionMenu == '1') {
			actType = "Update Name";
		}
		else if (actionMenu == '2') {
			actType = "Add Funds";
		}
		else if (actionMenu == '3') {
			actType = "Remove Funds";
		}
		else if (actionMenu == '0') {
			actType = "Exit";
		}
		return actType;
	}
	
	@Override
	public void custUpdateAccount() { 
		String choice = this.tellerUpdateMenu();
		if (choice.equals("Update Name")) {
			this.setAccountName(this.promptEnterAccountName());
		} else if (choice.equals("Add Funds")) {
			this.promptEnterAccountAddFunds();
		} else if (choice.equals("Remove Funds")) {
			this.promptEnterAccountRemoveFunds();
		}
	}

	
	@Override
	public void tellerOpenAccount() { 
		this.setAccountName(this.promptEnterAccountName());
		this.addFunds(this.promptEnterAccountBalance());
	}
	
	@Override
	public String tellerUpdateMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do?");
		System.out.println("1 - Update Account Name");
		System.out.println("2 - Add Funds");
		System.out.println("3 - Remove Funds");
		System.out.println("0 - Exit");
		char actionMenu = sc.next().toCharArray()[0];
		while (actionMenu != '1' && actionMenu != '2' && actionMenu != '3' && actionMenu != '4' 
				&& actionMenu != '5'  && actionMenu != '0'){
			System.out.println("1 - Update Account Name");
			System.out.println("2 - Add Funds");
			System.out.println("3 - Remove Funds");
			System.out.println("0 - Exit");
			actionMenu = sc.next().toCharArray()[0];
		}
		String actType = "";
		if (actionMenu == '1') {
			actType = "Update Name";
		}
		else if (actionMenu == '2') {
			actType = "Add Funds";
		}
		else if (actionMenu == '3') {
			actType = "Remove Funds";
		}
		else if (actionMenu == '0') {
			actType = "Exit";
		}
		return actType;
	}
	
	@Override
	public void tellerUpdateAccount() { 
		String choice = this.tellerUpdateMenu();
		if (choice.equals("Update Name")) {
			this.setAccountName(this.promptEnterAccountName());
		} else if (choice.equals("Add Funds")) {
			this.promptEnterAccountAddFunds();
		} else if (choice.equals("Remove Funds")) {
			this.promptEnterAccountRemoveFunds();
		} 
	}
	

}
