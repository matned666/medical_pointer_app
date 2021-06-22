package eu.mrndesign.matned.rtgpointer.widget.pointwidget;

import eu.mrndesign.matned.rtgpointer.model.IPoint;
import eu.mrndesign.matned.rtgpointer.model.PointColor;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ListObject extends AnchorPane implements IListObject{

    private IPoint point;

    private String name;
    private double x;
    private double y;
    private PointColor color;

    public ListObject(IPoint point) {
        this.point = point;

        this.x = point.getX();
        this.y = point.getY();
        this.color = point.getPointColor();
        this.name = point.getName();

        Text txt = new Text(point.getName());
        txt.setStyle("-fx-text-inner-color: "+color.getColorName()+";");
        this.setWidth(300);
        this.setHeight(100);
        this.getChildren().add(txt);
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
        return color;
    }

    @Override
    public IPoint getPoint() {
        return this.point;
    }
}
