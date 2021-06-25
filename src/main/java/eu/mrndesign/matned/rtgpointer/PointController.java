package eu.mrndesign.matned.rtgpointer;

import eu.mrndesign.matned.rtgpointer.service.IPointService;
import eu.mrndesign.matned.rtgpointer.service.PointService;
import eu.mrndesign.matned.rtgpointer.utils.Variables;
import eu.mrndesign.matned.rtgpointer.widget.IPointList;
import eu.mrndesign.matned.rtgpointer.widget.IWorkCanvas;
import eu.mrndesign.matned.rtgpointer.widget.PointList;
import eu.mrndesign.matned.rtgpointer.widget.WorkCanvas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PointController implements Initializable {

    private Stage primaryStage;

    @FXML
    private Button loadPointsButton;

    @FXML
    private Button savePointsButton;

    @FXML
    private Button clearPointsButton;

    @FXML
    private Button newPictureButton;

    @FXML
    private Label memoryInfo;

    @FXML
    private Label otherInfo;

    @FXML
    private ScrollPane leftScrollPane;

    @FXML
    private AnchorPane screen1;

    @FXML
    private AnchorPane screen2;

    @FXML
    private AnchorPane screen3;

    @FXML
    private AnchorPane screen4;

    public PointController() {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        IPointService mainService = PointService.getInstance();
        screen1.setBackground(Variables.BACKGROUND_IMAGE(Variables.MAIN_PICTURE));
        screen2.setBackground(Variables.BACKGROUND_IMAGE(Variables.MAIN_PICTURE));
        screen3.setBackground(Variables.BACKGROUND_IMAGE(Variables.MAIN_PICTURE));
        screen4.setBackground(Variables.BACKGROUND_IMAGE(Variables.MAIN_PICTURE));

        IWorkCanvas canvas1 = new WorkCanvas();
        IWorkCanvas canvas2 = new WorkCanvas();
        IWorkCanvas canvas3 = new WorkCanvas();
        IWorkCanvas canvas4 = new WorkCanvas();
        IPointList pointList = new PointList();

        leftScrollPane.setContent((Node) pointList);
        leftScrollPane.setStyle("-fx-background: rgb(0,0,0);\n -fx-background-color: rgb(0,0,0); -fx-border-width: 2px; -fx-border-color: rgb(40,40,40)");


        screen1.getChildren().add((Node) canvas1);
        screen2.getChildren().add((Node) canvas2);
        screen3.getChildren().add((Node) canvas3);
        screen4.getChildren().add((Node) canvas4);

        screen1.setStyle("-fx-border-width: 2px; -fx-border-color: rgb(40,40,40)");
        screen2.setStyle("-fx-border-width: 2px; -fx-border-color: rgb(40,40,40)");
        screen3.setStyle("-fx-border-width: 2px; -fx-border-color: rgb(40,40,40)");
        screen4.setStyle("-fx-border-width: 2px; -fx-border-color: rgb(40,40,40)");

        mainService.applyWidgets(canvas1, canvas2, canvas3, canvas4, pointList);
        mainService.appendOtherWidgets(memoryInfo, otherInfo, loadPointsButton, clearPointsButton, savePointsButton, newPictureButton);
        mainService.appendPictureWidgets(screen1,
                screen2,
                screen3,
                screen4);
        mainService.setPrimaryStage(primaryStage);
    }


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
