import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class UITest {
    private UI ui;
    private Account account;

    @Before
    public void initialize() {
        ui = new UI();
    }

    @Test
    public void newAccountSuccessfullyCreatesAccount() {
        ui.newAccount();
        String name = "Test";
        InputStream input = new ByteArrayInputStream(name.getBytes());
        System.setIn(input);

        String type = "Asset";
        input = new ByteArrayInputStream(type.getBytes());
        System.setIn(input);

        assertEquals("Test", account.getName());
    }
}