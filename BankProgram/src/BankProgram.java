import java.util.Scanner;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class BankProgram {
	private static Scanner input = new Scanner(System.in);
	private static ArrayList<Account> accountList = new ArrayList<Account>();

	// * DO NOT change anything in the main method *
	public static void main(String[] args) {
		int choice = -1;

		while (choice != 0) {

			switch (choice) {
			case 1:
				listAccounts();
				break;
			case 2:
				addAccount();
				break;
			case 3:
				depositMoney();
				break;
			case 4:
				withdrawMoney();
				break;
			case 5:
				deleteAccount();
				break;
			}

			displayMenu();
			choice = Integer.parseInt(input.nextLine());
		}

		System.out.println("\nThe program ends now. Bye!");
		input.close();
	}

	private static void displayMenu() {
		String line = "-----------------------------------------------------"
				+ "---------------------------------------------------------------";
		System.out.println(line);
		System.out.print(" 0 = Quit | 1 = List accounts | 2 = Add an account | "
				+ "3 = Deposit money | 4 = Withdraw money | 5 = Delete an account \n");
		System.out.println(line);
		System.out.print("Enter your choice: ");
	}

	// * NB! Edit only the methods below. DO NOT add any other methods! *

	private static void listAccounts() {
		System.out.print("\n** Account list **\n");
		DecimalFormat twoDecimals = new DecimalFormat("0.00");

		if (!accountList.isEmpty()) {
			for (Account a : accountList) {
				System.out.println("Number:  " + a.getAccountNumber() + " | " + "Balance :  "
						+ twoDecimals.format(a.getBalance()).replace(".", ",") + " euros");
			}

		}

	}

	private static void addAccount() {
		System.out.print("\n** Add an account **\n");

		System.out.print("Enter account number :");
		String acNumber = input.nextLine();
		Account x = findAccount(acNumber);
		if (acNumber.equals(x.getAccountNumber())) {
			System.out.println("Account not created. Account " + acNumber + " exists already!");
		} else {
			accountList.add(new Account(acNumber));
			System.out.println("Account created successfully!");
		}
	}

	// Finds an account in accountList by given account number.
	// Returns either a reference to the account object
	// OR null if the account is not found in accountList.

	private static Account findAccount(String accountNumber) {
		Account myAccount = new Account();
		for (Account c : accountList) {
			if (accountNumber.equals(c.getAccountNumber())) {
				return myAccount = c;
			}
		}
		return new Account();
	}

	private static void depositMoney() {
		System.out.print("\n** Deposit money to an account **\n");
		System.out.print("Enter account number: ");
		String a = input.nextLine();
		Account x = findAccount(a);
		if (a.equals(x.getAccountNumber())) {
			System.out.print("Enter the amount to be deposited: ");
			double b = Double.parseDouble(input.nextLine());
			x.deposit(b);
		}

		else {
			System.out.println("Account " + a + "  does not exist!");
		}

	}

	private static void withdrawMoney() {
		System.out.print("\n** Withdraw money from an account **\n");
		System.out.print("Enter account number: ");
		String z = input.nextLine();
		Account q = findAccount(z);
		if (z.equals(q.getAccountNumber())) {
			System.out.print("Enter the amount to be withdrawn: ");
			double t = Double.parseDouble(input.nextLine());
			q.withDraw(t);
		}

		else {
			System.out.println("Account " + z + " does not exist!");

		}

	}

	private static void deleteAccount() {
		System.out.print("\n** Delete an account **\n");

		System.out.print("Enter account number: ");
		String d = input.nextLine();
		if (!(accountList.isEmpty())) {
			Account dlt = findAccount(d);

			if (dlt.getAccountNumber().equals(d)) {
				accountList.remove(dlt);
				System.out.println("\nAccount deleted successfully!");
			}

			else {
				System.out.println("Nothing deleted. Account " + d + "does not exist!");
			}
		} else {
			System.out.println("Nothing deleted. Account " + d + " does not exist!");
		}

	}

}
// End

// End
class Account {
	// Fields
	String accountNumber;
	double balance = 0.0;

	// Constructor
	public Account(String accountNumber) {
		this.accountNumber = accountNumber;
		this.balance = 0.0;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	// Methods
	public Account() {
		this.accountNumber = accountNumber;
	}

	public boolean withDraw(double amount) {

		if (amount > 0 && amount < this.getBalance()) {

			this.balance = this.balance - amount;
			System.out.println("Withdrawal completed successfully!");

			return true;
		}

		else if (amount > this.balance) {
			this.balance = this.balance;
			System.out.println("Withdrawal not completed. Available balance is too low.");

			return false;
		}

		else {
			System.out.println("Cannot withdraw a negative amount!");

			return false;
		}

	}

	public void deposit(double amount) {
		if (amount > 0) {
			this.balance = this.balance + amount;
			System.out.println("Deposit completed successfully!");

		} else {
			System.out.println("Cannot deposit a negative amount!");
		}
	}

}