package eu.mrndesign.matned.rtgpointer.widget.pointwidget;

import eu.mrndesign.matned.rtgpointer.model.PointColor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static eu.mrndesign.matned.rtgpointer.utils.Variables.POINT_RADIUS;

public class GraphPoint implements IGraphPoint {

    private final long id;
    private final double startX;
    private final double startY;
    private final PointColor pointColor;

    public GraphPoint(Long id, double startX, double startY, PointColor pointColor) {
        this.id = id;
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
        addId(context);
    }

    private void addId(GraphicsContext context) {
        context.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 8));
        context.fillText(id+".", startX-5, startY-1);
    }

}
