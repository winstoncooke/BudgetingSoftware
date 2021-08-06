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
        account = new Account("Test Account", "Asset");
        assertEquals("Test Account", account.getName());
    }

    @Test
    public void setBalanceFunctionsProperly() {
        account = new Account("Test Account", "Asset");
        account.setBalance(123.45);
        assertEquals(123.45, account.getBalance(), 0.0);
    }

    @Test
    public void newAccountTypeIsAsset() {
        account = new Account("Test Account", "Asset");
        assertEquals("Asset", account.getType());
    }

    @Test
    public void newAccountTypeIsLiability() {
        account = new Account("Test Account", "Liability");
        assertEquals("Liability", account.getType());
    }

    @Test
    public void newAccountTypeIsEquity() {
        account = new Account("Test Account", "Equity");
        assertEquals("Equity", account.getType());
    }
}