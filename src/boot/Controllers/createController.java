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
import java.util.Random;

public class createController {

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton submitButton;

    @FXML
    private JFXTextField usernameField, firstName, lastName;


    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXPasswordField passwordField, passwordField2;

    @FXML
    private JFXDatePicker dob;

    private Parent root;
    private Stage stage;
    private Scene scene;


    String[] data = new String[7];
    String code;


    String ACCOUNTID;

    @FXML
    void handleCancel(ActionEvent event) throws IOException {

        if(event.getSource().equals(cancelButton)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathModel.BootScene));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setMaxWidth(816);
            stage.setMinHeight(539);
            stage.setScene(scene);
            stage.show();


        }
    }

    @FXML
    void handleSubmit(ActionEvent event) throws IOException {

        if (event.getSource().equals(submitButton)) {

            data = getData();

            if(emptyCheck()) {
                if (usernameVerifyer(data[0])) {
                    if (emailVerifyer(data[1])) {
                        if (matchingPasswords(data[2], data[3])) {


                            Database e = new Database();
                            Random randomizer = new Random();
                            code = String.format("%04d", randomizer.nextInt(10000));

                            randomizer = new Random();
                            ACCOUNTID = "1" +String.format("%08d", randomizer.nextInt(10000));
                            e.addUser(data[0],data[2],Integer.parseInt(ACCOUNTID),data[4] + " " +data[5],data[1],data[6], 0.0, Integer.parseInt(code) ,0);

                            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathModel.VerificationScene));
                                root = loader.load();
                                verificationController controller = loader.getController();
                                controller.displayVerificationText(data[1], code, Integer.parseInt(ACCOUNTID), data[0]);
                                 stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();


                        }
                    }

                }

            }
            parameterClearer();

        }

    }



    private void parameterClearer(){
        usernameField.setText("");
        passwordField.setText("");
        passwordField2.setText("");
        emailField.setText("");

        firstName.setText("");
        lastName.setText("");

    }





    private boolean emptyCheck() {

        for(int i = 0; i< data.length-1; i++){
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
        array[0] = usernameField.getText();
        array[1] = emailField.getText();
        array[2] = passwordField.getText();
        array[3] = passwordField2.getText();

        if(nameChecker(firstName.getText()) && nameChecker(lastName.getText())) {
            array[4] = firstName.getText();
            array[5] = lastName.getText();
        }

        if(!dob.getValue().toString().isEmpty()) {
            array[6] = dob.getValue().toString();
        }

        return array;


    }

    private boolean nameChecker(String text) {

        if(text.matches("[a-zA-Z]+")){
            return true;
        }
        return false;

    }

    private boolean emailVerifyer(String email) {
        if(!data[1].toLowerCase().contains("@gmu.edu")){
            return false;
        }
            try{
            InternetAddress e = new InternetAddress(email);
            e.validate();;
        } catch (AddressException e) {

            return false;

        }
        return true;


    }


}
