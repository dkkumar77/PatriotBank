package boot.Controllers;


import boot.BCrypt.BCrypt;
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

    /*
     * Handles the event of clicking cancelButton at createScene.fxml
     * Redirects back to BootScene.fxml
     * @param event An event representing cancelButton been clicked
     * */
    @FXML
    void handleCancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PathModel.BootScene));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*
     * Handles the event of clicking submitButton at createScene.fxml
     * Gets user data from fields at createScene.fxml and inserts them into database
     * @param event An event representing loginButton been clicked
     * */
    @FXML
    void handleSubmit(ActionEvent event) throws IOException {

        if (event.getSource().equals(submitButton)) {

            /** NEED TO DELETE
             */

            data = getData();

            if(emptyCheck()) {
                if (usernameVerifyer(data[0])) {
                    if (emailVerifyer(data[1])) {
                        if (matchingPasswords(data[2], data[3])) {

                            /*Using jBCrypt, https://www.mindrot.org/projects/jBCrypt/   jBCrypt-0.4.zip*/
                            String hashed = BCrypt.hashpw(data[2], BCrypt.gensalt());
                            //once a string is hashed, it can be reversed.
                            //But we can compare it the stored hashed string with the given String but hashed
                            /*
                            * To check if unscripted password matches hashed password in database
                            * if(BCrypt.checkpw(inputPassword, hashed)){
                            *    //hashed inputPassword equals stored hashed password
                            * }
                            * */

                            //Add to database

                            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathModel.VerificationScene));
                            root = loader.load();
                            verificationController controller = loader.getController();
                            controller.displayVerificationText(data[1]);
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();


                        }else{
                            /*If password doesnt match*/
                            /*Show warning on a textField: "Password doesnt match"*/
                            passField.setText("");
                            passField2.setText("");
                        }
                    }else{
                        /*If email is invalid*/
                        /*Show warning on a textField: "Invalid email"*/
                        emailField.setText("");
                    }
                }else{
                    /*If username includes invalid characters or username is already in use*/
                    /*Show warning on a textField: "Invalid username"*/
                    userField.setText("");
                }
            }else{
                /*If there are empty fields*/
                /*Show warning on a textField: "No empty fields"*/
            }

        }
    }

    /*
    * Clears all fields
    * */
    private void paramaterClearer(){
        userField.setText("");
        passField.setText("");
        passField2.setText("");
        emailField.setText("");
        firstName.setText("");
        lastName.setText("");

    }

    /*
    * Checks for empty fields
    * @return Returns true if all fields have data
    * */
    private boolean emptyCheck() {

        for(int i = 0; i< data.length; i++){
            if(data[i].isEmpty() == true){
                return false;

            }
        }

        return true;

    }

    /*
    * Checks if userField String has a valid name
    * @return Returns true if the userField String contains only letters
    * */
    private boolean usernameVerifyer(String a){
        if(data[0].matches("^[a-zA-Z0-9]*$")){  /*Should also check if username is already in database*/
            return true;
        }

        return false;



    }

    /*
    * Checks that both passField and passField2 are equal
    * @param a String to be checked for equality
    * @param b String to be checked for equality
    * @return Returns true if passField and passField2 are equal
    * */
    private boolean matchingPasswords(String a, String b){

        if(a.equals(b)){
            return true;
        }

        return false;


    }

    /*
    * Process data from fields and store them in a String array
    * @return Returns String array with all data from fields
    * */
    private String [] getData(){

        String [] array = new String[7];

        array[0] = userField.getText();
        array[1] = emailField.getText();
        array[2] = passField.getText();

        array[3] = passField2.getText();

        if(nameChecker(firstName.getText()) && nameChecker(lastName.getText())) {
            array[4] = firstName.getText();
            array[5] = lastName.getText();
        }else{  /*What if names contain illegal characters?*/
            /*Show warning on a textField: "No numbers or special characters"*/
        }

        array[6] = dob.getValue().toString();




        return array;


    }

    /*
    * Check String contains valid characters for a name
    * @param text String to be validated
    * @returns Returns true if given string contains valid characters
    * */
    private boolean nameChecker(String text) {

        if(text.matches("[a-zA-Z]+")){
            return true;
        }
        return false;

    }

    /*
    * Checks if emailField contains a valid email address
    * @param email String to be validated
    * @return Returns true if the email address is valid
    * */
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
