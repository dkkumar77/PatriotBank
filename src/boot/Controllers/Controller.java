package boot.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private JFXTextField username;


    @FXML
    private JFXButton submit;

    @FXML
    private JFXButton create;

    @FXML
    private JFXButton help;

    @FXML
    private JFXPasswordField password;

    String pass = "";
    String user = "";


    @FXML
    void handleSubmit(ActionEvent event) throws IOException {

        if (event.getSource().equals(submit)) {
            pass = this.password.getText();
            user = this.username.getText();
            clearParameters();


            /**
             * NEED TO UPDATE
             */
            Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        }

    }

    @FXML
    public void handleCreate(ActionEvent actionEvent) {
    }
    @FXML

    public void handleHelp(ActionEvent actionEvent) {
    }
    @FXML

    public void handlePassword(ActionEvent actionEvent) {
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
        this.pass = "";
        this.user = "";

    }


    private boolean passwordVerifyer(String password) {
        if (password.contains(" ")) {
            return true;

        }
        return false;
    }

}


