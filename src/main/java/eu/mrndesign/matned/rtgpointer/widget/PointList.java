package eu.mrndesign.matned.rtgpointer.widget;

import eu.mrndesign.matned.rtgpointer.widget.pointwidget.ListObject;
import eu.mrndesign.matned.rtgpointer.model.IPoint;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PointList extends VBox implements IPointList{

    public PointList() {
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

    }


    @Override
    public void addObject(IPoint point) {
        this.getChildren().add(new ListObject(point, this));
    }
}
