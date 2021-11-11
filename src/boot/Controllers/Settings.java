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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

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

    Database e;

    String user;

    @FXML
    void handleAccountInfo(ActionEvent event) {

        if(event.getSource().equals(accountInfo)){
            change.toBack();
            delete.toBack();
            clearPreexistingParameters();

            info.toFront();


        }
    }


    public void clearPreexistingParameters() {
        oldpass.setText("");
    }

    @FXML
    void handleChangePassword(ActionEvent event) {
        if(event.getSource().equals(changePass)){
            info.toBack();
            delete.toBack();
            change.toFront();
        }

    }


    @FXML
    void handleBack(ActionEvent event){
        if(event.getSource().equals(event));
        {

        }

    }

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

    @FXML
    public void handleCloseAccount(ActionEvent event){
        if(event.getSource().equals(closeAccount)) {
            info.toBack();
            change.toBack();
            delete.toFront();

        }

    }


    @FXML
    public void handleSubmitClose(ActionEvent event) throws IOException{

    }

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
