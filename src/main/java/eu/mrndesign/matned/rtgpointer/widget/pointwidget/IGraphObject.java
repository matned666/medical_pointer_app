package eu.mrndesign.matned.rtgpointer.widget.pointwidget;

import eu.mrndesign.matned.rtgpointer.model.PointColor;
import eu.mrndesign.matned.rtgpointer.widget.IWidget;

public interface IGraphObject extends IWidget {

    double getX();
    double getY();
    void setPoint(double x, double y);
    PointColor getPointColor();

}
