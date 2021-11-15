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
            System.out.println(bal);
            System.out.println(bal2);
            if(e.checkDatabase(username2)) {
                if(amt > bal) {
                    textLabel.setText("Insufficient Funds");
                    return;
                }
                e.updateBalance(username, (bal - amt));
                e.updateBalance(username2, (bal2 + amt));
                textLabel.setText("Transfer Complete");
            }
            else {
                textLabel.setText("Invalid User");
            }
        }
    }

    public void initialize() {
        username = userInstance.getInstance().getUsername();
    }
}
