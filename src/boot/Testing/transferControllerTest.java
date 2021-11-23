package boot.Testing;

import boot.Controllers.Database;
import boot.Controllers.transferController;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class transferControllerTest {

    private static Database e;
    private static transferController controller;

    @BeforeAll
    static void setUp() {
        e = new Database();
        e.addUser("user", "pass", 11111, "John Smith", "test@gmu.edu", "01-01-2000",0.0, 0, 0);
        e.addUser("user2", "pass2", 11112, "Jane Smith", "test2@gmu.edu", "01-01-2000",0.0, 0, 0);
        e.updateBalance("user",100.0);
        controller = new transferController();
    }

    @AfterAll
    static void close() {
        e.deleteItem("user");
        e.deleteItem("user2");
    }

    @Test
    void handleTransfer1() {
        controller.handleTransferTest(40.0, "user", "user2");
        assertEquals(60.0, e.getBalance("user"));
        assertEquals(40.0, e.getBalance("user2"));
    }

    @Test
    void handleTransfer2() {
        controller.handleTransferTest(60.0, "user", "user2");
        assertEquals(0.0, e.getBalance("user"));
        assertEquals(100.0, e.getBalance("user2"));
    }

    @Test
    void handleTransfer3() {
        controller.handleTransferTest(1.0, "user", "user2");
        assertEquals(0.0, e.getBalance("user"));
        assertEquals(100.0, e.getBalance("user2"));
    }
}