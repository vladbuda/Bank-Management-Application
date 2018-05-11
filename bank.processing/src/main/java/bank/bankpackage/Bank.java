package bank.bankpackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import bank.model.Account;
import bank.model.Person;

public class Bank implements BankProc
{
	private HashMap<Person, HashSet<Account>> map;
	private int personSize, accountSize;
	public Bank()
	{
		map = new HashMap<Person, HashSet<Account>>();
		this.deserialize();
		Set<Person> persons = map.keySet();
		personSize = persons.size();
		for(Person person : persons)
			accountSize += map.get(person).size();
	}

	@Override
	public void addPerson(Person p) throws AssertionError
	{
		assert bankIsWellFormed();
		assert Validator.validatePerson(p);
		personSize++;
		map.put(p, new HashSet<Account>());
		assert map.keySet().size() == personSize;
		assert bankIsWellFormed();
	}

	@Override
	public void deletePerson(Person p) throws AssertionError
	{
		assert bankIsWellFormed();
		assert map.containsKey(p);
		personSize--;
		map.remove(p);
		assert map.keySet().size() == personSize;
		assert bankIsWellFormed();
	}

	@Override
	public void modifyPerson(Person p) throws AssertionError
	{
		assert bankIsWellFormed();
		assert map.containsKey(p);
		Set<Person> set = map.keySet();
		Person target = null;
		boolean found = false;
		for(Person person : set)
		{
			if(person.equals(p))
			{
				found = true;
				target = person;
				break;
			}
		}
		if(found == true)
		{
			target.setName(p.getName());
			target.setAddress(p.getAddress());
		}
		assert map.keySet().contains(p);
		assert bankIsWellFormed();
	}
	@Override
	public void addAccount(Person p, Account a) throws AssertionError
	{
		assert bankIsWellFormed();
		assert (map.containsKey(p) && Validator.validateAccount(a) && !checkIfExists(a));
		HashSet<Account> set = map.get(p);
		set.add(a);
		accountSize++;
		Set<Person> personSet = map.keySet();
		for(Person person : personSet)
		{
			if(person.equals(p)) 
				{
					a.addObserver(person);
					break;
				}
		}
		//assert accountSize == map.get(p).size();
		assert bankIsWellFormed();
	}

	@Override
	public void deleteAccount(Person p, Account a) throws AssertionError 
	{
		assert bankIsWellFormed();
		assert(map.containsKey(p) && map.get(p).contains(a));
		HashSet<Account> set = map.get(p);
		for(Account account : set)
		{
			if(account.equals(a))
			{
				account.notifyObservers();
				break;
			}
		}
		accountSize--;
		set.remove(a);
		assert accountSize == map.get(p).size();
		assert bankIsWellFormed();
	}
	
	@Override
	public int deposit(Person p, Account a, float amount) throws AssertionError
	{
		assert bankIsWellFormed();
		assert (map.containsKey(p) && map.get(p).contains(a) && amount > 0);
		HashSet<Account> accountSet = map.get(p);
		Iterator<Account> accountIterator = accountSet.iterator();
		int state = 0;
		float previousBalance;
		while(accountIterator.hasNext())
		{
			Account account = accountIterator.next();
			if(account.equals(a))
			{
				previousBalance = account.getBalance();
				if(account.deposit(amount) == true) 
					{
						state = 1;
						account.notifyObservers();
						previousBalance += amount;
						assert previousBalance == account.getBalance();
					}
			}
		}
		assert bankIsWellFormed();
		return state;
	}
	
	@Override
	public int withdraw(Person p, Account a, float amount) throws AssertionError
	{
		assert bankIsWellFormed();
		assert (map.containsKey(p) && map.get(p).contains(a) && amount > 0);
		HashSet<Account> accountSet = map.get(p);
		Iterator<Account> accountIterator = accountSet.iterator();
		int state = 0;
		float previousBalance;
		while(accountIterator.hasNext())
		{
			Account account = accountIterator.next();
			if(account.equals(a))
			{
				previousBalance = account.getBalance();
				if(account.getType().equals("Saving"))
				{
					if(account.withdraw(amount) == true) 
						{
							account.notifyObservers();
							state = 2;
							previousBalance -= amount;
							assert previousBalance == account.getBalance();
						}
				}
				else
				{
					if(account.withdraw(amount) == true) 
						{
							account.notifyObservers();
							state = 1;
							previousBalance -= amount;
							assert previousBalance == account.getBalance();
						}
				}
				break;
			}
		}
		assert bankIsWellFormed();
		return state;
	}
	
	public ArrayList<String[]> getPersons()
	{
		ArrayList<String[]> persons = new ArrayList<String[]>();
		Set<Person> set = map.keySet();
		Iterator<Person> iterator = set.iterator();
		while(iterator.hasNext())
		{
			Person p = iterator.next();
			persons.add(new String[] {""+p.getName(),""+p.getCNP(), ""+p.getAddress()});
		}
		return persons;
	}

	public ArrayList<String[]> getAccounts()
	{
		ArrayList<String[]> accounts = new ArrayList<String[]>();
		Set<Person> set = map.keySet();
		Iterator<Person> iterator = set.iterator();
		HashSet<Account> accountSet= null;
		while(iterator.hasNext())
		{
			Person p = iterator.next();
			accountSet = map.get(p);
			Iterator<Account> accountIterator = accountSet.iterator();
			while(accountIterator.hasNext())
			{
				Account a = accountIterator.next();
				accounts.add(new String[] {""+p.getName(),""+a.getIban(),""+a.getType(),""+a.getBalance()});
			}
				
		}
		return accounts;
	}
	
	public Account getAccountIBAN(Person p, String iban)
	{
		HashSet<Account> accounts = getAccount(p);
		for(Account account : accounts)
			if(account.getIban().equals(iban)) return account;
		return null;
	}
	
	public Set<Person> getPerson()
	{
		return map.keySet();
	}
	
	public HashSet<Account> getAccount(Person p)
	{
		return map.get(p);
	}
	
	public void serialize()
	{
		 try
         {
                FileOutputStream fos = new FileOutputStream("hashmap.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(map);
                oos.close();
                fos.close();
         }
		 catch(IOException e)
		 {
			 e.printStackTrace();
		 }
	}
	
	@SuppressWarnings("unchecked")
	public void deserialize()
	{
		try
	      {
	         FileInputStream fis = new FileInputStream("hashmap.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis);
	         map = (HashMap<Person, HashSet<Account>>) ois.readObject();
	         ois.close();
	         fis.close();
	      }
		catch(IOException e)
	      {
	         e.printStackTrace();
	      }
		catch(ClassNotFoundException e)
	      {
	         e.printStackTrace();
	      }
	}
	private boolean bankIsWellFormed()
	{
		Set<Person> personSet = map.keySet();
		for(Person p : personSet)
		{
			HashSet<Account> accountSet = map.get(p);
			for(Account a : accountSet)
				if(a.getBalance() < 0) return false;
		}
		return true;
	}
	
	private boolean checkIfExists(Account a)
	{
		Set<Person> personSet = map.keySet();
		for(Person p : personSet)
		{
			HashSet<Account> accountSet = map.get(p);
			if(accountSet.contains(a)) return true;
		}
		return false;
	}
}
