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

    /*
    * Handles the event of clicking loginButton at BootScene.fxml
    * Redirects to HomeScreen.fxml once user name and password is verified
    * @param event An event representing loginButton been clicked
    * */
    @FXML
    void handleLogin(ActionEvent event) throws IOException {

        /**
         * DELETE
         */


        sceneChanger(PathModel.HomeScreen, event);



        /*If the object on which the event initially occurred is the loginButton button*/
        if(event.getSource().equals(loginButton)){
            /*Checks in database if name and password match, if so, move to HomeScreen.fxml*/
            if((checkName() && checkPassword())) {

                sceneChanger(PathModel.HomeScreen, event);
            }

        }




    }

    /*
    * Handles the event of clicking createButton at BootScene.fxml
    * Redirects to createScene.fxml to allow user to create a new account
    * @param event An event representing loginButton been clicked
    * */
    @FXML
    void handleCreate(ActionEvent event) throws IOException {
        /*If the object on which the event initially occurred is the createButton button
        * Move to createScene.fxml
        * */
        if(event.getSource().equals(createButton)){

            sceneChanger(PathModel.CreateScene, event);



        }
    }

    /*
     * Handles the event of clicking forgotButton at BootScene.fxml
     * Redirects to forgotBox.fxml
     * @param event An event representing forgotButton been clicked
     * */
    @FXML
    void handleForgot(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(PathModel.ForgotBox));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /*
    * Handles the event of clicking helpButton at BootScene.fxml
    * Redirects to HelpScreen.fxml
    * @param event An event representing helpButton been clicked
    * */
    @FXML
    void handleHelp(ActionEvent event) throws IOException {
        if(event.getSource().equals(helpButton)){
            sceneChanger(PathModel.HelpScreen, event);

        }

    }

    @FXML
    void handlePassword(ActionEvent event) {

    }

    private void sceneChanger(String path, ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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


