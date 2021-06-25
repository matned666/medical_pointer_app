package eu.mrndesign.matned.rtgpointer.widget.pointwidget;

import eu.mrndesign.matned.rtgpointer.model.IPoint;
import eu.mrndesign.matned.rtgpointer.model.PointColor;
import eu.mrndesign.matned.rtgpointer.service.IPointService;
import eu.mrndesign.matned.rtgpointer.service.PointService;
import eu.mrndesign.matned.rtgpointer.widget.IPointList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.concurrent.atomic.AtomicReference;

import static eu.mrndesign.matned.rtgpointer.utils.Variables.CANVAS_HEIGHT;
import static eu.mrndesign.matned.rtgpointer.utils.Variables.CANVAS_WIDTH;

public class ListObject extends AnchorPane implements IListObject {

    private final IPoint point;

    private PointColor color;
    private TextField xTextField;
    private TextField yTextField;
    private Text nameTxt;
    private final IPointService pointService = PointService.getInstance();

    public ListObject(IPoint point, IPointList root) {
        this.point = point;

        this.color = point.getPointColor();
        this.setBackground(new Background(new BackgroundFill(Color.web("rgb(25,25,25)"), CornerRadii.EMPTY, Insets.EMPTY)));
        this.prefWidthProperty().bind(((VBox) root).widthProperty());
        labels(point);
        border();
        textFields();
        deleteBtn();
        changeColorButton();
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

    @Override
    public void setPointColor(PointColor pointColor) {
        this.color = pointColor;
    }

    private void changeColorButton() {
        Button changeColor = new Button("chnage color");
        changeColor.setStyle(
                "-fx-background-color: black;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-color: grey;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: grey;"+
                        "-fx-font-size:8"
        );
        changeColor.setMaxHeight(5);
        changeColor.setMaxWidth(230);
        AnchorPane.setTopAnchor(changeColor,9.0);
        AnchorPane.setLeftAnchor(changeColor, 100.0);
        this.getChildren().add(changeColor);
        changeColor.setOnMouseClicked(x-> {
            color = PointColor.getByNum(point.getPointColor().getColorId()+1);
            point.setColor(color);
            nameTxt.setFill(color.getColor());
            pointService.refreshAll();
        });
    }

    private void deleteBtn() {
        Button deleteBtn = new Button("X");
        deleteBtn.setStyle(
                "-fx-background-color: darkred;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
        );
        deleteBtn.setMaxHeight(15);
        deleteBtn.setMaxWidth(15);
        AnchorPane.setTopAnchor(deleteBtn,30.0);
        AnchorPane.setLeftAnchor(deleteBtn, 255.0);
        this.getChildren().add(deleteBtn);
        deleteBtn.setOnMouseClicked(x-> pointService.removePoint(point));
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
        setFieldsKeyListeners();

    }

    private void labels(IPoint point) {
        nameTxt = new Text(point.getName());
        nameTxt.setFill(color.getColor());
        Text id = new Text("Id: " + point.getId());
        AnchorPane.setTopAnchor(id, 10.0);
        AnchorPane.setLeftAnchor(id, 10.0);
        AnchorPane.setTopAnchor(nameTxt, 10.0);
        AnchorPane.setLeftAnchor(nameTxt, 60.0);
        Text xTxt = new Text("x=");
        Text yTxt = new Text("y=");
        id.setFill(Color.GRAY);
        xTxt.setFill(Color.GRAY);
        yTxt.setFill(Color.GRAY);
        AnchorPane.setTopAnchor(xTxt, 35.0);
        AnchorPane.setLeftAnchor(xTxt, 10.0);
        AnchorPane.setTopAnchor(yTxt, 35.0);
        AnchorPane.setLeftAnchor(yTxt, 130.0);
        this.getChildren().add(nameTxt);
        this.getChildren().add(id);
        this.getChildren().add(xTxt);
        this.getChildren().add(yTxt);
    }

    private void setFieldsKeyListeners() {
        AtomicReference<Double> xPrev = new AtomicReference<>(Double.parseDouble(xTextField.getText()));
        AtomicReference<Double> yPrev = new AtomicReference<>(Double.parseDouble(yTextField.getText()));
        xTextField.setOnKeyReleased(x -> xFieldKeyListener(xPrev,x));
        yTextField.setOnKeyReleased(x -> yFieldKeyListener(yPrev,x));
    }

    private void confirmY(double d) {
        point.setY(d);
        pointService.refreshAll();
    }

    private void confirmX(double d) {
        point.setX(d);
        pointService.refreshAll();
    }

    private void yFieldKeyListener(AtomicReference<Double> yPrev, KeyEvent e) {
        double d = tryDouble(yPrev, yTextField, CANVAS_HEIGHT);
        if (e.getCode().equals(KeyCode.ENTER))
            confirmY(d);
    }

    private void xFieldKeyListener(AtomicReference<Double> xPrev, KeyEvent e) {
        double d = tryDouble(xPrev, xTextField, CANVAS_WIDTH);
        if (e.getCode().equals(KeyCode.ENTER))
            confirmX(d);
    }

    private double tryDouble(AtomicReference<Double> prev, TextField textField, int canvasMeasure) {
        try {
            double d = 0;
            if (!textField.getText().isEmpty())
            {
               d = Double.parseDouble(textField.getText());
            }
            if (d > canvasMeasure) d = canvasMeasure;
            if (d < 0) d = 0;
            prev.set(d);
            return d;

        } catch (Exception ex) {
            textField.setText(prev.get().toString());
            return prev.get();
        }
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
}
