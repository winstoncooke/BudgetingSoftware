// import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ChartOfAccountsTest {
    private ChartOfAccounts chartOfAccounts;

    @Before
    public void initialize() { chartOfAccounts = new ChartOfAccounts(); }

//     Check that the account list created is initially empty
    @Test
    public void accountsListIsEmptyAtBeginning() {
        assertEquals(0, chartOfAccounts.getAccountList().size());
    }

//    Check that account type cannot be something other than Asset, Liability, or Equity
    @Test
    public void newAccountMustBeAssetLiabilityOrEquity() {
        String type = "Fraud";
        chartOfAccounts.add("Test Account", type);
        assertEquals(0, chartOfAccounts.getAccountList().size());
    }

    @Test
    public void newAccountIsNotADuplicate() {
        String type = "Asset";
        chartOfAccounts.add("Account 1", type);
        assertFalse(chartOfAccounts.checkDuplicateAccount("Account 1"));
        assertTrue(chartOfAccounts.checkDuplicateAccount("Account 2"));
    }

    @Test
    public void accountCanBeDeleted() {
        chartOfAccounts.add("Test Account 1", "Asset");
        chartOfAccounts.add("Test Account 2", "Liability");
        chartOfAccounts.add("Test Account 3", "Equity");
        assertEquals(3, chartOfAccounts.getAccountList().size());
        chartOfAccounts.remove(2000);
        assertEquals(2, chartOfAccounts.getAccountList().size());
        assertFalse(chartOfAccounts.checkAccountExists(2000));
    }

    @Test
    public void getAccountNumberSuccessfullyRetrievesNumber() {
        chartOfAccounts.add("Test Account 1", "Asset");
        assertEquals(1000, chartOfAccounts.getAccountNumber(1000));
    }

//     Tests for Asset accounts list
    @Test
    public void assetListIsEmptyAtBeginning() {
        String type = "Asset";
        assertEquals(0, chartOfAccounts.getAssetAccounts().size());
    }

    @Test
    public void addingAccountGrowsAssetListByOne() {
        String type = "Asset";
        chartOfAccounts.add("Asset Account", type);
        assertEquals(1, chartOfAccounts.getAssetAccounts().size());
    }

    @Test
    public void addedAssetAccountIsInList() {
        String type = "Asset";
        chartOfAccounts.add("Asset Account", type);
        assertEquals("Asset Account", chartOfAccounts.getAssetAccounts().get(0).getName());
    }

    @Test
    public void accountNumberIsAppropriateForAssetAccounts() {
        String type = "Asset";
        chartOfAccounts.add("Asset Account", type);
        assertEquals(1000, chartOfAccounts.getAssetAccounts().get(0).getAccountNumber());
    }

    @Test
    public void balanceForAssetAccountsCanBeSet() {
        String type = "Asset";
        chartOfAccounts.add("Asset Account", type);
        chartOfAccounts.getAssetAccounts().get(0).updateBalance(1.23);
        assertEquals(1.23, chartOfAccounts.getAssetAccounts().get(0).getBalance(), 0.0);
    }

//     Tests for all Liability accounts list
    @Test
    public void liabilityListIsEmptyAtBeginning() {
        String type = "Liability";
        assertEquals(0, chartOfAccounts.getLiabilityAccounts().size());
    }

    @Test
    public void addingAccountGrowsLiabilityListByOne() {
        String type = "Liability";
        chartOfAccounts.add("Liability Account", type);
        assertEquals(1, chartOfAccounts.getLiabilityAccounts().size());
    }

    @Test
    public void addedLiabilityAccountIsInList() {
        String type = "Liability";
        chartOfAccounts.add("Liability Account", type);
        assertEquals("Liability Account", chartOfAccounts.getLiabilityAccounts().get(0).getName());
    }

    @Test
    public void accountNumberIsAppropriateForLiabilityAccounts() {
        String type = "Liability";
        chartOfAccounts.add("Liability Account", type);
        assertEquals(2000, chartOfAccounts.getLiabilityAccounts().get(0).getAccountNumber());
    }

    @Test
    public void balanceForLiabilityAccountsCanBeSet() {
        String type = "Liability";
        chartOfAccounts.add("Liability Account", type);
        chartOfAccounts.getLiabilityAccounts().get(0).updateBalance(1.23);
        assertEquals(1.23, chartOfAccounts.getLiabilityAccounts().get(0).getBalance(), 0.0);
    }

//     Tests for all Equity accounts list
    @Test
    public void equityListIsEmptyAtBeginning() {
        String type = "Equity";
        assertEquals(0, chartOfAccounts.getEquityAccounts().size());
    }

    @Test
    public void addingAccountGrowsEquityListByOne() {
        String type = "Equity";
        chartOfAccounts.add("Equity Account", type);
        assertEquals(1, chartOfAccounts.getEquityAccounts().size());
    }

    @Test
    public void addedEquityAccountIsInList() {
        String type = "Equity";
        chartOfAccounts.add("Equity Account", type);
        assertEquals("Equity Account", chartOfAccounts.getEquityAccounts().get(0).getName());
    }

    @Test
    public void accountNumberIsAppropriateForEquityAccounts() {
        String type = "Equity";
        chartOfAccounts.add("Equity Account", type);
        assertEquals(3000, chartOfAccounts.getEquityAccounts().get(0).getAccountNumber());
    }

    @Test
    public void balanceForEquityAccountsCanBeSet() {
        String type = "Equity";
        chartOfAccounts.add("Equity Account", type);
        chartOfAccounts.getEquityAccounts().get(0).updateBalance(1.23);
        assertEquals(1.23, chartOfAccounts.getEquityAccounts().get(0).getBalance(), 0.0);
    }

    @Test
    public void accountBalancesCanBeUpdatedByUsingAccountNumber() {
        String type = "Asset";
        chartOfAccounts.add("Account 1", type);
        chartOfAccounts.add("Account 2", type);
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