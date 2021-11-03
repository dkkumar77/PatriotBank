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
import javafx.stage.Stage;

import java.io.IOException;

public class createController {

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton submitButton;

    @FXML
    private JFXTextField userField;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField passField;

    @FXML
    private JFXTextField pass2Field;


    private Parent root;
    private Stage stage;
    private Scene scene;

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
        String email = emailField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(PathModel.VerificationScene));

        root = loader.load();

        verificationController controller = loader.getController();
        controller.displayVerificationText(email);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
