package eu.mrndesign.matned.rtgpointer;

refaimport eu.mrndesign.matned.rtgpointer.service.IPointService;
import eu.mrndesign.matned.rtgpointer.service.PointService;
import eu.mrndesign.matned.rtgpointer.widget.IPointList;
import eu.mrndesign.matned.rtgpointer.widget.IWorkCanvas;
import eu.mrndesign.matned.rtgpointer.widget.PointList;
import eu.mrndesign.matned.rtgpointer.widget.WorkCanvas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class PointController implements Initializable {

    @FXML
    private GridPane pictureGrid;

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
        File file = new File("src/main/resources/turtle-x-ray.jpg");
        Image image = new Image(file.toURI().toString());
        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true, true, true, true));
        IPointService ps = PointService.getInstance();
        screen1.setBackground(new Background(myBI));
        screen2.setBackground(new Background(myBI));
        screen3.setBackground(new Background(myBI));
        screen4.setBackground(new Background(myBI));
        leftScrollPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        pictureGrid.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        IWorkCanvas canvas1 = new WorkCanvas();
        IWorkCanvas canvas2 = new WorkCanvas();
        IWorkCanvas canvas3 = new WorkCanvas();
        IWorkCanvas canvas4 = new WorkCanvas();
        IPointList pointList = new PointList();

        leftScrollPane.setContent((Node) pointList);

        ps.applyWidgets(canvas1, canvas2, canvas3, canvas4, pointList);

        screen1.getChildren().add((Node) canvas1);
        screen2.getChildren().add((Node) canvas2);
        screen3.getChildren().add((Node) canvas3);
        screen4.getChildren().add((Node) canvas4);
    }


}
