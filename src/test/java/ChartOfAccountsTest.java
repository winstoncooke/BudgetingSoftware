import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
// import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;


public class ChartOfAccountsTest {
    private ChartOfAccounts chartOfAccounts;

    @Before
    public void initialize() {
        chartOfAccounts = new ChartOfAccounts();
    }

    @Test
    public void accountsListIsEmptyAtBeginning() {
        assertEquals(0, chartOfAccounts.accountList().size());
    }

    @Test
    public void assetListIsEmptyAtBeginning() {
        assertEquals(0, chartOfAccounts.assetList().size());
    }

    @Test
    public void liabilityListIsEmptyAtBeginning() {
        assertEquals(0, chartOfAccounts.liabilityList().size());
    }

    @Test
    public void equityListIsEmptyAtBeginning() {
        assertEquals(0, chartOfAccounts.equityList().size());
    }

    @Test
    public void addingAccountGrowsAccountListByOne() {
        chartOfAccounts.add("Test Account", "Asset");
        assertEquals(1, chartOfAccounts.accountList().size());
    }

    @Test
    public void addingAccountGrowsAssetListByOne() {
        chartOfAccounts.addAsset("Test Account", "Asset");
        assertEquals(1, chartOfAccounts.assetList().size());
    }

    @Test
    public void addingAccountGrowsLiabilityListByOne() {
        chartOfAccounts.addLiability("Test Account", "Liability");
        assertEquals(1, chartOfAccounts.liabilityList().size());
    }

    @Test
    public void addingAccountGrowsEquityListByOne() {
        chartOfAccounts.addEquity("Test Account", "Equity");
        assertEquals(1, chartOfAccounts.equityList().size());
    }

    @Test
    public void addedAccountIsInList() {
        chartOfAccounts.add("Test Account", "Asset");
        assertTrue(chartOfAccounts.accountList().contains("Test Account"));
    }

    @Test
    public void addedAssetAccountIsInList() {
        chartOfAccounts.addAsset("Test Account", "Asset");
        assertTrue(chartOfAccounts.assetList().contains("Test Account"));
    }

    @Test
    public void addedLiabilityAccountIsInList() {
        chartOfAccounts.addLiability("Test Account", "Liability");
        assertTrue(chartOfAccounts.liabilityList().contains("Test Account"));
    }

    @Test
    public void addedEquityAccountIsInList() {
        chartOfAccounts.addEquity("Test Account", "Equity");
        assertTrue(chartOfAccounts.equityList().contains("Test Account"));
    }
}