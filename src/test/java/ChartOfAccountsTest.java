// import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ChartOfAccountsTest {
    private ChartOfAccounts chartOfAccounts;

    @Before
    public void initialize() {
        chartOfAccounts = new ChartOfAccounts();
    }

    // Tests for all accounts list
    @Test
    public void accountsListIsEmptyAtBeginning() {
        assertEquals(0, chartOfAccounts.accountList().size());
    }

    @Test
    public void addingAccountGrowsAccountListByOne() {
        chartOfAccounts.add("Test Account", "Asset");
        assertEquals(1, chartOfAccounts.accountList().size());
    }

    @Test
    public void addedAccountIsInList() {
        chartOfAccounts.add("Test Account", "Asset");
        assertEquals("Test Account", chartOfAccounts.accountList().get(0).getName());
    }

    @Test
    public void balanceForAccountsCanBeSet() {
        chartOfAccounts.add("Test Account", "Asset");
        chartOfAccounts.accountList().get(0).setBalance(1.23);
        assertEquals(1.23, chartOfAccounts.accountList().get(0).getBalance(), 0.0);
    }

    // Tests for Asset accounts list
    @Test
    public void assetListIsEmptyAtBeginning() {
        assertEquals(0, chartOfAccounts.assetList().size());
    }

    @Test
    public void addingAccountGrowsAssetListByOne() {
        chartOfAccounts.addAsset("Test Account", "Asset");
        assertEquals(1, chartOfAccounts.assetList().size());
    }

    @Test
    public void addedAssetAccountIsInList() {
        chartOfAccounts.addAsset("Test Account", "Asset");
        assertEquals("Test Account", chartOfAccounts.assetList().get(0).getName());
    }

    @Test
    public void balanceForAssetAccountsCanBeSet() {
        chartOfAccounts.addAsset("Test Account", "Asset");
        chartOfAccounts.assetList().get(0).setBalance(1.23);
        assertEquals(1.23, chartOfAccounts.assetList().get(0).getBalance(), 0.0);
    }

    // Tests for all Liability accounts list
    @Test
    public void liabilityListIsEmptyAtBeginning() {
        assertEquals(0, chartOfAccounts.liabilityList().size());
    }

    @Test
    public void addingAccountGrowsLiabilityListByOne() {
        chartOfAccounts.addLiability("Test Account", "Liability");
        assertEquals(1, chartOfAccounts.liabilityList().size());
    }

    @Test
    public void addedLiabilityAccountIsInList() {
        chartOfAccounts.addLiability("Test Account", "Liability");
        assertEquals("Test Account", chartOfAccounts.liabilityList().get(0).getName());
    }

    @Test
    public void balanceForLiabilityAccountsCanBeSet() {
        chartOfAccounts.addLiability("Test Account", "Asset");
        chartOfAccounts.liabilityList().get(0).setBalance(1.23);
        assertEquals(1.23, chartOfAccounts.liabilityList().get(0).getBalance(), 0.0);
    }

    // Tests for all Equity accounts list
    @Test
    public void equityListIsEmptyAtBeginning() {
        assertEquals(0, chartOfAccounts.equityList().size());
    }

    @Test
    public void addingAccountGrowsEquityListByOne() {
        chartOfAccounts.addEquity("Test Account", "Equity");
        assertEquals(1, chartOfAccounts.equityList().size());
    }

    @Test
    public void addedEquityAccountIsInList() {
        chartOfAccounts.addEquity("Test Account", "Equity");
        assertEquals("Test Account", chartOfAccounts.equityList().get(0).getName());
    }

    @Test
    public void balanceForEquityAccountsCanBeSet() {
        chartOfAccounts.addEquity("Test Account", "Asset");
        chartOfAccounts.equityList().get(0).setBalance(1.23);
        assertEquals(1.23, chartOfAccounts.equityList().get(0).getBalance(), 0.0);
    }
}