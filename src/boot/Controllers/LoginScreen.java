package boot.Controllers;

import boot.BCrypt.BCrypt;
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

/**
 * Holds methods to handle events at BootScene.fxml
 * */
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

    /*changed from no access modifier to public access modifier*/
    public String username = "";
    public String password = "";

    private Parent root;
    private Stage stage;
    private Scene scene;

    /**
     * Handles the event of clicking loginButton at BootScene.fxml
     * Redirects to HomeScreen.fxml once user name and password is verified
     * @param event An event representing loginButton been clicked
     * */
    @FXML
    void handleLogin(ActionEvent event) throws IOException {

        try {
            BCrypt b = new BCrypt();

            if (event.getSource().equals(loginButton)) {
                userError.toBack();
                passError.toBack();
                if (userField.getText().equals("") || passField.getText().equals("")) {
                    userField.setText("");
                    passField.setText("");
                    userError.setText("Empty Username or Password");
                    userError.toFront();
                } else {
                    if ((checkDatabase(userField.getText().toLowerCase(), b.hashPass(passField.getText())))) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(PathModel.HomeScreen));
                        root = loader.load();
                        homeController controller = loader.getController();
                        controller.passData(userField.getText());
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        userError.toFront();
                        passError.toFront();
                        userError.setText("ERROR");
                        passError.setText("ERROR");
                    }
                }
            }
        }catch(RuntimeException e){
            userField.setText("");
            passField.setText("");
            userError.setText("Wrong username/password");
            userError.toFront();
        }
    }

    /**
     * Handles the event of clicking createButton at BootScene.fxml
     * Redirects to createScene.fxml to allow user to create a new account
     * @param event An event representing loginButton been clicked
     * */
    @FXML
    void handleCreate(ActionEvent event) throws IOException {
        if(event.getSource().equals(createButton)){
            sceneChanger(PathModel.CreateScene, event);
        }
    }


    /**
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

    /**
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

    /**
     * To be updated
     * */
    @FXML
    void handlePassword(ActionEvent event) {

    }

    /**
     * Checks if username's password equals to parameter password
     * @param username User's username
     * @param password string to be verified if is user's password
     * @return true if user's password matches password parameter, false if otherwise
     * */
    boolean checkDatabase(String username, String password){

        Database e = new Database();
        if(e.getPassword(username).equals(password)){
            return true;
        }
        return false;
    }

    /**
     * To be updated
     * @param path To be updated
     * @param event To be updated
     * */
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

    /**
     *  clears username and password parameters
     * */
    private void clearParameters() {
        this.username = "";
        this.password = "";

    }

    /**
     * To be updated
     * @param username To be updated
     * @param password To be updated
     * @return To be updated
     * */
    private boolean passwordVerifyer(String username, String password) {
        if (password.contains(" ")) {
            return true;
        }
        return false;
    }

    /**
     * Checks if userField text field is empty
     * @return return true if userField text field is empty, false otherwise
     * */
    private boolean checkName() {
        if(userField.getText() == null || userField.getText().trim().isEmpty()) {
            userError.setText("Please enter username.");
            return false;
        }
        userError.setText("");
        return true;
    }

    /**
     * Checks if passField text field is empty
     * @return return true if passField text field is empty, false otherwise
     * */
    private boolean checkPassword() {
        if(passField.getText() == null || passField.getText().trim().isEmpty()) {
            passError.setText("Please enter password.");
            return false;
        }
        passError.setText("");
        return true;
    }
}


