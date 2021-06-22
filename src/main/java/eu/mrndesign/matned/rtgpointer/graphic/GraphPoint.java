package eu.mrndesign.matned.rtgpointer.graphic;

import eu.mrndesign.matned.rtgpointer.model.PointColor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GraphPoint implements IGraphPoint {

    private double x;
    private double y;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private final PointColor pointColor;

    public GraphPoint(double startX, double startY, PointColor pointColor) {
        this.x = startX;
        this.y = startY;
        this.startX = x-POINT_RADIUS;
        this.startY = y-POINT_RADIUS;
        this.endX = x+POINT_RADIUS;
        this.endY = y+POINT_RADIUS;
        this.pointColor = pointColor;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public PointColor getPointColor() {
        return pointColor;
    }


    @Override
    public void draw(GraphicsContext context) {
        context.setFill(pointColor.getColor());
        context.setStroke(Color.DARKGRAY);
        context.fillOval(startX,startY,POINT_RADIUS*2,POINT_RADIUS*2);
        context.strokeOval(startX,startY,POINT_RADIUS*2,POINT_RADIUS*2);
    }

}
