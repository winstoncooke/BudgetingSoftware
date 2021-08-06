import java.util.ArrayList;

public class ChartOfAccounts {
    private ArrayList<Account> accounts;
    private ArrayList<Account> assets;
    private ArrayList<Account> liabilities;
    private ArrayList<Account> equities;

    private double assetTotalBalance;
    private double liabilityTotalBalance;
    private double equityTotalBalance;

    public ChartOfAccounts() {
        this.accounts = new ArrayList<>();
        this.assets = new ArrayList<>();
        this.assetTotalBalance = 0.00;
        this.liabilities = new ArrayList<>();
        this.liabilityTotalBalance = 0.00;
        this.equities = new ArrayList<>();
        this.equityTotalBalance = 0.00;
    }

//     Return a list of all account names
    public ArrayList<Account> accountList() {
        ArrayList<Account> list = new ArrayList<>();

        for (Account account : accounts) {
            list.add(account);
        }
        return list;
    }

//     Sum the total balance for all accounts
    public double sumAllAccountsBalance() {
        for (Account account : accounts) {
            assetTotalBalance += account.getBalance();
        }
        return assetTotalBalance;
    }

//     Add an account to the all accounts list
    public void add(String name, String type) {
        this.accounts.add(new Account(name, type));
    }

//     Return a list of Asset account names
    public ArrayList<Account> assetList() {
        ArrayList<Account> list = new ArrayList<>();
        for (Account asset : assets) {
            list.add(asset);
        }
        return list;
    }

//     Sum the total balance for Asset accounts
    public double sumAssetAccountsBalance() {
        for (Account asset : assets) {
            assetTotalBalance += asset.getBalance();
        }
        return assetTotalBalance;
    }

//     Add an account to the Asset accounts list
    public void addAsset(String name, String type) {
        this.assets.add(new Account(name, type));
    }

//     Return a list of Liability account names
    public ArrayList<Account> liabilityList() {
        ArrayList<Account> list = new ArrayList<>();
        for (Account liability : liabilities) {
            list.add(liability);
        }
        return list;
    }

//     Sum the total balance for Liability accounts
    public double sumLiabilityAccountsBalance() {
        for (Account liability : liabilities) {
            liabilityTotalBalance += liability.getBalance();
        }
        return liabilityTotalBalance;
    }

//     Return a list of Liability account names
    public ArrayList<Account> equityList() {
        ArrayList<Account> list = new ArrayList<>();
        for (Account equity : equities) {
            list.add(equity);
        }
        return list;
    }

//     Add an account to the Liability accounts list
    public void addLiability(String name, String type) {
        this.liabilities.add(new Account(name, type));
    }

//     Sum the total balance for Equity accounts
    public double sumEquityAccountsBalance() {
        for (Account equity : equities) {
            equityTotalBalance += equity.getBalance();
        }
        return equityTotalBalance;
    }

//     Add an account to the Equity accounts list
    public void addEquity(String name, String type) {
        this.equities.add(new Account(name, type));
    }

//    Print a formatted and comprehensive Chart of Accounts
    public void printChartOfAccounts() {
        System.out.println("Chart of Accounts");

        // Print Assets
        System.out.println("\nASSETS");
        if (assets.size() > 0) {
            for (Account asset : assets) {
                System.out.println(asset.getName() + ": " + asset.getBalance());
            }
        } else {
            System.out.println("* No Asset accounts *");
        }

        // Print Liabilities
        System.out.println("\nLIABILITIES");
        if (liabilities.size() > 0) {
            for (Account liability : liabilities) {
                System.out.println(liability.getName() + ": " + liability.getBalance());
            }
        } else {
            System.out.println("* No Liability accounts *");
        }

        // Print Equities
        System.out.println("\nEQUITY");
        if (equities.size() > 0) {
            for (Account equity : equities) {
                System.out.println(equity.getName() + ": " + equity.getBalance());
            }
        } else {
            System.out.println("* No Equity accounts *");
        }
    }
}
