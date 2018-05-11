package bank.model;

import java.io.Serializable;
import java.util.Observable;

import bank.gui.GUI;

public class Person implements Observer, Serializable
{
	private String name, cnp, address;
	
	public Person(String name, String cnp, String address)
	{
		this.name = name;
		this.cnp = cnp;
		this.address = address;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getCNP()
	{
		return this.cnp;
	}
	
	public void setCNP(String cnp)
	{
		this.cnp = cnp;
	}
	
	@Override
	public int hashCode()
	{
		return this.cnp.hashCode();
	}
	
	@Override
	public boolean equals(Object o)
	{
		Person p = (Person) o;
		return this.cnp.equals(p.cnp);
	}
	
	@Override
	public String toString()
	{
		return String.format("%s, %s, %s", name, cnp, address);
	}

	@Override
	public void update(Object o) 
	{
		GUI.notifyUser("An action was perfomed on account "+((Account) o).getIban()+" belonging to "+this.name, 1);
	}
}
