package application;

import java.text.DecimalFormat;

public class ChartOfAccounts {
    private final Directory accounts;
    private final Database database;

    private final DecimalFormat df;

    private int assetAccountNumber;
    private int liabilityAccountNumber;
    private int equityAccountNumber;

    public ChartOfAccounts(Database database) {
        this.df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern("#,##0.00;(#,##0.00)");

        this.accounts = new Directory();
        this.database = database;

        this.assetAccountNumber = 1000;
        this.liabilityAccountNumber = 2000;
        this.equityAccountNumber = 3000;
    }

//    Print a formatted and comprehensive Chart of Accounts REMOVE
    public void printChartOfAccounts() {
        System.out.println("* Chart of Accounts *");

//        Print Assets
        System.out.println("\nASSETS");
        database.printByAccountType("Asset");

//        Print Liabilities
        System.out.println("\nLIABILITIES");
        database.printByAccountType("Liability");

//        Print Equities
        System.out.println("\nEQUITIES");
        database.printByAccountType("Equity");
    }

//    Search a hash map of all accounts and return true if found
    public boolean checkAccountExists(int accountNumber) {
        return accounts.getDirectory().containsKey(accountNumber);
    }

    // !!! Add check for duplicate accounts
//    Add an account to the relevant account type list
    public void add(String name, String accountType) {
//        Add Asset account
        if (accountType.equals("Asset")) {
            assetAccountNumber = database.selectLastAccountNumber("Asset") + 10;

            if (assetAccountNumber < 2000) {
                database.createAccount(name, accountType);
                displayAccountAddedMessage("Asset");
            } else {
                System.out.println("ERROR: Too many Asset accounts already exist.");
                System.out.println("Please delete an Asset account before adding a new one.");
            }
        }

//        Add Liability account
        if (accountType.equals("Liability")) {
            liabilityAccountNumber = database.selectLastAccountNumber("Liability") + 10;

            if (liabilityAccountNumber < 3000) {
                database.createAccount(name, accountType);
                displayAccountAddedMessage("Liability");
            } else {
                System.out.println("ERROR: Too many Liability accounts already exist.");
                System.out.println("Please delete an Liability account before adding a new one.");
            }
        }

//        Add Equity account
        if (accountType.equals("Equity")) {
            equityAccountNumber = database.selectLastAccountNumber("Equity") + 10;
            if (equityAccountNumber < 4000) {
                database.createAccount(name, accountType);
                displayAccountAddedMessage("Equity");
            } else {
                System.out.println("ERROR: Too many Equity accounts already exist.");
                System.out.println("Please delete an Equity account before adding a new one.");
            }
        }
    }

////    Used to create an account in the add() method REMOVE
//    public void createAccount(int accountNumber, String name, String accountType) {
//        accounts.add(new Account(accountNumber, name, accountType));
//        System.out.println("\nAccount created: ");
//        System.out.println(accounts.getAllAccounts().get(accounts.getAllAccounts().size() - 1));
//    }

    // UPDATE to pull recent account from Database table rather than the array
    public void displayAccountAddedMessage(String type) {
        System.out.println("\nAccount created: ");
        database.selectLastRecord(type);
    }

//    Remove an account from the relevant account type list
    public void remove(int accountNumber) {
        Account account = getAccount(accountNumber);

//        Check that the account balance is empty before allowing removal of the account
        if (getAccountBalance(accountNumber) == 0) {
            database.deleteAccount(accountNumber, account.getType());
            System.out.println("\nAccount removed: ");
            System.out.println(account);
        } else {
            System.out.println("\nERROR: Account balance must be empty before deleting the account.");
            System.out.println("Debit/Credit the account as necessary first.");
        }
    }

//    Perform double entry function for inputting a transaction
    public void doubleEntry(int firstAccount, int secondAccount, double updateAmountInput) {
        double firstUpdateAmount;
        double secondUpdateAmount;

        if (getAccountType(firstAccount).equals("Asset")) {
            firstUpdateAmount = updateAmountInput;
        } else {
            firstUpdateAmount = -1.0 * updateAmountInput;
        }

        if (getAccountType(secondAccount).equals("Asset")) {
            secondUpdateAmount = -1.0 * updateAmountInput;
        } else {
            secondUpdateAmount = updateAmountInput;
        }

//        updateAccountBalance(firstAccount, firstUpdateAmount); REMOVE
        database.updateBalance(firstAccount, firstUpdateAmount);
//        updateAccountBalance(secondAccount, secondUpdateAmount); REMOVE
        database.updateBalance(secondAccount, secondUpdateAmount);
        System.out.println("\nTransaction recorded:");
        System.out.println(getAccountName(firstAccount) + ": " + df.format(firstUpdateAmount));
        System.out.print("        ");
        System.out.println(getAccountName(secondAccount) + ": " + df.format(secondUpdateAmount));
    }

//    Search a hash map of all accounts and return account if found
//    public Account getAccount(int accountNumber) {
//        if (database.checkAccountExists(accountNumber, getAccountType(accountNumber))) {
//            return accounts.getDirectory().get(accountNumber);
//        }
//
//        return null;
//    }

//    public int getAccountNumber(int accountNumber) {
//        return getAccount(accountNumber).getAccountNumber();
//    }
//
//    public String getAccountName(int accountNumber) {
//        return getAccount(accountNumber).getName();
//    }
//
//    public double getAccountBalance(int accountNumber) {
//        return getAccount(accountNumber).getBalance();
//    }

    public String getAccountType(int accountNumber) {
        String type = "";
        if (accountNumber > 999 && accountNumber < 2000) {
            type = "Asset";
        }

        if (accountNumber > 1999 && accountNumber < 3000) {
            type = "Liability";
        }

        if (accountNumber > 2999 && accountNumber < 4000) {
            type = "Equity";
        }

        return type;
    }

//    public void updateAccountBalance(int accountNumber, double number) {      REMOVE
//        accounts.getDirectory().get(accountNumber).updateBalance(number);
//    }

    public boolean checkDuplicateAccount(String name) {
        for (Account account : accounts.getDirectory().values()) {
            if (account.getName().equals(name)) {
                return false;
            }
        }

        return true;
    }
}
