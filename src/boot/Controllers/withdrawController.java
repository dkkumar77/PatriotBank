package boot.Controllers;

import boot.userInstance;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class withdrawController {

    @FXML
    private JFXTextField amountField;

    @FXML
    private JFXButton withdrawButton;

    @FXML
    private Label textLabel;

    private String username;

    @FXML
    void handleWithdraw(ActionEvent event) {
        Database e = new Database();
        if(event.getSource().equals(withdrawButton)) {
            double amt = Double.parseDouble(amountField.getText());
            double bal = e.getBalance(username);
            if(amt > bal) {
                textLabel.setText("Insufficient Funds");
            }
            else {
                e.updateBalance(username, (e.getBalance((username)) - amt));
                textLabel.setText("Withdraw Complete");
            }
        }
    }

    public void handleWithdrawTest(double amt, String user) {
        Database e = new Database();
        double bal = e.getBalance(user);
        if(amt > bal) {
            System.out.println("Insufficient Funds");
        }
        else {
            e.updateBalance(user, (e.getBalance((user)) - amt));
            System.out.println("Withdraw Complete");
        }
    }

    public void initialize() {
        username = userInstance.getInstance().getUsername();
    }

}
