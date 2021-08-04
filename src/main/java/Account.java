public class Account {
    private String name;
    private String type;
    private double balance;

    public Account(String name, String type) {
        this.name = name;
        this.type = type;
        this.balance = 0.00;
    }

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

    @Override
    public String toString() {
        return "Account: " + name + "\nBalance: " + balance;
    }
}
