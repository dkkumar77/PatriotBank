package boot.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.NumberFormat;

public class financeController {

    @FXML
    private Label balance;

    @FXML
    private JFXButton transferButton;

    @FXML
    private JFXButton depositButton;

    @FXML
    private JFXButton withdrawButton;

    @FXML
    void handleTransfer(ActionEvent event) throws IOException {
        showBox("transfer");
    }

    @FXML
    void handleDeposit(ActionEvent event) throws IOException {
        showBox("deposit");
    }

    @FXML
    void handleWithdraw(ActionEvent event) throws IOException {
        showBox("withdraw");
    }

    private void updateBalance() {
        // Get balance from database
        double bal = 1000.99;
        NumberFormat money = NumberFormat.getInstance();
        balance.setText("$" + money.format(bal));
    }

    private void showBox(String type) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/boot/View/" + type + "Box.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void initialize() {
        updateBalance();
    }
}
