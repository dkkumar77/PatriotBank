package boot.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

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
    private JFXButton closeAccount;

    @FXML
    private JFXTextField accountID;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField dob;

    @FXML
    private AnchorPane info, change;

    DATABASE e;

    @FXML
    void handleAccountInfo(ActionEvent event) {

        if(event.getSource().equals(accountInfo)){
            change.toBack();
            info.toFront();


        }
    }

    @FXML
    void handleChangePassword(ActionEvent event) {
        if(event.getSource().equals(changePass)){
            info.toBack();
            change.toFront();
        }

    }


    @FXML
    void handleBack(ActionEvent event){
        if(event.getSource().equals(event));{


        }


    }
    @FXML
    void handleCloseAccount(ActionEvent event) {

    }

    public void setAll(String user ){
        DATABASE e = new DATABASE();
        accountID.setText(String.valueOf(e.getAccountID(user)));
        username.setText(user);
        email.setText(e.getEmail(user));
        dob.setText(e.getDOB(user));



        info.toBack();
        info.toBack();

    }

}
