package boot;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Controller {
    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField password;

    @FXML
    private JFXButton submit;

    @FXML
    private JFXButton create;

    @FXML
    private JFXButton help;

    String pass = "";
    String user = "";


    @FXML
    void handleSubmit(ActionEvent event) {

        if (event.getSource().equals(submit)) {
            pass = this.password.getText();
            user = this.username.getText();

        }
    }


    private boolean passwordVerifyer(String password) {
        if (password.contains(" ")) {
            return true;


        }
        return false;
    }


    private void clearParameters() {
        this.pass = "";
        this.user = "";


    }
}
