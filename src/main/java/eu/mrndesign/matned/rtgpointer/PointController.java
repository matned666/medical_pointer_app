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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class PointController implements Initializable {

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
        screen1.setBackground(Variables.BACKGROUND_RTG_IMAGE);
        screen2.setBackground(Variables.BACKGROUND_RTG_IMAGE);
        screen3.setBackground(Variables.BACKGROUND_RTG_IMAGE);
        screen4.setBackground(Variables.BACKGROUND_RTG_IMAGE);

        IWorkCanvas canvas1 = new WorkCanvas();
        IWorkCanvas canvas2 = new WorkCanvas();
        IWorkCanvas canvas3 = new WorkCanvas();
        IWorkCanvas canvas4 = new WorkCanvas();
        IPointList pointList = new PointList();

        leftScrollPane.setContent((Node) pointList);

        screen1.getChildren().add((Node) canvas1);
        screen2.getChildren().add((Node) canvas2);
        screen3.getChildren().add((Node) canvas3);
        screen4.getChildren().add((Node) canvas4);

        mainService.applyWidgets(canvas1, canvas2, canvas3, canvas4, pointList);
    }


}
