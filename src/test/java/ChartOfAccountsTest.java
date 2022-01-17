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
        assertEquals(0, chartOfAccounts.getAccountList().size());
    }

//    Check that account type cannot be something other than Asset, Liability, or Equity
    @Test
    public void newAccountMustBeAssetLiabilityOrEquity() {
        String type = "Fraud";
        chartOfAccounts.add("Test application.Account", type);
        assertEquals(0, chartOfAccounts.getAccountList().size());
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
        assertEquals(3, chartOfAccounts.getAccountList().size());
        chartOfAccounts.remove(2000);
        assertEquals(2, chartOfAccounts.getAccountList().size());
        assertFalse(chartOfAccounts.checkAccountExists(2000));
    }

    @Test
    public void getAccountNumberSuccessfullyRetrievesNumber() {
        chartOfAccounts.add("Test application.Account 1", "Asset");
        assertEquals(1000, chartOfAccounts.getAccountNumber(1000));
    }

//     Tests for Asset accounts list
    @Test
    public void assetListIsEmptyAtBeginning() {
        String type = "Asset";
        assertEquals(0, chartOfAccounts.getAssetList().size());
    }

    @Test
    public void addingAccountGrowsAssetListByOne() {
        String type = "Asset";
        chartOfAccounts.add("Asset application.Account", type);
        assertEquals(1, chartOfAccounts.getAssetList().size());
    }

    @Test
    public void addedAssetAccountIsInList() {
        String type = "Asset";
        chartOfAccounts.add("Asset application.Account", type);
        assertEquals("Asset application.Account", chartOfAccounts.getAssetList().get(
                chartOfAccounts.getAssetList().size() - 1).getName());
    }

    @Test
    public void accountNumberIsAppropriateForAssetAccounts() {
        String type = "Asset";
        chartOfAccounts.add("Asset application.Account", type);
        assertEquals(1000, chartOfAccounts.getAssetList().get(
                chartOfAccounts.getAssetList().size() - 1).getAccountNumber());
    }

    @Test
    public void balanceForAssetAccountsCanBeSet() {
        String type = "Asset";
        chartOfAccounts.add("Asset application.Account", type);
        chartOfAccounts.getAssetList().get(chartOfAccounts.getAssetList().size()
                - 1).updateBalance(1.23);
        assertEquals(1.23, chartOfAccounts.getAssetList().get(
                chartOfAccounts.getAssetList().size() - 1).getBalance(), 0.0);
    }

//     Tests for all Liability accounts list
    @Test
    public void liabilityListIsEmptyAtBeginning() {
        String type = "Liability";
        assertEquals(0, chartOfAccounts.getLiabilityList().size());
    }

    @Test
    public void addingAccountGrowsLiabilityListByOne() {
        String type = "Liability";
        chartOfAccounts.add("Liability application.Account", type);
        assertEquals(1, chartOfAccounts.getLiabilityList().size());
    }

    @Test
    public void addedLiabilityAccountIsInList() {
        String type = "Liability";
        chartOfAccounts.add("Liability application.Account", type);
        assertEquals("Liability application.Account", chartOfAccounts.getLiabilityList().get(
                chartOfAccounts.getLiabilityList().size() - 1).getName());
    }

    @Test
    public void accountNumberIsAppropriateForLiabilityAccounts() {
        String type = "Liability";
        chartOfAccounts.add("Liability application.Account", type);
        assertEquals(2000, chartOfAccounts.getLiabilityList().get(
                chartOfAccounts.getLiabilityList().size() - 1).getAccountNumber());
    }

    @Test
    public void balanceForLiabilityAccountsCanBeSet() {
        String type = "Liability";
        chartOfAccounts.add("Liability application.Account", type);
        chartOfAccounts.getLiabilityList().get(
                chartOfAccounts.getLiabilityList().size() - 1).updateBalance(1.23);
        assertEquals(1.23, chartOfAccounts.getLiabilityList().get(
                chartOfAccounts.getLiabilityList().size() - 1).getBalance(), 0.0);
    }

//     Tests for all Equity accounts list
    @Test
    public void equityListIsEmptyAtBeginning() {
        String type = "Equity";
        assertEquals(0, chartOfAccounts.getEquityList().size());
    }

    @Test
    public void addingAccountGrowsEquityListByOne() {
        String type = "Equity";
        chartOfAccounts.add("Equity application.Account", type);
        assertEquals(1, chartOfAccounts.getEquityList().size());
    }

    @Test
    public void addedEquityAccountIsInList() {
        String type = "Equity";
        chartOfAccounts.add("Equity application.Account", type);
        assertEquals("Equity application.Account", chartOfAccounts.getEquityList().get(
                chartOfAccounts.getEquityList().size() - 1).getName());
    }

    @Test
    public void accountNumberIsAppropriateForEquityAccounts() {
        String type = "Equity";
        chartOfAccounts.add("Equity application.Account", type);
        assertEquals(3000, chartOfAccounts.getEquityList().get(
                chartOfAccounts.getEquityList().size() - 1).getAccountNumber());
    }

    @Test
    public void balanceForEquityAccountsCanBeSet() {
        String type = "Equity";
        chartOfAccounts.add("Equity application.Account", type);
        chartOfAccounts.getEquityList().get(
                chartOfAccounts.getEquityList().size() - 1).updateBalance(1.23);
        assertEquals(1.23, chartOfAccounts.getEquityList().get(
                chartOfAccounts.getEquityList().size() - 1).getBalance(), 0.0);
    }

    @Test
    public void accountBalancesCanBeUpdatedByUsingAccountNumber() {
        String type = "Asset";
        chartOfAccounts.add("application.Account 1", type);
        chartOfAccounts.add("application.Account 2", type);
        chartOfAccounts.getAccount(1000).updateBalance(1.23);
        chartOfAccounts.getAccount(1010).updateBalance(2.46);
        assertEquals(1.23, chartOfAccounts.getAccountBalance(1000), 0.0);
        assertEquals(2.46, chartOfAccounts.getAccountBalance(1010), 0.0);
    }

    @Test
    public void doubleEntryFunctionWorks() {
        chartOfAccounts.add("Cash", "Asset");
        chartOfAccounts.add("Accounts Payable", "Liability");
        chartOfAccounts.doubleEntry(1000, 2000, 12345);
        assertEquals(12345, chartOfAccounts.getAccountBalance(1000), 0.0);
        assertEquals(12345, chartOfAccounts.getAccountBalance(2000), 0.0);
    }

    @Test
    public void checkForAccountExistsFunctionsProperly() {
        chartOfAccounts.add("Cash", "Asset");
        assertTrue(chartOfAccounts.checkAccountExists(1000));
        assertFalse(chartOfAccounts.checkAccountExists(2000));
    }
}