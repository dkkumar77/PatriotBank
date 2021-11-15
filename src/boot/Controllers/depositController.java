package boot.Controllers;

import boot.userInstance;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class depositController {

    @FXML
    private JFXTextField amountField;

    @FXML
    private JFXButton depositButton;

    @FXML
    private Label textLabel;

    private String username;

    @FXML
    void handleDeposit(ActionEvent event) {
        Database e = new Database();
        if(event.getSource().equals(depositButton)) {
            double amt = Double.parseDouble(amountField.getText());
            e.updateBalance(username,(e.getBalance((username)) + amt));
            textLabel.setText("Deposit Complete");
        }
    }

    public void initialize() {
        username = userInstance.getInstance().getUsername();
    }

}
