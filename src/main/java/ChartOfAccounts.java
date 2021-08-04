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

    // Return a list of all account names
    public ArrayList<String> accountList() {
        ArrayList<String> list = new ArrayList<>();
        for (Account account : accounts) {
            list.add(account.getName());
        }
        return list;
    }

    // Return a list of Asset account names
    public ArrayList<String> assetList() {
        ArrayList<String> list = new ArrayList<>();
        for (Account asset : assets) {
            list.add(asset.getName());
        }
        return list;
    }

    // Sum the total balance for Asset accounts
    public double sumAssetAccountsBalance() {
        for (Account asset : assets) {
            assetTotalBalance += asset.getBalance();
        }
        return assetTotalBalance;
    }

    // Return a list of Liability account names
    public ArrayList<String> liabilityList() {
        ArrayList<String> list = new ArrayList<>();
        for (Account liability : liabilities) {
            list.add(liability.getName());
        }
        return list;
    }

    // Sum the total balance for Liability accounts
    public double sumLiabilityAccountsBalance() {
        for (Account liability : liabilities) {
            liabilityTotalBalance += liability.getBalance();
        }
        return liabilityTotalBalance;
    }

    // Return a list of Liability account names
    public ArrayList<String> equityList() {
        ArrayList<String> list = new ArrayList<>();
        for (Account equity : equities) {
            list.add(equity.getName());
        }
        return list;
    }

    // Sum the total balance for Equity accounts
    public double sumEquityAccountsBalance() {
        for (Account equity : equities) {
            equityTotalBalance += equity.getBalance();
        }
        return equityTotalBalance;
    }

    public void add(String name, String type) {
        this.accounts.add(new Account(name, type));
    }

    public void addAsset(String name, String type) {
        this.assets.add(new Account(name, type));
    }

    public void addLiability(String name, String type) {
        this.liabilities.add(new Account(name, type));
    }

    public void addEquity(String name, String type) {
        this.equities.add(new Account(name, type));
    }

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
