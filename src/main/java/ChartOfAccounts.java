import java.util.ArrayList;
import java.text.DecimalFormat;

public class ChartOfAccounts {
    private final ArrayList<Account> assets;
    private final ArrayList<Account> liabilities;
    private final ArrayList<Account> equities;

    private final DecimalFormat df;

    private int assetAccountNumber;
    private int liabilityAccountNumber;
    private int equityAccountNumber;

    private double assetTotalBalance;
    private double liabilityTotalBalance;
    private double equityTotalBalance;

    public ChartOfAccounts() {
        this.assets = new ArrayList<>();
        this.liabilities = new ArrayList<>();
        this.equities = new ArrayList<>();

        this.df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern("#,##0.00;(#,##0.00)");

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
    public void add(String name, String accountType) {
//        Add Asset account
        if (accountType.equals("Asset") && checkDuplicateAccount(name)) {
            if (assetAccountNumber < 2000) {
                assets.add(new Account(assetAccountNumber, name, accountType));
                System.out.println("\nAccount created: ");
                System.out.println(assets.get(assets.size() - 1));
                assetAccountNumber += 10;
            } else {
                System.out.println("ERROR: Too many Asset accounts already exist.");
                System.out.println("Please delete an Asset account before adding a new one.");
            }
        }

//        Add Liability account
        if (accountType.equals("Liability") && checkDuplicateAccount(name)) {
            if (liabilityAccountNumber < 3000) {
                liabilities.add(new Account(liabilityAccountNumber, name, accountType));
                System.out.println("\nAccount created: ");
                System.out.println(liabilities.get(liabilities.size() - 1));
                liabilityAccountNumber += 10;
            } else {
                System.out.println("ERROR: Too many Liability accounts already exist.");
                System.out.println("Please delete an Liability account before adding a new one.");
            }
        }

//        Add Equity account
        if (accountType.equals("Equity") && checkDuplicateAccount(name)) {
            if (equityAccountNumber < 4000) {
                equities.add(new Account(equityAccountNumber, name, accountType));
                System.out.println("\nAccount created: ");
                System.out.println(equities.get(equities.size() - 1));
                equityAccountNumber += 10;
            } else {
                System.out.println("ERROR: Too many Equity accounts already exist.");
                System.out.println("Please delete an Equity account before adding a new one.");
            }
        }
    }

//    Remove an account from the relevant account type list
    public void remove(int accountNumber) {
        String accountType = getAccountType(accountNumber);
        Account accountInfo = accountList(accountType).get(getIndexNumber(accountNumber));

//        Check that the account balance is empty before allowing removal of the account
        if (getAccountBalance(accountNumber) == 0) {
//            Remove Asset account
            if (accountType.equals("Asset")) {
                assets.remove(getIndexNumber(accountNumber));
                System.out.println("\nAccount removed: ");
                System.out.println(accountInfo);
            }

//            Remove Liability account
            if (accountType.equals("Liability")) {
                liabilities.remove(getIndexNumber(accountNumber));
                System.out.println("\nAccount removed: ");
                System.out.println(accountInfo);
            }

//            Remove Equity account
            if (accountType.equals("Equity")) {
                equities.remove(getIndexNumber(accountNumber));
                System.out.println("\nAccount removed: ");
                System.out.println(accountInfo);
            }
        } else {
            System.out.println("\nERROR: Account balance must be empty before deleting the account.");
            System.out.println("Debit/Credit the account as necessary first.");
        }
    }

    public int getIndexNumber(int accountNumber) {
        int startPoint = 0;
        int endPoint = 0;
        String accountType = getAccountType(accountNumber);

        if (accountType.equals("Asset")) {
            endPoint = assets.size() - 1;
        }

        if (accountType.equals("Liability")) {
            endPoint = liabilities.size() - 1;
        }

        if (accountType.equals("Equity")) {
            endPoint = equities.size() - 1;
        }

        if (accountType.equals("")) {
            return -1;
        }

        while (startPoint <= endPoint) {
            int middle = (endPoint + startPoint) / 2;

            if (accountType.equals("Asset")) {
                if (assets.get(middle).getAccountNumber() == accountNumber) {
                    return middle;
                }

                if (assets.get(middle).getAccountNumber() < accountNumber) {
                    startPoint = middle + 1;
                }

                if (assets.get(middle).getAccountNumber() > accountNumber) {
                    endPoint = middle - 1;
                }
            }

            if (accountType.equals("Liability")) {
                if (liabilities.get(middle).getAccountNumber() == accountNumber) {
                    return middle;
                }

                if (liabilities.get(middle).getAccountNumber() < accountNumber) {
                    startPoint = middle + 1;
                }

                if (liabilities.get(middle).getAccountNumber() > accountNumber) {
                    endPoint = middle - 1;
                }
            }

            if (accountType.equals("Equity")) {
                if (equities.get(middle).getAccountNumber() == accountNumber) {
                    return middle;
                }

                if (equities.get(middle).getAccountNumber() < accountNumber) {
                    startPoint = middle + 1;
                }

                if (equities.get(middle).getAccountNumber() > accountNumber) {
                    endPoint = middle - 1;
                }
            }
        }

        return -1;
    }

    public boolean accountExists(int accountNumber) {
        int startPoint = 0;
        int endPoint = 0;
        String accountType = getAccountType(accountNumber);

        if (accountType.equals("Asset")) {
            endPoint = assets.size() - 1;
        }

        if (accountType.equals("Liability")) {
            endPoint = liabilities.size() - 1;
        }

        if (accountType.equals("Equity")) {
            endPoint = equities.size() - 1;
        }

        if (accountType.equals("")) {
            return false;
        }

        while (startPoint <= endPoint) {
            int middle = (endPoint + startPoint) / 2;

            if (accountType.equals("Asset")) {
                if (assets.get(middle).getAccountNumber() == accountNumber) {
                    return true;
                }

                if (assets.get(middle).getAccountNumber() < accountNumber) {
                    startPoint = middle + 1;
                }

                if (assets.get(middle).getAccountNumber() > accountNumber) {
                    endPoint = middle - 1;
                }
            }

            if (accountType.equals("Liability")) {
                if (liabilities.get(middle).getAccountNumber() == accountNumber) {
                    return true;
                }

                if (liabilities.get(middle).getAccountNumber() < accountNumber) {
                    startPoint = middle + 1;
                }

                if (liabilities.get(middle).getAccountNumber() > accountNumber) {
                    endPoint = middle - 1;
                }
            }

            if (accountType.equals("Equity")) {
                if (equities.get(middle).getAccountNumber() == accountNumber) {
                    return true;
                }

                if (equities.get(middle).getAccountNumber() < accountNumber) {
                    startPoint = middle + 1;
                }

                if (equities.get(middle).getAccountNumber() > accountNumber) {
                    endPoint = middle - 1;
                }
            }
        }

        return false;
    }

    public int getAccountNumber(int accountNumber) {
        int startPoint = 0;
        int endPoint = 0;
        String accountType = getAccountType(accountNumber);

        if (accountType.equals("Asset")) {
            endPoint = assets.size() - 1;
        }

        if (accountType.equals("Liability")) {
            endPoint = liabilities.size() - 1;
        }

        if (accountType.equals("Equity")) {
            endPoint = equities.size() - 1;
        }

        if (accountType.equals("")) {
            return -1;
        }

        while (startPoint <= endPoint) {
            int middle = (endPoint + startPoint) / 2;

            if (accountType.equals("Asset")) {
                if (assets.get(middle).getAccountNumber() == accountNumber) {
                    return assets.get(middle).getAccountNumber();
                }

                if (assets.get(middle).getAccountNumber() < accountNumber) {
                    startPoint = middle + 1;
                }

                if (assets.get(middle).getAccountNumber() > accountNumber) {
                    endPoint = middle - 1;
                }
            }

            if (accountType.equals("Liability")) {
                if (liabilities.get(middle).getAccountNumber() == accountNumber) {
                    return liabilities.get(middle).getAccountNumber();
                }

                if (liabilities.get(middle).getAccountNumber() < accountNumber) {
                    startPoint = middle + 1;
                }

                if (liabilities.get(middle).getAccountNumber() > accountNumber) {
                    endPoint = middle - 1;
                }
            }

            if (accountType.equals("Equity")) {
                if (equities.get(middle).getAccountNumber() == accountNumber) {
                    return equities.get(middle).getAccountNumber();
                }

                if (equities.get(middle).getAccountNumber() < accountNumber) {
                    startPoint = middle + 1;
                }

                if (equities.get(middle).getAccountNumber() > accountNumber) {
                    endPoint = middle - 1;
                }
            }
        }

        return -1;
    }

    public String getAccountName(int accountNumber) {
        int startPoint = 0;
        int endPoint = 0;
        String accountType = getAccountType(accountNumber);

        if (accountType.equals("Asset")) {
            endPoint = assets.size() - 1;
        }

        if (accountType.equals("Liability")) {
            endPoint = liabilities.size() - 1;
        }

        if (accountType.equals("Equity")) {
            endPoint = equities.size() - 1;
        }

        if (accountType.equals("")) {
            return "\nERROR: Account not found";
        }

        while (startPoint <= endPoint) {
            int middle = (endPoint + startPoint) / 2;

            if (accountType.equals("Asset")) {
                if (assets.get(middle).getAccountNumber() == accountNumber) {
                    return assets.get(middle).getName();
                }

                if (assets.get(middle).getAccountNumber() < accountNumber) {
                    startPoint = middle + 1;
                }

                if (assets.get(middle).getAccountNumber() > accountNumber) {
                    endPoint = middle - 1;
                }
            }

            if (accountType.equals("Liability")) {
                if (liabilities.get(middle).getAccountNumber() == accountNumber) {
                    return liabilities.get(middle).getName();
                }

                if (liabilities.get(middle).getAccountNumber() < accountNumber) {
                    startPoint = middle + 1;
                }

                if (liabilities.get(middle).getAccountNumber() > accountNumber) {
                    endPoint = middle - 1;
                }
            }

            if (accountType.equals("Equity")) {
                if (equities.get(middle).getAccountNumber() == accountNumber) {
                    return equities.get(middle).getName();
                }

                if (equities.get(middle).getAccountNumber() < accountNumber) {
                    startPoint = middle + 1;
                }

                if (equities.get(middle).getAccountNumber() > accountNumber) {
                    endPoint = middle - 1;
                }
            }
        }

        return "\nERROR: Account not found";
    }

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

    public double getAccountBalance(int accountNumber) {
        double accountBalance = 0;
        String accountType = getAccountType(accountNumber);

        if (accountType.equals("Asset")) {
            accountBalance = assets.get(getIndexNumber(accountNumber)).getBalance();
        }

        if (accountType.equals("Liability")) {
            accountBalance = liabilities.get(getIndexNumber(accountNumber)).getBalance();
        }

        if (accountType.equals("Equity")) {
            accountBalance = equities.get(getIndexNumber(accountNumber)).getBalance();
        }

        return accountBalance;
    }

    public void updateAccountBalance(int accountNumber, double number) {
        String accountType = getAccountType(accountNumber);

        if (accountType.equals("Asset")) {
            assets.get(getIndexNumber(accountNumber)).updateBalance(number);
        }

        if (accountType.equals("Liability")) {
            liabilities.get(getIndexNumber(accountNumber)).updateBalance(number);
        }

        if (accountType.equals("Equity")) {
            equities.get(getIndexNumber(accountNumber)).updateBalance(number);
        }
    }

    public String getAccountType(int accountNumber) {
        String type = "";

        if (accountNumber >= 1000 && accountNumber < 2000) {
            type = "Asset";
        }

        if (accountNumber >= 2000 && accountNumber < 3000) {
            type = "Liability";
        }

        if (accountNumber >= 3000 && accountNumber < 4000) {
            type = "Equity";
        }

        return type;
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

//    Print a single account
    public void printAccountNameWithBalance(int input) {
        System.out.println(
                accountList().get(getIndexNumber(input)).getName() +
                ": " +
                accountList().get(getIndexNumber(input)).formattedBalance());
    }

//    Print a formatted and comprehensive Chart of Accounts
    public void printChartOfAccounts() {
        System.out.println("* Chart of Accounts *");

        // Print Assets
        System.out.println("\nASSETS");
        if (assets.size() > 0) {
            for (Account asset : assets) {
                System.out.println(asset.getAccountNumber() + " - " + asset.getName() + ": " + asset.formattedBalance());
            }
        } else {
            System.out.println("* No Asset accounts *");
        }

        // Print Liabilities
        System.out.println("\nLIABILITIES");
        if (liabilities.size() > 0) {
            for (Account liability : liabilities) {
                System.out.println(liability.getAccountNumber() + " - " + liability.getName() + ": " +
                        liability.formattedBalance());
            }
        } else {
            System.out.println("* No Liability accounts *");
        }

        // Print Equities
        System.out.println("\nEQUITY");
        if (equities.size() > 0) {
            for (Account equity : equities) {
                System.out.println(equity.getAccountNumber() + " - " + equity.getName() + ": " +
                        equity.formattedBalance());
            }
        } else {
            System.out.println("* No Equity accounts *");
        }
    }
}
