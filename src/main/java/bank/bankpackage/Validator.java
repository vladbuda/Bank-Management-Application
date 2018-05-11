package bank.bankpackage;

import java.util.regex.Pattern;

import bank.model.Account;
import bank.model.Person;

public class Validator 
{
	private static final String CNP_PATTERN = "^[0-9]+$";
	private static final String IBAN_PATTERN = "[A-Z]{2}[0-9]{4}";
	
	public static boolean validatePerson(Person p)
	{
		if(p.getName().length() == 0) return false;
		else if (p.getAddress().length() == 0 ) return false;
		else
		{ 
			Pattern pattern = Pattern.compile(CNP_PATTERN);
			return (pattern.matcher(p.getCNP()).matches() && p.getCNP().length() == 13);
		}
	}
	
	public static boolean validateAccount(Account a)
	{
		Pattern pattern = Pattern.compile(IBAN_PATTERN);
		return pattern.matcher(a.getIban()).matches();
	}
}
