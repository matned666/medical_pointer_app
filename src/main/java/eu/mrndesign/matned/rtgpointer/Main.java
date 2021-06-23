package eu.mrndesign.matned.rtgpointer;

import eu.mrndesign.matned.rtgpointer.utils.Variables;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        File file = new File(Variables.MAIN_PICTURE);
        Image image = new Image(file.toURI().toString());
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
