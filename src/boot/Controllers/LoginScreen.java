package boot.Controllers;

import boot.Model.PathModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginScreen {

    @FXML
    private JFXTextField userField;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton createButton;

    @FXML
    private JFXButton helpButton;

    @FXML
    private JFXPasswordField passField;

    @FXML
    private Hyperlink forgotButton;

    @FXML
    private Label userError;

    @FXML
    private Label passError;

    String username = "";
    String password = "";

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    void handleLogin(ActionEvent event) throws IOException {
        if((checkName() && checkPassword())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/boot/View/HomeScreen.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    void handleCreate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PathModel.CreateScene));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void handleForgot(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(PathModel.ForgotBox));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    void handleHelp(ActionEvent event) {

    }

    @FXML
    void handlePassword(ActionEvent event) {

    }

    /**
     *
     * __________________________________________________________________
     *                 NON-TRIVIAL LIST OF METHODS
     * __________________________________________________________________
     *
     */

    /**
     * private method clearParameters() can't be accessed outside of this class as it simply allows for reset of
     * pass and user variables
     *
     */






    private void clearParameters() {
        this.username = "";
        this.password = "";

    }

    private boolean passwordVerifyer(String username, String password) {
        if (password.contains(" ")) {
            return true;
        }
        return false;
    }

    private boolean checkName() {
        if(userField.getText() == null || userField.getText().trim().isEmpty()) {
            userError.setText("Please enter username.");
            return false;
        }
        userError.setText("");
        return true;
    }

    private boolean checkPassword() {
        if(passField.getText() == null || passField.getText().trim().isEmpty()) {
            passError.setText("Please enter password.");
            return false;
        }
        passError.setText("");
        return true;
    }
}

