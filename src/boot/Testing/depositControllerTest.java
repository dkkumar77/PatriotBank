package boot.Testing;

import boot.Controllers.Database;
import boot.Controllers.depositController;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class depositControllerTest {

    private static Database e;
    private static depositController controller;

    @BeforeAll
    static void setUp() {
        e = new Database();
        e.addUser("user", "pass", 11111, "John Smith", "test@gmu.edu", "01-01-2000",0.0, 0, 0);
        controller = new depositController();
    }

    @AfterAll
    static void close() {
        e.deleteItem("user");
    }

    @Test
    void handleDeposit1() {
        controller.handleDepositTest(50.0, "user");
        assertEquals(50.0, e.getBalance("user"));
    }

    @Test
    void handleDeposit2() {
        controller.handleDepositTest(50.0, "user");
        assertEquals(100.0, e.getBalance("user"));
    }
}