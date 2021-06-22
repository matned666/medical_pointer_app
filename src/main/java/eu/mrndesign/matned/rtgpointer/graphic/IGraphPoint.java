package eu.mrndesign.matned.rtgpointer.graphic;

import eu.mrndesign.matned.rtgpointer.model.IPoint;
import eu.mrndesign.matned.rtgpointer.model.PointColor;
import javafx.scene.canvas.GraphicsContext;

public interface IGraphPoint{

    int POINT_RADIUS = 5;

    double getX();
    double getY();
    void setPoint(double x, double y);
    PointColor getPointColor();

    void draw(GraphicsContext context);

    static IGraphPoint applyData(IPoint model){
        return new GraphPoint(model.getX(), model.getY(), model.getPointColor());
    }


}
