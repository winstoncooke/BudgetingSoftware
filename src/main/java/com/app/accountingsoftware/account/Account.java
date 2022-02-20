package com.app.accountingsoftware.account;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@Table
public class Account {
    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            initialValue = 1000,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )

    private long id;
    private String name;
    private String type;
    private double balance;

    public Account() {
    }

    public Account(String name, String type) {
        this.name = name;
        this.type = type;
        this.balance = 0.00;
    }

    public Account(long accountNumber, String name, String type, double balance) {
        this.id = accountNumber;
        this.name = name;
        this.type = type;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public double getBalance() {
        return this.balance;
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
        return id + " - " + name;
    }
}
