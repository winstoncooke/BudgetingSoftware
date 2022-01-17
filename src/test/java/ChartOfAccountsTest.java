// import static org.junit.Assert.assertFalse;
import application.ChartOfAccounts;
import application.Database;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class ChartOfAccountsTest {
    private Database database;
    private ChartOfAccounts chartOfAccounts;

    private String PATH = System.getProperty("user.dir") + "/database/";
    private String fileName = "accounts.db";

    @Before
    public void initialize() {
        this.database = new Database(PATH, fileName);
        chartOfAccounts = new ChartOfAccounts(database);
    }

//     Check that the account list created is initially empty
    @Test
    public void accountsListIsEmptyAtBeginning() {
        assertEquals(0, database.selectAllAccounts().size());
    }

//    Check that account type cannot be something other than Asset, Liability, or Equity
    @Test
    public void newAccountMustBeAssetLiabilityOrEquity() {
        String type = "Fraud";
        chartOfAccounts.add("Test application.Account", type);
        assertEquals(0, database.selectAllAccounts().size());
    }

    @Test
    public void newAccountIsNotADuplicate() {
        String type = "Asset";
        chartOfAccounts.add("application.Account 1", type);
        assertFalse(chartOfAccounts.checkDuplicateAccount("application.Account 1"));
        assertTrue(chartOfAccounts.checkDuplicateAccount("application.Account 2"));
    }

    @Test
    public void accountCanBeDeleted() {
        chartOfAccounts.add("Test application.Account 1", "Asset");
        chartOfAccounts.add("Test application.Account 2", "Liability");
        chartOfAccounts.add("Test application.Account 3", "Equity");
        assertEquals(3, database.selectAllAccounts().size());
        chartOfAccounts.remove(2000);
        assertEquals(2, database.selectAllAccounts().size());
        assertFalse(chartOfAccounts.checkAccountExists(2000));
    }

    @Test
    public void getAccountNumberSuccessfullyRetrievesNumber() {
        chartOfAccounts.add("Test Account 1", "Asset");
        assertEquals(1000, database.getLastAccountNumber("Asset"), 0.0);
    }

//     Tests for Asset accounts list
    @Test
    public void assetListIsEmptyAtBeginning() {
        String type = "Asset";
        assertEquals(0, database.selectByAccountType("Asset").size());
    }

    @Test
    public void addingAccountGrowsAssetListByOne() {
        String type = "Asset";
        chartOfAccounts.add("Asset Account", type);
        assertEquals(1, database.selectAllAccounts().size());
    }

    @Test
    public void addedAssetAccountIsInList() {
        String type = "Asset";
        chartOfAccounts.add("Asset Account", type);
        assertEquals("Asset Account", database.getAccountName(
                database.getLastAccountNumber("Asset")));
    }

    @Test
    public void accountNumberIsAppropriateForAssetAccounts() {
        String type = "Asset";
        chartOfAccounts.add("Asset Account", type);
        assertEquals(1000, database.getLastAccountNumber(type), 0.0);
    }

    @Test
    public void balanceForAssetAccountsCanBeSet() {
        String type = "Asset";
        chartOfAccounts.add("Asset Account", type);
        database.updateBalance(database.getLastAccountNumber(type), 1.23);
        assertEquals(1.23, database.getAccountBalance(
                database.getLastAccountNumber(type)), 0.0);
    }

//     Tests for all Liability accounts list
    @Test
    public void liabilityListIsEmptyAtBeginning() {
        String type = "Liability";
        assertEquals(0, database.selectByAccountType(type).size());
    }

    @Test
    public void addingAccountGrowsLiabilityListByOne() {
        String type = "Liability";
        chartOfAccounts.add("Liability Account", type);
        assertEquals(1, database.selectByAccountType(type).size());
    }

    @Test
    public void addedLiabilityAccountIsInList() {
        String type = "Liability";
        chartOfAccounts.add("Liability Account", type);
        assertEquals("Liability Account", database.getAccountName(
                database.getLastAccountNumber(type)));
    }

    @Test
    public void accountNumberIsAppropriateForLiabilityAccounts() {
        String type = "Liability";
        chartOfAccounts.add("Liability Account", type);
        assertEquals(2000, database.getLastAccountNumber(type), 0.0);
    }

    @Test
    public void balanceForLiabilityAccountsCanBeSet() {
        String type = "Liability";
        chartOfAccounts.add("Liability Account", type);
        database.updateBalance(database.getLastAccountNumber(type), 1.23);
        assertEquals(1.23, database.getAccountBalance(
                database.getLastAccountNumber(type)), 0.0);
    }

//     Tests for all Equity accounts list
    @Test
    public void equityListIsEmptyAtBeginning() {
        String type = "Equity";
        assertEquals(0, database.selectByAccountType(type).size());
    }

    @Test
    public void addingAccountGrowsEquityListByOne() {
        String type = "Equity";
        chartOfAccounts.add("Equity Account", type);
        assertEquals(1, database.selectByAccountType(type));
    }

    @Test
    public void addedEquityAccountIsInList() {
        String type = "Equity";
        chartOfAccounts.add("Equity Account", type);
        assertEquals("Equity Account", database.getAccountName(
                database.getLastAccountNumber(type)));
    }

    @Test
    public void accountNumberIsAppropriateForEquityAccounts() {
        String type = "Equity";
        chartOfAccounts.add("Equity Account", type);
        assertEquals(3000, database.getLastAccountNumber(type), 0.0);
    }

    @Test
    public void balanceForEquityAccountsCanBeSet() {
        String type = "Equity";
        chartOfAccounts.add("Equity Account", type);
        database.updateBalance(database.getLastAccountNumber(type), 1.23);
        assertEquals(1.23, database.getAccountBalance(
                database.getLastAccountNumber(type)), 0.0);
    }

    @Test
    public void accountBalancesCanBeUpdatedByUsingAccountNumber() {
        String type = "Asset";
        chartOfAccounts.add("Account 1", type);
        chartOfAccounts.add("Account 2", type);
        database.updateBalance(1000, 1.23);
        database.updateBalance(1010, 2.46);
        assertEquals(1.23, database.getAccountBalance(1000), 0.0);
        assertEquals(2.46, database.getAccountBalance(1010), 0.0);
    }

//    @Test
//    public void doubleEntryFunctionWorks() {
//        chartOfAccounts.add("Cash", "Asset");
//        chartOfAccounts.add("Accounts Payable", "Liability");
//        chartOfAccounts.doubleEntry(1000, 2000, 12345);
//        assertEquals(12345, chartOfAccounts.getAccountBalance(1000), 0.0);
//        assertEquals(12345, chartOfAccounts.getAccountBalance(2000), 0.0);
//    }

    @Test
    public void checkForAccountExistsFunctionsProperly() {
        chartOfAccounts.add("Cash", "Asset");
        assertTrue(chartOfAccounts.checkAccountExists(1000));
        assertFalse(chartOfAccounts.checkAccountExists(2000));
    }
}