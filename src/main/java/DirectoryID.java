import java.util.HashMap;

public class DirectoryID {
    private HashMap<Integer, Account> directoryByID;

    public DirectoryID() {
        this.directoryByID = new HashMap<>();
    }

    public HashMap<Integer, Account> getDirectoryByID() {
        return directoryByID;
    }

    public void addAccount(Account account) {
        int accountNumber = account.getAccountNumber();

        if (this.directoryByID.containsKey(accountNumber)) {
            System.out.println("Account is already in the Chart of Accounts");
        } else {
            directoryByID.put(accountNumber, account);
        }
    }

    public void removeAccount(Account account) {
        int accountNumber = account.getAccountNumber();

        if (this.directoryByID.containsKey(accountNumber)) {
            directoryByID.remove(accountNumber);
        } else {
            System.out.println("Account not found");
        }
    }

    public void setBalance(Account account, Integer number) {
        int accountNumber = account.getAccountNumber();

        if (this.directoryByID.containsKey(accountNumber)) {
            directoryByID.get(accountNumber).updateBalance(number);
        }
    }
}
