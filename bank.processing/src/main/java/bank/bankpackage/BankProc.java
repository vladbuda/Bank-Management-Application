package bank.bankpackage;

import bank.model.Account;
import bank.model.Person;

public interface BankProc 
{
 /**This method adds a new person
  * Preconditions: New person's details must obey the syntax
  * (name and address field must not be empty and CNP must contain exactly 13 digits) 
  * Postconditions: The new person will be added to the bank's database and its size
  * will be incremented by 1
 * @param p the person to be added
 */
void addPerson(Person p);

 /**This method deletes and existing person
  * Precondition: The person must exist in the database
  * Postcondition: The person will be removed from the bank's database and its size
  * will be decremented by 1
 * @param p the person to be deleted
 */
void deletePerson(Person p);

 /**This method updates the details of an existing person
  * Precondition: The person must exist in the database
  * Postcondition: The existing person will have its details modified (name, address)
 * @param p details of an existing person
 */
void modifyPerson(Person p);
 
 /**This method adds a new account to an existing person
  * Precondition: The person must exist in the database and the account must obey the syntax
  * (IBAN must start with 2 capital letters and be followed by 4 digits)
  * Postcondition: The account will be added to that person and the number of accounts in the bank
  * will be incremented by 1
 * @param p the holder of the account
 * @param a the account to be added
 */
void addAccount(Person p, Account a);

 /**This method deletes and existing account from its holder set
  * Precondition: The person must exist in the database and be the holder of the account
  * Postcondition: The account will be deleted from the holder's account set and the number of accounts
  * will be decremented by 1
 * @param p the holder of the account 
 * @param a the account to be deleted
 */
void deleteAccount(Person p, Account a);

/**This method deposits an amount to an account
 * Precondition: The person must exist and be the holder of the specified account. Amount must be a positive number
 * Postcondition: The amount will be added to the account's balance
 * @param p the account holder
 * @param a account details
 * @param amount the amount to be deposited
 * @return true in case of success or false otherwise
 */
int deposit(Person p, Account a, float amount);

/**This method withdraws the specified amount from an account
 * Precondition: The person must exist and be the holder of the specified account. Amount must be a positive number
 * Postcondition: The amount will be subtracted from the account's balance
 * @param p the account holder
 * @param a account details
 * @param amount the amount to be withdrawn
 * @return true in case of success or false otherwise
 */
int withdraw(Person p, Account a, float amount);

}
