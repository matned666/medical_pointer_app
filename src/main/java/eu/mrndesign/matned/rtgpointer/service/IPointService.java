package eu.mrndesign.matned.rtgpointer.service;

import eu.mrndesign.matned.rtgpointer.model.IPoint;
import eu.mrndesign.matned.rtgpointer.widget.IWidget;
import javafx.scene.control.Labeled;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public interface IPointService {

    void appendOtherWidgets(Labeled... w);

    void refreshAll();
    void refresh(IWidget canvas);
    void applyWidgets(IWidget... widgets);

    List<IPoint> getPoints();
    Optional<IPoint> getPoint(double x, double y);
    void insertNewPoint(double x, double y);
    void selectPoint(IPoint point);

    void setActualTime(long actualTime);

    void setPrimaryStage(Stage primaryStage);

    void appendPictureWidgets(AnchorPane... screen);
}
