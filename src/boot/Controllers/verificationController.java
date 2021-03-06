package boot.Controllers;

import boot.Model.PathModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Holds methods to handle events at verificationScene.fxml
 * */
public class verificationController {

    @FXML
    private Label verificationText;

    @FXML
    private JFXButton confirmButton;

    @FXML
    private JFXTextField codeField;

    private String code;

    /*changed from no access modifier to public access modifier*/
    public int accountID;
    public String username;

    /**
     * Handles the event of clicking confirmButton at verificationScene.fxml
     * @param event An event representing confirmButton been clicked
     * */
    @FXML
    public void handleConfirm(ActionEvent event) throws IOException {  /*changed from no access modifier to public access modifier*/
        Database e = new Database();

        if(event.getSource().equals(confirmButton)){
            if(Integer.parseInt(codeField.getText()) == ((e.getCODE(username))))
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(PathModel.HomeScreen));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        if(codeField.getText() == null || codeField.getText().trim().isEmpty()) {
            // Error text
        }
    }

    /**
     * Sends verification code to user
     * @param receiver Email's recipient
     * @param code verification code
     * */
   public void sendEmail(String receiver, String code) throws MessagingException {
        // Email setup
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String sender = "cs321team6@gmail.com";
        String senderPassword = "teamsixproject1!";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, senderPassword);
            }
        });

        Message message = verificationMessage(session, receiver, code);

        Transport.send(message);
    }

    /**
     * Wraps up message to be emailed
     * @param session ...
     * @param receiver Email's recipient
     * @param code Verification code
     * @return Returns message to be sent
     * */
   private Message verificationMessage(Session session, String receiver, String code) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("cs321team6@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject("Patriot Bank Verification");
            message.setText("Hello, here is your 4-digit verification code. \n" + code);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Displays confirmation after email is sent
     * @param email User's email
     * @param code verification code
     * @param accountID User's account ID
     * @param username Users's username
     * */
   public void displayVerificationText(String email, String code, int accountID, String username) {
        this.username = username;
        this.accountID = accountID;
        verificationText.setText("An email with a verification code has been sent to \n" + email);
        try {
            sendEmail(email, code);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

