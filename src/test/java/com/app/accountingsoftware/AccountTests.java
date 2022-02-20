package com.app.accountingsoftware;

import static org.junit.jupiter.api.Assertions.*;

import com.app.accountingsoftware.account.Account;
import org.junit.jupiter.api.Test;

public class AccountTests {
    private Account account;

    @Test
    public void newAccountSuccessfullyCreatesAccount() {
        account = new Account("Account", "Asset");
        assertEquals("Account", account.getName());
        assertEquals("Asset", account.getType());
    }

    @Test
    public void updateBalanceFunctionsProperly() {
        account = new Account("Account", "Asset");
        account.updateBalance(123.45);
        assertEquals(123.45, account.getBalance(), 0.0);
        account.updateBalance(-123.45);
        assertEquals(0, account.getBalance(), 0.0);
    }
}
