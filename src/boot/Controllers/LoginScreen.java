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

        if(event.getSource().equals(loginButton)){
            userError.toBack();
            passError.toBack();
            if((checkDatabase(userField.getText(), passField.getText()))) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(PathModel.HomeScreen));
                root = loader.load();
                homeController controller = loader.getController();
                controller.passData(userField.getText());
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }


        }
        else{

            userError.toFront();
            passError.toFront();
            userError.setText("ERROR");
            passError.setText("ERROR");

        }



    }

    @FXML
    void handleCreate(ActionEvent event) throws IOException {
        if(event.getSource().equals(createButton)){
            sceneChanger(PathModel.CreateScene, event);



        }
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
    void handleHelp(ActionEvent event) throws IOException {
        if(event.getSource().equals(helpButton)){
            sceneChanger(PathModel.HelpScreen, event);


        }

    }

    @FXML
    void handlePassword(ActionEvent event) {

    }

    boolean checkDatabase(String username, String password){
        Database e = new Database();

        if(e.getPassword(username).equals(password)){
            return true;
        }
        return false;

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


