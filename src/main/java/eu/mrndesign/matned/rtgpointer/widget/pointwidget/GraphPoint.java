package eu.mrndesign.matned.rtgpointer.widget.pointwidget;

import eu.mrndesign.matned.rtgpointer.model.PointColor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static eu.mrndesign.matned.rtgpointer.utils.Variables.POINT_RADIUS;

public class GraphPoint implements IGraphPoint {

    private final double startX;
    private final double startY;
    private final PointColor pointColor;

    public GraphPoint(double startX, double startY, PointColor pointColor) {
        this.startX = startX-POINT_RADIUS;
        this.startY = startY-POINT_RADIUS;
        this.pointColor = pointColor;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(pointColor.getColor());
        context.setStroke(Color.DARKGRAY);
        context.fillOval(startX,startY,POINT_RADIUS*2,POINT_RADIUS*2);
        context.strokeOval(startX,startY,POINT_RADIUS*2,POINT_RADIUS*2);
    }

}
