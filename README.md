## General info
This project is a small Java banking application to help me understand/practice/implement some of the fundamentals of OOP and is also an opportunity for me to use JUnit for unit testing.

The code uses uses poloymorphism through inheritence (producing different actions depending on the accounts class) and uses an interface to enable a couple of the existing account types (and any future ones) to transfer money between themselves. Numerous unit tests for each class have been delveloped in JUnit.


## Technologies
Project is created with:
* Java (v11)
* JUnit 5
* Eclipse IDE


## Application
The application is a bank which is able to manage it tellers and customers. The customers have a range of accounts types to choose from (detailed below) and a single customer can have multiple accounts. 

Tellers can add new customers and also new tellers, they can also update elements of a customer account that a customer isn't able to (such as change credit and overdraft limits).

For demo purposes this has been set as a very trusting bank, enabling customers to add funds to their accounts out of thin air (functionality I'm sure we'd all like to see other banks follow!). This makes it easier to see/explore some of the functionality without having to switch back and forth between teller/customer roles. 

The bank to start with a customer (who has no accounts) and a teller, both of which have an id of 1.

All customer accounts must be closed before a customer can be removed from the bank.



**Account Types**

_Basic_ - An account into which a customer can deposit money and remove money.

_Current_ - As per Basic but with an overdraft facility. A customer can open a current account but a teller needs to set/agree overdraft (which can only be reduced when the customer has appropriate funds). Money can be transfered between other accounts of this type and also with Credit accounts.

_Credit_ - An account which enables the customer to use credit to a level set/managed by a teller. A customer can open/name the account but cannot use it until a credit limit set/agreed by a teller. Money can be transfered between other accounts of this type and also with Current accounts.

_Saving_ - An account into which a customer can add/remove up to a limit of £40 a day (withdrawls being dependent on available funds). This account type allows money transfers. 

**Account Functionality**

When listed, accounts are sorted by name rather than by account number to make it easier for a customer to find.

Each of the account types log transactions and can produce a statement which is tailored to give details specific to that account type. The transactions on each statement are sorted so that the most recent transactions is at the to of the statement. 

Account types which allow money transfers allow them between a customers own accounts and between different customer accounts.

An account must have a zero balance before it can be closed.


## Setup
To run this application on Windows:
* Ensure you have Java installed and your PATH variable is setup correctly.
* Clone this GIT repo to your computer.
* In the Windows command prompt navigate into the cloned repo's top level directory.
* Type in java -jar BankingApp.jar

