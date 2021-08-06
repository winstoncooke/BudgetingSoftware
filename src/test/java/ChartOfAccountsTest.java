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

//     Tests for all accounts list
    @Test
    public void accountsListIsEmptyAtBeginning() {
        assertEquals(0, chartOfAccounts.accountList().size());
    }

//     Tests for Asset accounts list
    @Test
    public void assetListIsEmptyAtBeginning() {
        String type = "Asset";
        assertEquals(0, chartOfAccounts.accountList(type).size());
    }

    @Test
    public void addingAccountGrowsAssetListByOne() {
        String type = "Asset";
        chartOfAccounts.add("Test Account", type);
        assertEquals(1, chartOfAccounts.accountList(type).size());
    }

    @Test
    public void addedAssetAccountIsInList() {
        String type = "Asset";
        chartOfAccounts.add("Test Account", type);
        assertEquals("Test Account", chartOfAccounts.accountList(type).get(0).getName());
    }

    @Test
    public void balanceForAssetAccountsCanBeSet() {
        String type = "Asset";
        chartOfAccounts.add("Test Account", type);
        chartOfAccounts.accountList(type).get(0).setBalance(1.23);
        assertEquals(1.23, chartOfAccounts.accountList(type).get(0).getBalance(), 0.0);
    }

//     Tests for all Liability accounts list
    @Test
    public void liabilityListIsEmptyAtBeginning() {
        String type = "Liability";
        assertEquals(0, chartOfAccounts.accountList(type).size());
    }

    @Test
    public void addingAccountGrowsLiabilityListByOne() {
        String type = "Liability";
        chartOfAccounts.add("Test Account", type);
        assertEquals(1, chartOfAccounts.accountList(type).size());
    }

    @Test
    public void addedLiabilityAccountIsInList() {
        String type = "Liability";
        chartOfAccounts.add("Test Account", type);
        assertEquals("Test Account", chartOfAccounts.accountList(type).get(0).getName());
    }

    @Test
    public void balanceForLiabilityAccountsCanBeSet() {
        String type = "Liability";
        chartOfAccounts.add("Test Account", type);
        chartOfAccounts.accountList(type).get(0).setBalance(1.23);
        assertEquals(1.23, chartOfAccounts.accountList(type).get(0).getBalance(), 0.0);
    }

//     Tests for all Equity accounts list
    @Test
    public void equityListIsEmptyAtBeginning() {
        String type = "Equity";
        assertEquals(0, chartOfAccounts.accountList(type).size());
    }

    @Test
    public void addingAccountGrowsEquityListByOne() {
        String type = "Equity";
        chartOfAccounts.add("Test Account", type);
        assertEquals(1, chartOfAccounts.accountList(type).size());
    }

    @Test
    public void addedEquityAccountIsInList() {
        String type = "Equity";
        chartOfAccounts.add("Test Account", type);
        assertEquals("Test Account", chartOfAccounts.accountList(type).get(0).getName());
    }

    @Test
    public void balanceForEquityAccountsCanBeSet() {
        String type = "Equity";
        chartOfAccounts.add("Test Account", type);
        chartOfAccounts.accountList(type).get(0).setBalance(1.23);
        assertEquals(1.23, chartOfAccounts.accountList(type).get(0).getBalance(), 0.0);
    }
}