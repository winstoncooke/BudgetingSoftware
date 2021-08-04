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

    // Return a list of account names
    public ArrayList<String> accountList() {
        ArrayList<String> list = new ArrayList<>();
        for (Account account : accounts) {
            list.add(account.getName());
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

    // Sum the total balance for Liability accounts
    public double sumLiabilityAccountsBalance() {
        for (Account liability : liabilities) {
            liabilityTotalBalance += liability.getBalance();
        }
        return liabilityTotalBalance;
    }

    // Sum the total balance for Equity accounts
    public double sumEquityAccountsBalance() {
        for (Account equity : equities) {
            equityTotalBalance += equity.getBalance();
        }
        return equityTotalBalance;
    }

    // Returns the account list formatted with numbers
    public String formattedList() {
        // Add account names to a list
        ArrayList<String> formattedList = new ArrayList<>();

        //Format with numbers for printing
        for (int i = 0; i < accounts.size(); i++) {
            formattedList.add(Integer.valueOf((i + 1)) + ". " + accounts.get(i));
        }
        return "* Chart of Accounts *\n" + formattedList;
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
                System.out.println(asset.getName());
            }
        } else {
            System.out.println("* No Asset accounts *");
        }

        // Print Liabilities
        System.out.println("\nLIABILITIES");
        if (liabilities.size() > 0) {
            for (Account liability : liabilities) {
                System.out.println(liability.getName());
            }
        } else {
            System.out.println("* No Liability accounts *");
        }

        // Print Equities
        System.out.println("\nEQUITY");
        if (equities.size() > 0) {
            for (Account equity : equities) {
                System.out.println(equity.getName());
            }
        } else {
            System.out.println("* No Equity accounts *");
        }
    }
}
