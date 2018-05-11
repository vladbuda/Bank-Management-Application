package bank.model;

public class SpendingAccount extends Account
{
	private static final long serialVersionUID = -6710512785675981468L;

	public SpendingAccount(String iban)
	{
		super(iban);
		super.setType("Spending");
	}
	
	public  boolean deposit(float amount)
	{
		super.balance += amount;
		return true;
	}
	
	public boolean withdraw(float amount)
	{
		if(amount <= balance)
		{
			super.balance -= amount;
			return true;
		}
		return false;
	}
}
