package com.app.budgetingsoftware;

import static org.junit.jupiter.api.Assertions.*;

import com.app.budgetingsoftware.account.Account;
import org.junit.jupiter.api.Test;

public class AccountTests {

    @Test
    public void newAccountSuccessfullyCreatesAccount() {
        Account account = new Account("Movies", "Entertainment", 50);
        assertEquals("Movies", account.getName());
        assertEquals("Entertainment", account.getCategory());
        assertEquals(50, account.getBudget());
    }

    @Test
    public void addExpenseFunctionsProperly() {
        Account account = new Account("Movies", "Entertainment", 150);
        account.updateExpenses(123.45);
        assertEquals(123.45, account.getExpenses(), 0.0);
        account.updateExpenses(-123.45);
        assertEquals(0, account.getExpenses(), 0.0);
    }
}
