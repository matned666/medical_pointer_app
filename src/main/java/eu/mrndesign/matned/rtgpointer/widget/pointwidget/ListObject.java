package eu.mrndesign.matned.rtgpointer.widget.pointwidget;

import eu.mrndesign.matned.rtgpointer.model.IPoint;
import eu.mrndesign.matned.rtgpointer.model.PointColor;
import eu.mrndesign.matned.rtgpointer.service.IPointService;
import eu.mrndesign.matned.rtgpointer.service.PointService;
import eu.mrndesign.matned.rtgpointer.widget.IPointList;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.concurrent.atomic.AtomicReference;

public class ListObject extends AnchorPane implements IListObject {

    private IPoint point;

    private String name;
    private double x;
    private double y;
    private PointColor color;
    private IPointList root;
    private TextField xTextField;
    private TextField yTextField;
    private final IPointService pointService = PointService.getInstance();

    public ListObject(IPoint point, IPointList root) {
        this.point = point;
        this.root = root;

        this.x = point.getX();
        this.y = point.getY();
        this.color = point.getPointColor();
        this.name = point.getName();
        this.setBackground(new Background(new BackgroundFill(Color.web("rgb(25,25,25)"), CornerRadii.EMPTY, Insets.EMPTY)));
        this.prefWidthProperty().bind(((VBox) root).widthProperty());
        labels(point);
        border();
        textFields();

    }

    private void textFields() {
        xTextField = new TextField ();
        yTextField = new TextField ();
        AnchorPane.setTopAnchor(xTextField, 30.0);
        AnchorPane.setLeftAnchor(xTextField, 40.0);
        AnchorPane.setTopAnchor(yTextField, 30.0);
        AnchorPane.setLeftAnchor(yTextField, 160.0);
        xTextField.setMaxWidth(75);
        yTextField.setMaxWidth(75);
        xTextField.setBackground(new Background(new BackgroundFill(Color.web("rgb(125,125,125)"), CornerRadii.EMPTY, Insets.EMPTY)));
        yTextField.setBackground(new Background(new BackgroundFill(Color.web("rgb(125,125,125)"), CornerRadii.EMPTY, Insets.EMPTY)));
        xTextField.setText(point.getX().toString());
        yTextField.setText(point.getY().toString());
        this.getChildren().add(xTextField);
        this.getChildren().add(yTextField);
        setChangePossibility();

    }

    private void setChangePossibility() {
        AtomicReference<Double> xPrev = new AtomicReference<>(Double.parseDouble(xTextField.getText()));
        AtomicReference<Double> yPrev = new AtomicReference<>(Double.parseDouble(yTextField.getText()));
        xTextField.setOnKeyReleased(x -> {
            try {
                double d = Double.parseDouble(xTextField.getText());
                xPrev.set(Double.parseDouble(xTextField.getText()));
                point.setX(Double.parseDouble(xTextField.getText()));
                pointService.refreshAll();
            } catch (Exception e) {
                xTextField.setText(xPrev.get().toString());
            }
        });
        yTextField.setOnKeyReleased(x -> {
            try {
                double d = Double.parseDouble(yTextField.getText());
                yPrev.set(Double.parseDouble(yTextField.getText()));
                point.setY(Double.parseDouble(yTextField.getText()));
                pointService.refreshAll();
            } catch (Exception e) {
                yTextField.setText(yPrev.get().toString());
            }
        });
    }

    private String removeLastCharacter(String str) {
        String result = null;
        if ((str != null) && (str.length() > 0)) {
            result = str.substring(0, str.length() - 1);
        }
        return result;
    }

    private void labels(IPoint point) {
        Text nameTxt = new Text(point.getName());
        nameTxt.setFill(color.getColor());
        AnchorPane.setTopAnchor(nameTxt, 10.0);
        AnchorPane.setLeftAnchor(nameTxt, 10.0);
        Text xTxt = new Text("x=");
        Text yTxt = new Text("y=");
        xTxt.setFill(Color.GRAY);
        yTxt.setFill(Color.GRAY);
        AnchorPane.setTopAnchor(xTxt, 35.0);
        AnchorPane.setLeftAnchor(xTxt, 10.0);
        AnchorPane.setTopAnchor(yTxt, 35.0);
        AnchorPane.setLeftAnchor(yTxt, 130.0);
        this.getChildren().add(nameTxt);
        this.getChildren().add(xTxt);
        this.getChildren().add(yTxt);
    }

    private void border() {
        Text label = new Text();
        AnchorPane.setTopAnchor(label, 50.0);
        AnchorPane.setLeftAnchor(label, 292.0);
        this.getChildren().add(label);
        Rectangle r = new Rectangle();
        r.setX(2);
        r.setY(2);
        r.setWidth(292);
        r.setHeight(65);
        r.setStroke(Color.DARKGRAY);
        r.setFill(Color.TRANSPARENT);
        this.getChildren().add(r);
        this.setStyle("-fx-border-color: black; -fx-border-width: 2px");
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

    @Override
    public void update() {
        xTextField.setText(point.getX().toString());
        yTextField.setText(point.getY().toString());
    }
}
