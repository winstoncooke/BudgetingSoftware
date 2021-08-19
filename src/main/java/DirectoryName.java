import java.util.HashMap;

public class DirectoryName {
    private HashMap<String, Account> directoryByName;

    public DirectoryName() {
        this.directoryByName = new HashMap<>();
    }

    public HashMap<String, Account> getDirectoryByName() {
        return directoryByName;
    }

    public void addAccount(Account account) {
        String accountName = account.getName();

        if (this.directoryByName.containsKey(accountName)) {
            System.out.println("Account is already in the Chart of Accounts");
        } else {
            directoryByName.put(accountName, account);
        }
    }

    public void removeAccount(Account account) {
        String accountName = account.getName();

        if (this.directoryByName.containsKey(accountName)) {
            directoryByName.remove(accountName);
        } else {
            System.out.println("Account not found");
        }
    }
}
