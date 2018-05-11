package bank.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bank.bankpackage.Bank;
import bank.model.Account;
import bank.model.Person;
import bank.model.SpendingAccount;

public class BankTest 
{
	@Test
	public void addPersonTest()
	{
		Bank bank = new Bank();
		Person person = new Person("Vlad","1970807262511","1 Decembrie 1918");
		bank.addPerson(person);
		assertEquals("Add person", true, bank.getPerson().contains(person));
	}
	
	@Test
	public void deletePersonTest()
	{
		Bank bank = new Bank();
		Person person = new Person("Vlad","1970807262511","1 Decembrie 1918");
		bank.addPerson(person);
		bank.deletePerson(person);
		assertEquals("Delete person", false, bank.getPerson().contains(person));
	}
	
	@Test
	public void addAccount()
	{
		Bank bank = new Bank();
		Person person = new Person("Vlad","1970807262511","1 Decembrie 1918");
		bank.addPerson(person);
		Account account = new SpendingAccount("RO1234");
		bank.addAccount(person, account);
		assertEquals("Add account", true, bank.getAccount(person).contains(account));
	}
	
	@Test
	public void deleteAccount()
	{
		Bank bank = new Bank();
		Person person = new Person("Vlad","1970807262511","1 Decembrie 1918");
		bank.addPerson(person);
		SpendingAccount account = new SpendingAccount("RO1234");
		bank.addAccount(person, account);
		bank.deleteAccount(person, account);
		assertEquals("Delete account", false, bank.getAccount(person).contains(account));
	}
	
	@Test
	public void deposit()
	{
		Bank bank = new Bank();
		Person person = new Person("Vlad","1970807262511","1 Decembrie 1918");
		bank.addPerson(person);
		Account account = new SpendingAccount("RO1234");
		bank.addAccount(person, account);
		bank.deposit(person, account, 150);
		assertEquals(150, account.getBalance(), 0);
	}
	
	@Test
	public void withdraw()
	{
		Bank bank = new Bank();
		Person person = new Person("Vlad","1970807262511","1 Decembrie 1918");
		bank.addPerson(person);
		Account account = new SpendingAccount("RO1234");
		bank.addAccount(person, account);
		bank.deposit(person, account, 150);
		bank.withdraw(person, account, 100);
		assertEquals(50, account.getBalance(), 0);
	}
}
