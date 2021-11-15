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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Holds methods to handle events at helpScreen.fxml
 * */
public class Help {

    @FXML
    private JFXTextArea messageArea;

    @FXML
    private JFXButton submit;

    @FXML
    private JFXButton back;

    //private String message;

    /*changed from no access modifier to public access modifier*/
    public Parent root;
    public Stage stage;
    public Scene scene;

    Properties properties = new Properties();

    private String sender = "cs321team6@gmail.com";
    private String senderPassword = "teamsixproject1!";

    /**
     * Handles the event of clicking back button at helpScreen.fxml
     * @param event An event representing back button been clicked
     * */
    @FXML
    public void handleBack(ActionEvent event) throws IOException {  /*changed from no access modifier to public access modifier*/

        if(event.getSource().equals(back)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathModel.BootScene));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Handles the event of clicking submit button at helpScreen.fxml
     * @param event An event representing submit button been clicked
     * */
    @FXML
    public void handleSubmit(ActionEvent event) throws MessagingException, IOException {  /*changed from no access modifier to public access modifier*/

        if(event.getSource().equals(submit)){

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sender, senderPassword);
                }
            });
            Message message = feedbackMessage(session);
            Transport.send(message);

            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathModel.BootScene));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            handleBack(event);

        }




    }
    public void initialize() {
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
    }

    private Message feedbackMessage(Session session) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("cs321team6@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("cs321team6@gmail.com"));
            message.setSubject("Patriot Bank Feedback");
            message.setText(messageArea.getText());
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
