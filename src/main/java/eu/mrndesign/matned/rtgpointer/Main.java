package eu.mrndesign.matned.rtgpointer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass()
                .getResource("/picturepointer.fxml"));
        AnchorPane anchorPane = loader.load();
        PointController controller = loader.getController();
        primaryStage.setTitle("RTG Pointer");
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
