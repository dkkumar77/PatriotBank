package boot.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class verificationController {

    @FXML
    private Label verificationText;

    @FXML
    private JFXButton confirmButton;

    @FXML
    private JFXTextField codeField;

    private String code;

    @FXML
    void handleConfirm(ActionEvent event) {
        if(codeField.getText() == null || codeField.getText().trim().isEmpty()) {
            // Error text
        }
    }

   public void initialize() {
        // Generate 4-digit code
        Random randomizer = new Random();
        code = String.format("%04d", randomizer.nextInt(10000));
   }

   private void sendEmail(String receiver) throws MessagingException {
        // Email setup
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String sender = "PatriotBankGMU@gmail.com";
        String senderPassword = "TfcVgy206987";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, senderPassword);
            }
        });

        Message message = verificationMessage(session, receiver);

        Transport.send(message);
    }

   private Message verificationMessage(Session session, String receiver) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("PatriotBankGMU@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject("Patriot Bank Verification");
            message.setText("Hello, here is your 4-digit verification code. \n" + code);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }

   public void displayVerificationText(String email) {
        verificationText.setText("An email with a verification code has been sent to \n" + email);
        try {
            sendEmail(email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

