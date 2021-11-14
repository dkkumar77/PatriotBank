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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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

    private String message;

    /*changed from no access modifier to public access modifier*/
    public Parent root;
    public Stage stage;
    public Scene scene;

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
    public void handleSubmit(ActionEvent event) {  /*changed from no access modifier to public access modifier*/

        if(event.getSource().equals(submit)){
            message = messageArea.toString();


            /*
            SEND MESSAGE TO 321 TEAM EMAIL
             */

        }

    }

}
