package boot.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CreateAnAccount {

    @FXML
    private JFXButton submit;

    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField confirmPassword;

    @FXML
    private JFXDatePicker dob;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXButton back;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleSubmit(ActionEvent event) {

    }

}
