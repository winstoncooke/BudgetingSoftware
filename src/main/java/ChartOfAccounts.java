import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.HashMap;

public class ChartOfAccounts {
    private final ArrayList<Account> accounts;
    private final DirectoryID accountsByID;
    private final DirectoryName accountsByName;

    private final DecimalFormat df;

    private int assetAccountNumber;
    private int liabilityAccountNumber;
    private int equityAccountNumber;

    public ChartOfAccounts() {
        this.df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern("#,##0.00;(#,##0.00)");

        this.accounts = new ArrayList<>();
        this.accountsByID = new DirectoryID();
        this.accountsByName = new DirectoryName();

        this.assetAccountNumber = 1000;
        this.liabilityAccountNumber = 2000;
        this.equityAccountNumber = 3000;
    }

//    Print a formatted and comprehensive Chart of Accounts
    public void printChartOfAccounts() {
        System.out.println("* Chart of Accounts *");

//        Print Assets
        System.out.println("\nASSETS");
        if (getAssetAccounts().size() > 0) {
            printAccountList("Asset");
        } else {
            System.out.println("* No Asset accounts *");
        }

//        Print Liabilities
        System.out.println("\nLIABILITIES");
        if (getLiabilityAccounts().size() > 0) {
            printAccountList("Liability");
        } else {
            System.out.println("* No Liability accounts *");
        }

//        Print Equities
        System.out.println("\nEQUITY");
        if (getEquityAccounts().size() > 0) {
            printAccountList("Equity");
        } else {
            System.out.println("* No Equity accounts *");
        }
    }

    //    Return list of accounts for the requested account type
    public void printAccountList(String type) {
        if (type.equals("Asset")) {
            printAssetAccounts();
        }

        if (type.equals("Liability")) {
            printLiabilityAccounts();
        }

        if (type.equals("Equity")) {
            printEquityAccounts();
        }
    }

//    Return list of all accounts
    public ArrayList<Account> getAccountList() {
        return accounts;
    }

    public void printAssetAccounts() {
        for (Account asset : getAssetAccounts()) {
            System.out.println(asset + ": " + asset.formattedBalance());
        }
    }

    public ArrayList<Account> getAssetAccounts() {
        ArrayList<Account> assets = new ArrayList<>();
        for (Account asset : accounts) {
            if (asset.getType().equals("Asset")) {
                assets.add(asset);
            }
        }
        return assets;
    }

    public void printLiabilityAccounts() {
        for (Account liability : getLiabilityAccounts()) {
            System.out.println(liability + ": " + liability.formattedBalance());
        }
    }

    public ArrayList<Account> getLiabilityAccounts() {
        ArrayList<Account> liabilities = new ArrayList<>();
        for (Account liability : accounts) {
            if (liability.getType().equals("Liability")) {
                liabilities.add(liability);
            }
        }
        return liabilities;
    }

    public void printEquityAccounts() {
        for (Account equity : getEquityAccounts()) {
            System.out.println(equity + ": " + equity.formattedBalance());
        }
    }

    public ArrayList<Account> getEquityAccounts() {
        ArrayList<Account> equities = new ArrayList<>();
        for (Account equity : accounts) {
            if (equity.getType().equals("Equity")) {
                equities.add(equity);
            }
        }
        return equities;
    }

//    Search a hash map of all accounts and return true if found
    public boolean checkAccountExists(int accountNumber) {
        return accountsByID.getDirectoryByID().containsKey(accountNumber);
    }

//    Add an account to the relevant account type list
    public void add(String name, String accountType) {
//        Add Asset account
        if (accountType.equals("Asset") && checkDuplicateAccount(name)) {
            if (assetAccountNumber < 2000) {
                createAccount(assetAccountNumber, name, accountType);
                assetAccountNumber += 10;
            } else {
                System.out.println("ERROR: Too many Asset accounts already exist.");
                System.out.println("Please delete an Asset account before adding a new one.");
            }
        }

//        Add Liability account
        if (accountType.equals("Liability") && checkDuplicateAccount(name)) {
            if (liabilityAccountNumber < 3000) {
                createAccount(liabilityAccountNumber, name, accountType);
                liabilityAccountNumber += 10;
            } else {
                System.out.println("ERROR: Too many Liability accounts already exist.");
                System.out.println("Please delete an Liability account before adding a new one.");
            }
        }

//        Add Equity account
        if (accountType.equals("Equity") && checkDuplicateAccount(name)) {
            if (equityAccountNumber < 4000) {
                createAccount(equityAccountNumber, name, accountType);
                equityAccountNumber += 10;
            } else {
                System.out.println("ERROR: Too many Equity accounts already exist.");
                System.out.println("Please delete an Equity account before adding a new one.");
            }
        }
    }

//    Used to create an account in the add() method
    public void createAccount(int accountNumber, String name, String accountType) {
        accounts.add(new Account(accountNumber, name, accountType));
        accountsByID.addAccount(new Account(accountNumber, name, accountType));
        accountsByName.addAccount(new Account(accountNumber, name, accountType));
        System.out.println("\nAccount created: ");
        System.out.println(accounts.get(accounts.size() - 1));
    }

//    Remove an account from the relevant account type list
    public void remove(int accountNumber) {
        Account account = getAccount(accountNumber);

//        Check that the account balance is empty before allowing removal of the account
        if (getAccountBalance(accountNumber) == 0) {
            accounts.remove(getIndexNumber(accountNumber));
            accountsByID.removeAccount(account);
            accountsByName.removeAccount(account);
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

        updateAccountBalance(firstAccount, firstUpdateAmount);
        updateAccountBalance(secondAccount, secondUpdateAmount);
        System.out.println("\nTransaction recorded:");
        System.out.println(getAccountName(firstAccount) + ": " + df.format(firstUpdateAmount));
        System.out.print("        ");
        System.out.println(getAccountName(secondAccount) + ": " + df.format(secondUpdateAmount));
    }

    public int getIndexNumber(int accountNumber) {
        int startPoint = 0;
        int endPoint = accounts.size() - 1;

        while (startPoint <= endPoint) {
            int middle = (endPoint + startPoint) / 2;

            if (accounts.get(middle).getAccountNumber() == accountNumber) {
                return middle;
            }

            if (accounts.get(middle).getAccountNumber() < accountNumber) {
                startPoint = middle + 1;
            }

            if (accounts.get(middle).getAccountNumber() > accountNumber) {
                endPoint = middle - 1;
            }
        }

        return -1;
    }

//    Search a hash map of all accounts and return account if found
    public Account getAccount(int accountNumber) {
        if (accountsByID.getDirectoryByID().get(accountNumber) != null) {
            return accountsByID.getDirectoryByID().get(accountNumber);
        }

        return null;
    }

    public int getAccountNumber(int accountNumber) {
        return getAccount(accountNumber).getAccountNumber();
    }

    public String getAccountName(int accountNumber) {
        return getAccount(accountNumber).getName();
    }

    public double getAccountBalance(int accountNumber) {
        return getAccount(accountNumber).getBalance();
    }

    public String getAccountType(int accountNumber) {
        return getAccount(accountNumber).getType();
    }

    public void updateAccountBalance(int accountNumber, double number) {
        accounts.get(getIndexNumber(accountNumber)).updateBalance(number);
        accountsByID.getDirectoryByID().get(accountNumber).updateBalance(number);
    }

    public boolean checkDuplicateAccount(String name) {
        return !accountsByName.getDirectoryByName().containsKey(name);
    }
}
