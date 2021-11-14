package boot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for JavaFX application.
 * */
public class Main extends Application {

    /**
     * Called when the JavaFX application is started.
     * @param primaryStage Where all the visual parts of the JavaFX application are displayed.
     * */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/BootScene.fxml"));
        primaryStage.setTitle("Patriot Bank V1");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.setMaxHeight(539);
        primaryStage.setMinHeight(539);
        primaryStage.setMaxWidth(816);
        primaryStage.setMinWidth(816);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
