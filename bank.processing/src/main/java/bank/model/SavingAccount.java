package bank.model;

public class SavingAccount extends Account
{
	private static final long serialVersionUID = 779878328940080373L;
	private boolean oneDeposit, oneWithdraw;
	public SavingAccount(String iban)
	{
		super(iban);
		super.setType("Saving");
		oneDeposit = oneWithdraw =  true;
	}
	
	@Override
	public boolean deposit(float amount)
	{
		if(amount >= 50000 && oneDeposit == true) 
		{
			oneDeposit = false;
			super.balance += amount;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean withdraw(float amount)
	{
		if(amount >= 50000 && oneWithdraw == true) 
		{
			oneWithdraw = false;
			super.balance -= amount;
			return true;
		}
		return false;
	}
}
