package boot.Controllers;

import boot.Model.PathModel;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Holds methods to handle events at HomeScreen.fxml
 * */
public class homeController {

    @FXML
    private JFXButton financeButton;

    @FXML
    private JFXButton settingsButton;

    @FXML
    private JFXButton outButton;

    @FXML
    private StackPane stackPane;

    /*changed from no access modifier to public access modifier*/
    public AnchorPane finance, settings;
    public String username;

    /**
     * Initializes controllers at HomeScreen.fxml
     * */
    public void initialize() throws IOException {
        finance = FXMLLoader.load(getClass().getResource(PathModel.FinanceScene));
        settings = FXMLLoader.load(getClass().getResource(PathModel.SettingsScreen));
        stackPane.getChildren().add((Node) settings);
        stackPane.getChildren().add((Node) finance);
    }

    /**
     * Handles the event of clicking financeButton at HomeScreen.fxml
     * Loads FinanceScene.fxml
     * @param event An event representing financeButton been clicked
     * */
    @FXML
    void handleFinance(ActionEvent event) {
        finance.toFront();
    }

    /**
     * Handles the event of clicking financeButton at HomeScreen.fxml
     * Loads SettingsScreen.fxml
     * @param event An event representing settingsButton been clicked
     * */
    @FXML
    void handleSettings(ActionEvent event) throws IOException {
        if(event.getSource().equals(settingsButton)) {
            Parent root;
            Stage stage;
            Scene scene;

            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathModel.SettingsScreen));
            root = loader.load();
            Settings controller = loader.getController();
            controller.setAll(username);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * Handles the event of clicking outButton at HomeScreen.fxml
     * Redirects back to BootScene.fxml
     * @param event An event representing outButton been clicked
     * */
    @FXML
    void handleOut(ActionEvent event) throws IOException {

        if(event.getSource().equals(outButton)) {
            sceneChanger(PathModel.BootScene, event);
        }
    }

    /**
     * To be updated
     * @param path To be updated
     * @param event To be updated
     * */
    private void sceneChanger(String path, ActionEvent event) throws IOException{
        Stage stage;
        Parent root;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * To be updated
     * @param username To be updated
     * */
    public void passData(String username) {
        this.username = username;
    }
}
