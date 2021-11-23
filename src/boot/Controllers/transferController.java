package boot.Controllers;

import boot.userInstance;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class transferController {

    @FXML
    private JFXTextField userField;

    @FXML
    private JFXTextField amountField;

    @FXML
    private JFXButton transferButton;

    @FXML
    private Label textLabel;

    private String username;
    private String username2;

    @FXML
    void handleTransfer(ActionEvent event) {
        Database e = new Database();
        if(event.getSource().equals(transferButton)) {
            username2 = userField.getText();
            double amt = Double.parseDouble(amountField.getText());
            double bal = e.getBalance(username);
            double bal2 = e.getBalance(username2);
            if(amt > bal) {
                textLabel.setText("Insufficient Funds");
                return;
            }
            e.updateBalance(username, (bal - amt));
            e.updateBalance(username2, (bal2 + amt));
            textLabel.setText("Transfer Complete");
        }
            /*
            else {
                textLabel.setText("Invalid User");
            }
            */
    }

    public void handleTransferTest(Double amt, String sender, String receiver) {
        Database e = new Database();
        double bal = e.getBalance(sender);
        double bal2 = e.getBalance(receiver);
        if(amt > bal) {
            System.out.println("Insufficient Funds");
            return;
        }
        e.updateBalance(sender, (bal - amt));
        e.updateBalance(receiver, (bal2 + amt));
        System.out.println("Transfer Complete");
    }

    public void initialize() {
        username = userInstance.getInstance().getUsername();
    }
}
