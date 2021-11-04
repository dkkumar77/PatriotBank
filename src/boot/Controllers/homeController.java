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

public class homeController {

    @FXML
    private JFXButton financeButton;

    @FXML
    private JFXButton settingsButton;

    @FXML
    private JFXButton outButton;

    @FXML
    private StackPane stackPane;

    AnchorPane finance, settings;
    
    public void initialize() throws IOException {
        finance = FXMLLoader.load(getClass().getResource(PathModel.FinanceScene));
        settings = FXMLLoader.load(getClass().getResource(PathModel.SettingsScreen));
        stackPane.getChildren().add((Node) settings);
        stackPane.getChildren().add((Node) finance);
    }

    @FXML
    void handleFinance(ActionEvent event) {
        finance.toFront();
    }

    @FXML
    void handleSettings(ActionEvent event) {
        settings.toFront();
    }

    @FXML
    void handleOut(ActionEvent event) throws IOException {

        if(event.getSource().equals(outButton)) {
            sceneChanger(PathModel.BootScene, event);

        }

        /*
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
         */
    }



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

}
