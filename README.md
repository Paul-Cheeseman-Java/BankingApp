## General info
This project is a small banking application to help me understand the fundamentals of OOP and using jUnit for unit testing.  
It uses poloymorphism through inheritence (producing different actions depending on the accounts class) and uses an interface to enable a couple of the existing account types (and any future ones) to transfer money between themselves.

I used a factory design pattern for producing the account objects and as I only required one Bank object for the application I implemented it using a singleton design pattern.  


## Technologies
Project is created with:
* Java (v11)
* jUnit 5
	
## Setup
***************************************************
Needs sorting
***************************************************
To run this application on Windows:
* Ensure you have Java installed and your PATH variable is setup correctly.
* Clone this GIT repo to your computer.
* In the Windows command prompt navigate into the cloned repo's top level Music_Library directory.
* In the command prompt enter: ******************************PUT IN THE CORRECT COMMANDS******************************


## Application
The default is for the bank to start with a single customer and teller, both of which have an id of 1.


Users:
------
Tellers and Customer

Tellers can add new customers and also new tellers, they can also update elements of a customer account that a customer isn't able to (such as credit limits and overdrafts).


All customer accounts must be closed before a customer can be removed from the bank.



Account Types:
-------------- 
Basic - An account into which a customer can deposit money and remove money.

Current - As per Basic but with an overdraft facility. Customer can open an use account but teller needs to set/agree overdraft.

Credit - An account which enables the customer to use credit to a level set/managed by a teller. Customer can open but can use until a credi limit set/agreed 

Saving - An account into which a customer can add/remove up to a limit of £40 a day (dependent on available funds). Customer can open and transfer money into.


WHen listed, accounts are sorted by name rather than by account number to make it easier for a customer to find.

Each of the account types log transactions and can produce a statement which is tailored to give details specific to that account type. The transactions on each statement are sorted so that the most recent transactions is at the to of the statement. 

An account must have a zero balanace before it can be closed.




