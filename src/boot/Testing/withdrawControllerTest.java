package boot.Testing;

import boot.Controllers.Database;
import boot.Controllers.withdrawController;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class withdrawControllerTest {

    private static Database e;
    private static withdrawController controller;

    @BeforeAll
    static void setUp() {
        e = new Database();
        e.addUser("user", "pass", 11111, "John Smith", "test@gmu.edu", "01-01-2000",0.0, 0, 0);
        e.updateBalance("user",100.0);
        controller = new withdrawController();
    }

    @AfterAll
    static void close() {
        e.deleteItem("user");
    }

    @Test
    void handleWithdraw1() {
        controller.handleWithdrawTest(50.0, "user");
        assertEquals(50.0, e.getBalance("user"));
    }

    @Test
    void handleWithdraw2() {
        controller.handleWithdrawTest(50.0, "user");
        assertEquals(0.0, e.getBalance("user"));
    }

    @Test
    void handleWithdraw3() {
        controller.handleWithdrawTest(1.0, "user");
        //Insufficient funds, balance should stay the same
        assertEquals(0.0, e.getBalance("user"));
    }
}