import java.util.ArrayList;
import java.util.HashMap;

public class Directory {
    private HashMap<Integer, Account> directory;

    public Directory() {
        this.directory = new HashMap<>();
    }

    public HashMap<Integer, Account> getDirectory() {
        return directory;
    }

    public void add(Account account) {
        int accountNumber = account.getAccountNumber();

        if (this.directory.containsKey(accountNumber)) {
            System.out.println("Account is already in the Chart of Accounts");
        } else {
            directory.put(accountNumber, account);
        }
    }

    public void remove(Account account) {
        int accountNumber = account.getAccountNumber();

        if (this.directory.containsKey(accountNumber)) {
            directory.remove(accountNumber);
        } else {
            System.out.println("Account not found");
        }
    }

    public void setBalance(Account account, Integer number) {
        int accountNumber = account.getAccountNumber();

        if (this.directory.containsKey(accountNumber)) {
            directory.get(accountNumber).updateBalance(number);
        }
    }

    public void printAssetAccounts() {
        for (Account asset : getAssetAccounts()) {
            System.out.println(asset + ": " + asset.formattedBalance());
        }
    }

    public ArrayList<Account> getAssetAccounts() {
        ArrayList<Account> assets = new ArrayList<>();
        for (Account asset : directory.values()) {
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
        for (Account liability : directory.values()) {
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
        for (Account equity : directory.values()) {
            if (equity.getType().equals("Equity")) {
                equities.add(equity);
            }
        }
        return equities;
    }
}
