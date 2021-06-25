package eu.mrndesign.matned.rtgpointer.widget.pointwidget;

import eu.mrndesign.matned.rtgpointer.model.IPoint;
import eu.mrndesign.matned.rtgpointer.model.PointColor;

public interface IListObject extends IGraphObject{

    IPoint getPoint();

    void update();

    void setPointColor(PointColor pointColor);
}
