package bank.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bank.bankpackage.Bank;
import bank.model.Account;
import bank.model.Person;
import bank.model.SavingAccount;
import bank.model.SpendingAccount;

public class GUI 
{
	private JTable personsTable, accountsTable;
	private DefaultTableModel personsModel, accountsModel;
	private JScrollPane personsScroll, accountsScroll;
	private Object[][] personsData, accountsData;
	private String[] personsHeader, accountsHeader;
	
	private JFrame frame, personTableFrame, accountTableFrame;
	private JPanel personPanel, accountPanel, operationPanel;
	private JTabbedPane tabPane;
	private JTextField nameField, cnpField, addressField, cnpAccountField, ibanAccountField, cnpOperationField, ibanOperationField, amountField;
	private JButton insertPerson, updatePerson, deletePerson, showPersonTable, insertAccount, updateAccount, deleteAccount, showAccountTable, deposit, withdraw;
	private JComboBox<String> typeBox;
	private Bank bank;
	private ArrayList<String[]> accounts;
	private ArrayList<String[]> persons;
	
	public GUI()
	{
		bank = new Bank();
		
		frame = new JFrame("Bank");
		
		personPanel = new JPanel();
		accountPanel = new JPanel();
		operationPanel = new JPanel();
		
		tabPane = new JTabbedPane();
		
		nameField = new JTextField("Name");
		cnpField = new JTextField("CNP");
		addressField = new JTextField("Address");
		
		insertPerson = new JButton("Insert");
		updatePerson = new JButton("Update");
		deletePerson = new JButton("Delete");
		showPersonTable = new JButton("Show Table");
		
		nameField.setBounds(30, 50, 200, 30);
		cnpField.setBounds(30, 105, 200, 30);
		addressField.setBounds(30, 155, 200, 30);
		
		insertPerson.setBounds(250, 50, 100, 30);
		updatePerson.setBounds(250, 105, 100, 30);
		deletePerson.setBounds(250, 155, 100, 30);
		showPersonTable.setBounds(370, 50, 120, 135);
		
		insertPerson.setFocusable(false);
		updatePerson.setFocusable(false);
		deletePerson.setFocusable(false);
		showPersonTable.setFocusable(false);
		
		personPanel.add(nameField);
		personPanel.add(cnpField);
		personPanel.add(addressField);
		
		personPanel.add(insertPerson);
		personPanel.add(updatePerson);
		personPanel.add(deletePerson);
		personPanel.add(showPersonTable);
		
		personPanel.setVisible(true);
		personPanel.setLayout(null);
	
		cnpAccountField = new JTextField("CNP");
		ibanAccountField = new JTextField("IBAN");
		typeBox = new JComboBox<String>(new String[] {"Spending Account", "Saving Account"});
		
		typeBox.setEditable(true);
		typeBox.setSelectedItem("Account Type");
		typeBox.setEditable(false);
		  
		insertAccount = new JButton("Insert");
		updateAccount = new JButton("Update");
		deleteAccount = new JButton("Delete");
		showAccountTable = new JButton("Show Table");
		
		cnpAccountField.setBounds(30, 50, 200, 30);
		ibanAccountField.setBounds(30, 105, 200, 30);
		typeBox.setBounds(30, 155, 200, 30);
		
		insertAccount.setBounds(250, 75, 100, 30);
		updateAccount.setBounds(250, 105, 100, 30);
		deleteAccount.setBounds(250, 135, 100, 30);
		showAccountTable.setBounds(370, 50, 120, 135);
		
		accountPanel.add(cnpAccountField);
		accountPanel.add(ibanAccountField);
		accountPanel.add(typeBox);
		
		accountPanel.add(insertAccount);
		accountPanel.add(updateAccount);
		accountPanel.add(deleteAccount);
		accountPanel.add(showAccountTable);
		
		accountPanel.setVisible(true);
		accountPanel.setLayout(null);
		
		
		cnpOperationField = new JTextField("CNP");
		ibanOperationField = new JTextField("IBAN");
		amountField = new JTextField("Amount");
		
		deposit = new JButton("Deposit");
		withdraw = new JButton("Withdraw");
		
		cnpOperationField.setBounds(30, 50, 200, 30);
		ibanOperationField.setBounds(30, 105, 200, 30);
		amountField.setBounds(30, 155, 200, 30);
		
		deposit.setBounds(280, 50, 150, 30);
		withdraw.setBounds(280, 105, 150, 30);
		
		operationPanel.add(cnpOperationField);
		operationPanel.add(ibanOperationField);
		operationPanel.add(amountField);
		
		operationPanel.add(deposit);
		operationPanel.add(withdraw);
		
		operationPanel.setVisible(true);
		operationPanel.setLayout(null);
		
		tabPane.addTab("Person", personPanel);
		tabPane.addTab("Account", accountPanel);
		tabPane.addTab("Operation", operationPanel);
		
		frame.setSize(530, 330);
		frame.setResizable(false);
		frame.add(tabPane);
		frame.setVisible(true);;
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		personTableFrame = new JFrame("Persons");
		accountTableFrame = new JFrame("Accounts");
		
		personsData = new Object[0][0];
		personsHeader = new String[] {"name","cnp","address"};
		accountsData = new Object[0][0];
		accountsHeader = new String[] {"name","iban","type","balance"};
		
		personsModel = new DefaultTableModel(personsData, personsHeader);
		accountsModel = new DefaultTableModel(accountsData, accountsHeader);
		
		personsTable = new JTable(personsModel);
		accountsTable = new JTable(accountsModel);
		
		personsTable.setCellSelectionEnabled(false);
		personsTable.setColumnSelectionAllowed(false);
		personsTable.setRowSelectionAllowed(true);
		
		accountsTable.setCellSelectionEnabled(false);
		accountsTable.setColumnSelectionAllowed(false);
		accountsTable.setRowSelectionAllowed(true);
		
		personsScroll = new JScrollPane();
		accountsScroll = new JScrollPane();
		
		personsScroll.setBounds(30, 50, 500, 275);
		accountsScroll.setBounds(30, 50, 500, 275);
		personsScroll.setViewportView(personsTable);
		accountsScroll.setViewportView(accountsTable);
		
		personTableFrame.setSize(575, 400);
		accountTableFrame.setSize(575, 400);
		
		personTableFrame.add(personsScroll);
		accountTableFrame.add(accountsScroll);
		
		personTableFrame.setLayout(null);
		accountTableFrame.setLayout(null);
		
		ActionHandler handler = new ActionHandler();
		showPersonTable.addActionListener(handler);
		showAccountTable.addActionListener(handler);
		
		insertPerson.addActionListener(handler);
		updatePerson.addActionListener(handler);
		deletePerson.addActionListener(handler);
		
		insertAccount.addActionListener(handler);
		updateAccount.addActionListener(handler);
		deleteAccount.addActionListener(handler);
		
		updateAccount.setVisible(false);
		
		deposit.addActionListener(handler);
		withdraw.addActionListener(handler);
		
		//bank.deserialize();
		frame.addWindowListener(new WindowAdapter()
				{
					@Override
					public void windowClosing(WindowEvent e)
					{
						bank.serialize();
					}
				});
		accountsTable.addMouseListener(new MouseAdapter() 
			{
				public void mouseClicked(MouseEvent e)
				{
					JTextArea textArea = new JTextArea(5, 5);
					int row = accountsTable.rowAtPoint(e.getPoint());
					for(String s : accounts.get(row))
						textArea.setText(textArea.getText()+s+"\n");
					JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Clipboard", JOptionPane.NO_OPTION, null);
				}
			});
		personsTable.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e)
			{
				JTextArea textArea = new JTextArea(5, 5);
				int row = personsTable.rowAtPoint(e.getPoint());
				for(String s : persons.get(row))
					textArea.setText(textArea.getText()+s+"\n");
				JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Clipboard", JOptionPane.NO_OPTION, null);
			}
		});
	}
	
	private void refreshPersons()
	{
		persons = bank.getPersons();
		personsModel.setRowCount(0);
		for(String[] s : persons)
			personsModel.addRow(s);
	}
	
	private void refreshAccounts()
	{
		accounts = bank.getAccounts();
		accountsModel.setRowCount(0);
		for(String[] s : accounts)
			accountsModel.addRow(s);
	}
	
	public static void notifyUser(String message, int severity)
	{
		String title;
		if (severity == 1) title = "Notification";
		else title = "Invalid format";
		JOptionPane.showMessageDialog(null, message, title, severity, null);
	}
	
	private class ActionHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == showPersonTable) 
			{
				personTableFrame.setVisible(true);
				refreshPersons();
			}
			else if (e.getSource() == showAccountTable) 
			{
				accountTableFrame.setVisible(true);
				refreshAccounts();
			}
			else if(e.getSource() == insertPerson)
			{
				String name, cnp, address;
				name = nameField.getText();
				cnp = cnpField.getText();
				address = addressField.getText();
				try
				{
					bank.addPerson(new Person(name, cnp, address));
					refreshPersons();
					nameField.setText("Name");
					cnpField.setText("CNP");
					addressField.setText("Address");
				}
				catch(AssertionError exception)
				{
					notifyUser("Input should not have blank spaces and CNP must have exactly 13 digits",0);
				}
			}
			else if(e.getSource() == updatePerson)
			{
				String name, cnp, address;
				name = nameField.getText();
				cnp = cnpField.getText();
				address = addressField.getText();
				try
				{
					bank.modifyPerson(new Person(name, cnp, address));
					refreshPersons();
					nameField.setText("Name");
					cnpField.setText("CNP");
					addressField.setText("Address");
				}
				catch(AssertionError exception)
				{
					notifyUser("The person does not exist",0);
				}
			}
			else if(e.getSource() == deletePerson)
			{
				String cnp = cnpField.getText();
				try
				{
					bank.deletePerson(new Person("dummy", cnp, "dummy"));
					refreshPersons();
					nameField.setText("Name");
					cnpField.setText("CNP");
					addressField.setText("Address");
				}
				catch(AssertionError exception)
				{
					notifyUser("The person does not exist",0);
				}
			}
			else if(e.getSource() == insertAccount)
			{
				String cnp, iban;
				cnp = cnpAccountField.getText();
				iban = ibanAccountField.getText();
				if(typeBox.getSelectedItem().equals("Saving Account"))
					{
						try
						{
							bank.addAccount(new Person("dummy", cnp, "dummy"), new SavingAccount(iban));
							refreshAccounts();
							cnpAccountField.setText("CNP");
							ibanAccountField.setText("IBAN");
							typeBox.setSelectedItem("Account Type");
						}
						catch(AssertionError exception)
						{
							notifyUser("The person does not exist or IBAN is invalid (2 capital letters followed by 4 digits) or already exists",0);
						}
					}
				else 
					{
						try
						{
							bank.addAccount(new Person("dummy", cnp, "dummy"), new SpendingAccount(iban));
							refreshAccounts();
							cnpAccountField.setText("CNP");
							ibanAccountField.setText("IBAN");
							typeBox.setSelectedItem("Account Type");
						}
						catch(AssertionError exception)
						{
							notifyUser("The person does not exist or IBAN is invalid (2 capital letters followed by 4 digits)",0);
						}
					}
			}
			else if(e.getSource() == deleteAccount)
			{
				String iban, cnp;
				iban = ibanAccountField.getText();
				cnp = cnpAccountField.getText();
				try
				{
					Account account = bank.getAccountIBAN(new Person("dummy", cnp, "dummy"), iban);
					if(account.getType() == "Saving")
						bank.deleteAccount(new Person("dummy",cnp,"dummy"), new SavingAccount(iban));
					else
						bank.deleteAccount(new Person("dummy",cnp,"dummy"), new SpendingAccount(iban));
					refreshAccounts();
					notifyUser("The account "+iban+" has been successfully deleted", 1);
					cnpAccountField.setText("CNP");
					ibanAccountField.setText("IBAN");
					typeBox.setSelectedItem("Account Type");
				}
				catch(AssertionError exception)
				{
					notifyUser("The person does not exist or IBAN is invalid (it must start with 2 capital letters followed by 4 digits) or does not exist",0);
				}
			}
			else if(e.getSource() == deposit)
			{
				String iban, cnp;
				Float amount;
				int state;
				iban = ibanOperationField.getText();
				cnp = cnpOperationField.getText();
				amount = Float.parseFloat(amountField.getText());
				try
				{
					Account account = bank.getAccountIBAN(new Person("dummy", cnp, "dummy"), iban);
					if(account.getType() == "Saving")
						state = bank.deposit(new Person("dummy",cnp,"dummy"), new SavingAccount(iban), amount);
					else
						state = bank.deposit(new Person("dummy",cnp,"dummy"), new SpendingAccount(iban), amount);
					if(state == 1)
					{
						notifyUser("You successfully made a deposit of "+amount, 1);
						refreshAccounts();
						ibanOperationField.setText("IBAN");
						cnpOperationField.setText("CNP");
						amountField.setText("Amount");
					}
					else notifyUser("Below the minimum amount for a Saving Account (50000) or you have already made a deposit in this Saving Account (only one allowed)", 1);
					
				}
				catch(AssertionError exception)
				{
					notifyUser("The person does not exist or IBAN is invalid (it must start with 2 capital letters followed by 4 digits) or does not exist",0);
				}
			}
			else if(e.getSource() == withdraw)
			{
				String iban, cnp;
				Float amount;
				int state;
				iban = ibanOperationField.getText();
				cnp = cnpOperationField.getText();
				amount = Float.parseFloat(amountField.getText());
				try
				{
					Account account = bank.getAccountIBAN(new Person("dummy", cnp, "dummy"), iban);
					if(account.getType() == "Saving")
						state = bank.withdraw(new Person("dummy",cnp,"dummy"), new SavingAccount(iban), amount);
					else
						state = bank.withdraw(new Person("dummy",cnp,"dummy"), new SpendingAccount(iban), amount);
					if(state == 0) notifyUser("Insufficient funds or you have already withdrawn from a Saving Account (only one withdraw allowed)", 1);
					else
					{
						if(state == 1) notifyUser("You successfully withdrew "+amount, 1);
						else notifyUser("You successfully withdrew "+amount+" with 2% interest, resulting in "+(amount+(2*amount/100)), 1);
						refreshAccounts();
						ibanOperationField.setText("IBAN");
						cnpOperationField.setText("CNP");
						amountField.setText("Amount");
					}
				}
				catch(AssertionError exception)
				{
					notifyUser("The person does not exist or IBAN is invalid (it must start with 2 capital letters followed by 4 digits) or does not exist",0);
				}
			}
		}
	}
}
