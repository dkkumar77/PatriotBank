package boot.Controllers;

import boot.BCrypt.BCrypt;
import boot.Model.PathModel;
import boot.userInstance;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Holds methods to handle events at SettingsScreen.fxml
 * */
public class Settings {

    @FXML
    private JFXTextField oldpass;

    @FXML
    private JFXTextField newPass;

    @FXML
    private JFXTextField confirmNewPassword;

    @FXML
    private JFXButton changePass;

    @FXML
    private JFXButton accountInfo;

    @FXML
    private JFXButton closeAccount, submitClose;

    @FXML
    private JFXTextField accountID;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField dob;

    @FXML
    private AnchorPane info, change, delete;


    @FXML
    private JFXPasswordField passwordForClose;

    /*changed from no access modifier to public access modifier*/
    public Database e;
    public String user;

    /**
     * Handles the event of clicking accountInfo button atSettingsScreen.fxml
     * @param event An event representing accountInfo button been clicked
     * */
    @FXML
    public void handleAccountInfo(ActionEvent event) {  /*changed from no access modifier to public access modifier*/

        if(event.getSource().equals(accountInfo)){
            change.toBack();
            delete.toBack();
            clearPreexistingParameters();

            info.toFront();
        }
    }

    /**
     * Clear oldPass text field.
     * */
    public void clearPreexistingParameters() {
        oldpass.setText("");
    }

    /**
     * Handles the event of clicking changePass button atSettingsScreen.fxml
     * @param event An event representing accountInfo button been clicked
     * */
    @FXML
    public void handleChangePassword(ActionEvent event) {  /*changed from no access modifier to public access modifier*/
        if(event.getSource().equals(changePass)){
            info.toBack();
            delete.toBack();
            change.toFront();
        }
    }

    /**
     * To be implemented
     * @param event To be implemented
     * */
    @FXML
    public void handleBack(ActionEvent event){ /*changed from no access modifier to public access modifier*/
        if(event.getSource().equals(event));
        {

        }
    }

    public void initialize() {
        user = userInstance.getInstance().getUsername();
        setAll(user);
    }

    /**
     * Retrieves user's information from database to display
     * @param user User's username
     * */
    public void setAll(String user ){
        Database e = new Database();
        this.user = user;

        accountID.setText(String.valueOf(e.getAccountID(user)));
        username.setText(user);
        email.setText(e.getEmail(user));
        dob.setText(e.getDOB(user));

        info.toBack();
        change.toBack();
        delete.toBack();
    }

    /**
     * Handles the event of clicking closeAccount button atSettingsScreen.fxml
     * @param event An event representing closeAccount button been clicked
     * */
    @FXML
    public void handleCloseAccount(ActionEvent event){
        if(event.getSource().equals(closeAccount)) {
            info.toBack();
            change.toBack();
            delete.toFront();
        }
    }

    /**
     * Handles the event of clicking submitClose button atSettingsScreen.fxml
     * @param event An event representing submitClose button been clicked
     * */
    @FXML
    public void handleSubmitClose(ActionEvent event) throws IOException{
        Database e = new Database();
        BCrypt b = new BCrypt();
        if(event.getSource().equals(submitClose)){

            if(e.getPassword(username.getText()).equals(b.hashPass(passwordForClose.getText()))){
                e.deleteItem(username.getText());

                Stage stage;
                Parent root;

                FXMLLoader loader = new FXMLLoader(getClass().getResource(PathModel.BootScene));
                root = loader.load();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        }
    }

    /**
     * Handles the event of clicking submit button atSettingsScreen.fxml
     * @param actionEvent An event representing submit button been clicked
     * */
    @FXML
    public void handleSubmit(ActionEvent actionEvent) throws IOException {
        Database e = new Database();

        if(oldpass.getText().equals(e.getPassword(user))){
            if(newPass.getText().equals(confirmNewPassword.getText())){

                e.updatePassword(user,newPass.getText());

                newPass.setText("");
                confirmNewPassword.setText("");
                oldpass.setText("");

            }
        }
    }
}
