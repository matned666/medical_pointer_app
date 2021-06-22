package eu.mrndesign.matned.rtgpointer.widget;

import eu.mrndesign.matned.rtgpointer.model.IPoint;
import javafx.scene.layout.VBox;

public class PointList extends VBox implements IPointList{

    public PointList() {
    }


    @Override
    public void addObject(IPoint point) {
        this.getChildren().add(new ListObject(point));
    }
}
