import java.util.ArrayList;

public class ChartOfAccounts {
    private ArrayList<Account> assets;
    private ArrayList<Account> liabilities;
    private ArrayList<Account> equities;

    private int assetAccountNumber;
    private int liabilityAccountNumber;
    private int equityAccountNumber;
    private int index;

    private double assetTotalBalance;
    private double liabilityTotalBalance;
    private double equityTotalBalance;

    public ChartOfAccounts() {
        this.assets = new ArrayList<>();
        this.liabilities = new ArrayList<>();
        this.equities = new ArrayList<>();

        this.assetTotalBalance = 0.00;
        this.liabilityTotalBalance = 0.00;
        this.equityTotalBalance = 0.00;

        this.assetAccountNumber = 1000;
        this.liabilityAccountNumber = 2000;
        this.equityAccountNumber = 3000;
    }

//    Return list of all accounts
    public ArrayList<Account> accountList() {
        ArrayList<Account> list = new ArrayList<>();
        list.addAll(assets);
        list.addAll(liabilities);
        list.addAll(equities);
        return list;
    }

//    Return list of accounts for the requested account type
    public ArrayList<Account> accountList(String type) {
        ArrayList<Account> list = new ArrayList<>();

        if (type.equals("Asset")) {
            list.addAll(assets);
        }

        if (type.equals("Liability")) {
            list.addAll(liabilities);
        }

        if (type.equals("Equity")) {
            list.addAll(equities);
        }

        return list;
    }

//    Add an account to the relevant account type list
    public void add(String name, String type) {
//        Add Asset account
        if (type.equals("Asset") && checkDuplicateAccount(name)) {
            if (assetAccountNumber < 2000) {
                this.assets.add(new Account(assetAccountNumber, name, type));
                System.out.println("\nAccount created: " +
                    accountList().get(accountList().size() - 1).getAccountNumber() +
                    " - " +
                    accountList().get(accountList().size() - 1).getName() +
                    " (" +
                    accountList().get(accountList().size() - 1).getType() +
                    ")");
                assetAccountNumber += 10;
            } else {
                System.out.println("ERROR: Too many Asset accounts already exist.");
                System.out.println("Please delete an Asset account before adding a new one.");
            }
        }

//        Add Liability account
        if (type.equals("Liability") && checkDuplicateAccount(name)) {
            if (liabilityAccountNumber < 3000) {
                this.liabilities.add(new Account(liabilityAccountNumber, name, type));
                System.out.println("\nAccount created: " +
                        accountList().get(accountList().size() - 1).getAccountNumber() +
                        " - " +
                        accountList().get(accountList().size() - 1).getName() +
                        " (" +
                        accountList().get(accountList().size() - 1).getType() +
                        ")");
                liabilityAccountNumber += 10;
            } else {
                System.out.println("ERROR: Too many Liability accounts already exist.");
                System.out.println("Please delete an Liability account before adding a new one.");
            }
        }

//        Add Equity account
        if (type.equals("Equity") && checkDuplicateAccount(name)) {
            if (equityAccountNumber < 4000) {
                this.equities.add(new Account(equityAccountNumber, name, type));
                System.out.println("\nAccount created: " +
                        accountList().get(accountList().size() - 1).getAccountNumber() +
                        " - " +
                        accountList().get(accountList().size() - 1).getName() +
                        " (" +
                        accountList().get(accountList().size() - 1).getType() +
                        ")");
                equityAccountNumber += 10;
            } else {
                System.out.println("ERROR: Too many Asset accounts already exist.");
                System.out.println("Please delete an Asset account before adding a new one.");
            }
        }
    }

    public boolean checkDuplicateAccount(String name) {
        for (int i = 0; i < accountList().size(); i++) {
            if (accountList().get(i).getName().equals(name)) {
                System.out.println("\nERROR: Account already exists");
                return false;
            }
        }
        return true;
    }

    public double getAccountBalance (int accountNumber) {
        return accountList().get(getIndexNumber(accountNumber)).getBalance();
    }

    public int getIndexNumber (int accountNumber) {
        for (int i = 0; i < accountList().size(); i++) {
            if (accountList().get(i).getAccountNumber() == accountNumber) {
                index = i;
            }
        }
        return index;
    }

//     Sum the total balance for all accounts
    public double sumAllAccountsBalance() {
        double totalBalance = 0.0;
//        Sum Assets
        for (Account asset : assets) {
            totalBalance += asset.getBalance();
        }
//        Sum Liabilities
        for (Account liability : liabilities) {
            totalBalance += liability.getBalance();
        }

//        Sum Equities
        for (Account equity : equities) {
            totalBalance += equity.getBalance();
        }

        return totalBalance;
    }

//     Sum the total balance for Asset accounts
    public double sumAssetAccountsBalance() {
        for (Account asset : assets) {
            assetTotalBalance += asset.getBalance();
        }
        return assetTotalBalance;
    }

//     Sum the total balance for Liability accounts
    public double sumLiabilityAccountsBalance() {
        for (Account liability : liabilities) {
            liabilityTotalBalance += liability.getBalance();
        }
        return liabilityTotalBalance;
    }

//     Sum the total balance for Equity accounts
    public double sumEquityAccountsBalance() {
        for (Account equity : equities) {
            equityTotalBalance += equity.getBalance();
        }
        return equityTotalBalance;
    }

//    Print a formatted and comprehensive Chart of Accounts
    public void printChartOfAccounts() {
        System.out.println("* Chart of Accounts *");

        // Print Assets
        System.out.println("\nASSETS");
        if (assets.size() > 0) {
            for (Account asset : assets) {
                System.out.println(asset.getAccountNumber() + " - " + asset.getName() + ": " + asset.getBalance());
            }
        } else {
            System.out.println("* No Asset accounts *");
        }

        // Print Liabilities
        System.out.println("\nLIABILITIES");
        if (liabilities.size() > 0) {
            for (Account liability : liabilities) {
                System.out.println(liability.getAccountNumber() + " - " + liability.getName() + ": " + liability.getBalance());
            }
        } else {
            System.out.println("* No Liability accounts *");
        }

        // Print Equities
        System.out.println("\nEQUITY");
        if (equities.size() > 0) {
            for (Account equity : equities) {
                System.out.println(equity.getAccountNumber() + " - " + equity.getName() + ": " + equity.getBalance());
            }
        } else {
            System.out.println("* No Equity accounts *");
        }
    }
}
