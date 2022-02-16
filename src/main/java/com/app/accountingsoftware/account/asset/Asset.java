package com.app.accountingsoftware.account.asset;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@Table
public class Asset {
    @Id
    @SequenceGenerator(
            name = "asset_sequence",
            sequenceName = "asset_sequence",
            initialValue = 1000,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "asset_sequence"
    )

    private long accountNumber;
    private String name;
    private String type;
    private double balance;

    public Asset() {
    }

    public Asset(String name) {
        this.name = name;
        this.type = "Asset";
        this.balance = 0.00;
    }

    public Asset(long accountNumber, String name, String type, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.type = type;
        this.balance = balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFormattedBalance() {
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern("#,##0.00;(#,##0.00)");
        return df.format(balance);
    }

    @Override
    public String toString() {
        return accountNumber + " - " + name;
    }
}
