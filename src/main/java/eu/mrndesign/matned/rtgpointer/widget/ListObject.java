package eu.mrndesign.matned.rtgpointer.widget;

import eu.mrndesign.matned.rtgpointer.model.IPoint;
import javafx.scene.layout.AnchorPane;

public class ListObject extends AnchorPane implements IListObject{

    private IPoint point;

    public ListObject(IPoint point) {
        this.point = point;
    }
}
