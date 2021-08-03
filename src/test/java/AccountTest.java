import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountTest {
    private Account account;

/*
    @Before
    public void initialize() {

    }
*/

    @Test
    public void newAccountSuccessfullyCreatesAccount() {
        account = new Account("Test", "Asset");
        assertEquals("Test", account.getName());
    }

    @Test
    public void newAccountCanHoldBalance() {
        account = new Account("Test", "Asset", 123.45);
        assertEquals(123.45, account.getBalance(), 0.0);
    }

    @Test
    public void newAccountTypeIsAsset() {
        account = new Account("Test", "Asset");
        assertEquals("Asset", account.getType());
    }

    @Test
    public void newAccountTypeIsLiability() {
        account = new Account("Test", "Liability");
        assertEquals("Liability", account.getType());
    }

    @Test
    public void newAccountTypeIsEquity() {
        account = new Account("Test", "Equity");
        assertEquals("Equity", account.getType());
    }
}