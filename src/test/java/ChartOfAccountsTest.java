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
    public void chartOfAccountsIsEmptyAtBeginning() {
        assertEquals(0, chartOfAccounts.accountList().size());
    }

    @Test
    public void addingAccountGrowsListByOne() {
        chartOfAccounts.add("Test Account", "Asset");
        assertEquals(1, chartOfAccounts.accountList().size());
    }

    @Test
    public void addedAccountIsInList() {
        chartOfAccounts.add("Test Account", "Asset");
        assertTrue(chartOfAccounts.accountList().contains("Test Account"));
    }

    @Test
    public void addingAccountIsProperlyFormatted() {
        chartOfAccounts.add("Test Account", "Asset");
        assertTrue(chartOfAccounts.formattedList().contains("1. Test Account"));
    }
}