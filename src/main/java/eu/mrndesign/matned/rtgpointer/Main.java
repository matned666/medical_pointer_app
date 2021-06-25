package eu.mrndesign.matned.rtgpointer;

import eu.mrndesign.matned.rtgpointer.utils.Variables;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass()
                .getResource("/picturepointer.fxml"));
        AnchorPane anchorPane = loader.load();
        PointController pointController = loader.getController();
        primaryStage.setTitle("RTG Pointer");
        Scene scene = new Scene(anchorPane);

        anchorPane.setBackground(Variables.BACKGROUND_BLACK);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        pointController.setPrimaryStage(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
