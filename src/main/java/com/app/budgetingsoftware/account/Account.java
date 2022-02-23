package com.app.budgetingsoftware.account;

import javax.persistence.*;

@Entity
@Table
public class Account {
    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )

    private long id;
    private String name;
    private String category;
    private double budget;
    private double expenses;

    public Account() {
    }

    public Account(String name, String category, double budget) {
        this.name = name;
        this.category = category;
        this.budget = budget;
        this.expenses = 0.00;
    }

    public String getName() {
        return this.name;
    }

    public double getBudget() {
        return this.budget;
    }

    public String getCategory() {
        return this.category;
    }

    public double getExpenses() {
        return this.expenses;
    }

    public void updateExpenses(double amount) {
        this.expenses += amount;
    }
}
