package eu.mrndesign.matned.rtgpointer.widget.pointwidget;

import eu.mrndesign.matned.rtgpointer.model.IPoint;
import javafx.scene.canvas.GraphicsContext;

public interface IGraphPoint extends IGraphObject{

    void draw(GraphicsContext context);

    static IGraphPoint applyData(IPoint model){
        return new GraphPoint(model.getX(), model.getY(), model.getPointColor());
    }


}
