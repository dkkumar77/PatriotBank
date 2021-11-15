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

    @FXML
    void handleTransfer(ActionEvent event) {
        Database e = new Database();
        if(event.getSource().equals(transferButton)) {
            double amt = Double.parseDouble(amountField.getText());
            double bal = e.getBalance(username);
            String user2 = userField.getText();
            if(e.checkDatabase(user2)) {
                if(amt > bal) {
                    textLabel.setText("Insufficient Funds");
                }
                else {
                    e.updateBalance(username, (e.getBalance((username)) - amt));
                    e.updateBalance(user2, (e.getBalance((user2)) + amt));
                    textLabel.setText("Transfer Complete");
                }
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
