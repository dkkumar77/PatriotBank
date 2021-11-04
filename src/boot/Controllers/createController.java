package boot.Controllers;

import boot.Model.PathModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;

public class createController {

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton submitButton;

    @FXML
    private JFXTextField userField, firstName, lastName;


    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXPasswordField passField, passField2;

    @FXML
    private JFXDatePicker dob;

    private Parent root;
    private Stage stage;
    private Scene scene;

    private String password;
    private String password_verifyer;
    private String email;

    private String username;
    String[] data = new String[7];


    @FXML
    void handleCancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PathModel.BootScene));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleSubmit(ActionEvent event) throws IOException {

        if (event.getSource().equals(submitButton)) {

            /** NEED TO DELETE
             */


            /*


             */


            data = getData();

            if(emptyCheck()) {
                if (usernameVerifyer(data[0])) {
                    if (emailVerifyer(data[1])) {
                        if (matchingPasswords(data[2], data[3])) {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource(PathModel.VerificationScene));
                                root = loader.load();
                                verificationController controller = loader.getController();
                                controller.displayVerificationText(data[1]);
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();


                        }
                    }

                }

            }




        }
    }

    private void paramaterClearer(){
        userField.setText("");
        passField.setText("");
        passField2.setText("");
        emailField.setText("");
        firstName.setText("");
        lastName.setText("");

    }

    private boolean emptyCheck() {

        for(int i = 0; i< data.length; i++){
            if(data[i].isEmpty() == true){
                return false;

            }
        }

        return true;

    }


    private boolean usernameVerifyer(String a){
        if(data[0].matches("^[a-zA-Z0-9]*$")){
            return true;
        }

        return false;



    }

    private boolean matchingPasswords(String a, String b){

        if(a.equals(b)){
            return true;
        }

        return false;


    }

    private String [] getData(){

        String [] array = new String[7];

        array[0] = userField.getText();
        array[1] = emailField.getText();
        array[2] = passField.getText();

        array[3] = passField2.getText();

        if(nameChecker(firstName.getText()) && nameChecker(lastName.getText())) {
            array[4] = firstName.getText();
            array[5] = lastName.getText();
        }

        array[6] = dob.getValue().toString();




        return array;


    }

    private boolean nameChecker(String text) {

        if(text.matches("[a-zA-Z]+")){
            return true;
        }
        return false;

    }

    private boolean emailVerifyer(String email) {
        try{
            InternetAddress e = new InternetAddress(email);
            e.validate();;
        } catch (AddressException e) {

            return false;

        }
        return true;


    }


}
