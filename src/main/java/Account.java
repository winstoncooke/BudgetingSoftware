import java.text.DecimalFormat;

public class Account {
    private final DecimalFormat df;

    private final int accountNumber;
    private final String name;
    private final String type;
    private double balance;

    public Account(int accountNumber, String name, String type) {
        this.df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern("#,##0.00;(#,##0.00)");

        this.accountNumber = accountNumber;
        this.name = name;
        this.type = type;
        this.balance = 0.00;
    }

    public int getAccountNumber() {return accountNumber;}

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public String formattedBalance() {
        return df.format(balance);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void updateBalance (double balance) {
        this.balance += balance;
    }

    @Override
    public String toString() {
        return accountNumber + " - " + name;
    }
}
