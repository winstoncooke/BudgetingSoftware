public class Account {
    private int accountNumber;
    private String name;
    private String type;
    private double balance;

    public Account(int accountNumber, String name, String type) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.type = type;
        this.balance = 0.00;
    }

    public int getAccountNumber() {return accountNumber;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void updateBalance (double balance) {
        this.balance += balance;
    }

    @Override
    public String toString() {
        return "Account: " + name + "\nBalance: " + balance;
    }
}
