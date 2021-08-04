import java.util.ArrayList;

public class ChartOfAccounts {
    private ArrayList<Account> accounts;

    public ChartOfAccounts() {
        this.accounts = new ArrayList<>();
    }

    // Return a list of account names
    public ArrayList<String> accountList() {
        ArrayList<String> list = new ArrayList<>();
        for (Account account : accounts) {
            list.add(account.getName());
        }
        return list;
    }

    // Returns the account list formatted with numbers
    public ArrayList<String> formattedList() {
        ArrayList<String> formattedList = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            for (Account account : accounts) {
                formattedList.add((i + 1) + ". " + account.getName());
            }
        }
        return formattedList;
    }

    public void add(String name, String type) {
        this.accounts.add(new Account(name, type));
    }
}
