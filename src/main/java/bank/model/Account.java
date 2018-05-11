package bank.model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Account implements Serializable
{
	private static final long serialVersionUID = -418924355277270954L;
	private String iban, type;
	protected float balance;
	private ArrayList<Observer> observers;
	
	public Account(String iban)
	{
		this.iban = iban;
		observers = new ArrayList<Observer>();
	}
	
	public String getIban()
	{
		return this.iban;
	}
	
	public float getBalance()
	{
		return this.balance;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getType()
	{
		return type;
	}
	
	public abstract boolean deposit(float amount);
	
	public abstract boolean withdraw(float amount);
	
	public void addObserver(Observer o)
	{
		observers.add(o);
	}
	
	public void notifyObservers()
	{
		for(Observer o : observers) o.update(this);
	}
	
	@Override
	public int hashCode()
	{
		return iban.hashCode();
	}
	
	@Override
	public boolean equals(Object a)
	{
		Account account = (Account) a;
		return this.iban.equals(account.iban);
	}
	
	@Override
	public String toString()
	{
		return String.format("IBAN: %s, balance: %.2f", iban, balance);
	}

	
}
