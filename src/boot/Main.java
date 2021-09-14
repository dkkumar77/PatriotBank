package boot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("bootscene.fxml"));
        primaryStage.setTitle("Patriot Bank V1");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.setMaxHeight(500);
        primaryStage.setMinHeight(500);
        primaryStage.setMaxWidth(800);
        primaryStage.setMinWidth(800);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
