package boot.Controllers;

import boot.userInstance;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.text.NumberFormat;

/**
 * Holds methods to handle events related to money transfer.
 * */
public class financeController {

    @FXML
    private Label balance;

    @FXML
    private JFXButton transferButton;

    @FXML
    private JFXButton depositButton;

    @FXML
    private JFXButton withdrawButton;

    private String username;

    @FXML
    void handleTransfer(ActionEvent event) throws IOException {
        showBox("Transfer");
    }

    @FXML
    void handleDeposit(ActionEvent event) throws IOException {
        showBox("Deposit");
    }

    @FXML
    void handleWithdraw(ActionEvent event) throws IOException {
        showBox("Withdraw");
    }

    /**
     * Updates balance from database.
     * @param username User's username.
     * */
    private void updateBalance(String username) {
        // Get balance from database
        double bal = new Database().getBalance(username);

        NumberFormat money = NumberFormat.getInstance();
        balance.setText("$" + money.format(bal));
    }

    /**
     * To be updated
     * */
    private void showBox(String type) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/boot/View/" + type + "Box.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                updateBalance(username);
            }
        });
    }

    public void initialize() {
        username = userInstance.getInstance().getUsername();
        updateBalance(username);
    }
}
