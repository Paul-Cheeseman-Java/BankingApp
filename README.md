## General info
This project is a small banking application to help me understand/practice/implement some of the fundamentals of OOP and also for me to use jUnit for unit testing in a practical way.

It uses poloymorphism through inheritence (producing different actions depending on the accounts class) and uses an interface to enable a couple of the existing account types (and any future ones) to transfer money between themselves.

I used a factory design pattern for producing the account objects and as I only required one Bank object for the application I implemented it using a singleton design pattern.  


## Technologies
Project is created with:
* Java (v11)
* jUnit 5
* Eclipse



## Application
The default is for the bank to start with a single customer and teller, both of which have an id of 1.


### Users
The users are tellers and customers. Tellers can add new customers and also new tellers, they can also update elements of a customer account that a customer isn't able to (such as credit limits and overdrafts).

All customer accounts must be closed before a customer can be removed from the bank.



**Account Types**
_Basic_ - An account into which a customer can deposit money and remove money.

_Current_ - As per Basic but with an overdraft facility. A customer can open a current account but a teller needs to set/agree overdraft (which can only be reduced when the customer has appropriate funds). Money can be transfered between accounts of this type along with Credit accounts.

_Credit_ - An account which enables the customer to use credit to a level set/managed by a teller. A customer can open/name the account but cannot use it until a credit limit set/agreed by a teller. This account type allows money transfers.

_Saving_ - An account into which a customer can add/remove up to a limit of £40 a day (withdrawls being dependent on available funds). This account type allows money transfers. 

**Account Functionality**
When listed, accounts are sorted by name rather than by account number to make it easier for a customer to find.

Each of the account types log transactions and can produce a statement which is tailored to give details specific to that account type. The transactions on each statement are sorted so that the most recent transactions is at the to of the statement. 

Account types which allow money transfers allow them between a customers own accounts and between different customer accounts.

An account must have a zero balanace before it can be closed.


## Setup
***************************************************
Needs sorting
***************************************************
To run this application on Windows:
* Ensure you have Java installed and your PATH variable is setup correctly.
* Clone this GIT repo to your computer.
* In the Windows command prompt navigate into the cloned repo's top level Music_Library directory.
* In the command prompt enter: ******************************PUT IN THE CORRECT COMMANDS******************************

