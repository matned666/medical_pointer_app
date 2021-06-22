package eu.mrndesign.matned.rtgpointer.service;

import eu.mrndesign.matned.rtgpointer.widget.pointwidget.IGraphPoint;
import eu.mrndesign.matned.rtgpointer.widget.pointwidget.IListObject;
import eu.mrndesign.matned.rtgpointer.model.IPoint;
import eu.mrndesign.matned.rtgpointer.model.Point;
import eu.mrndesign.matned.rtgpointer.widget.*;
import eu.mrndesign.matned.rtgpointer.widget.pointwidget.ListObject;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * PointService singleton class
 * created to have the same instance for all 4 canvas
 */

public class PointService implements IPointService {

    private static IPointService instance;

    public static IPointService getInstance() {
        if (instance == null) {
            synchronized (PointService.class) {
                if (instance == null)
                    instance = new PointService();
            }
        }
        return instance;
    }


    private final List<IPoint> points;
    private final List<IListObject> listObjects;
    private final List<IWidget> widgets;

    private PointService() {
        if (instance != null) {
            throw new IllegalStateException("Cannot create new instance, please use getInstance method instead.");
        }
        points = new ArrayList<>();
        listObjects = new ArrayList<>();
        widgets = new ArrayList<>();
    }

    @Override
    public void refreshAll() {
        for (IWidget w : widgets) {
            refresh(w);
        }
    }

    @Override
    public void refresh(IWidget object) {
        if (object instanceof IWorkCanvas) refreshCanvas(object);
        else if (object instanceof IPointList) refreshPointList(object);
        refreshListObjects();

    }

    @Override
    public void applyWidgets(IWidget... widgets) {
        this.widgets.addAll(Arrays.asList(widgets));
    }

    @Override
    public List<IPoint> getPoints() {
        return points;
    }

    @Override
    public Optional<IPoint> getPoint(double x, double y) {
        return points.stream().filter(point -> point.isInRange(x, y)).findFirst();
    }

    @Override
    public void insertNewPoint(double x, double y) {
        points.add(new Point(getLastId() + 1, x, y, getLastColor() + 1));
    }

    @Override
    public void selectPoint(IPoint point) {
        if (point != null) {
            points.stream().filter(IPoint::isSelected).forEach(IPoint::setSelected);
            point.setSelected();
        }
    }

    @Override
    public void changePosition(IPoint point, double x, double y) {
        if (point != null) {
            point.setX(x);
            point.setY(y);
        }
    }

    @Override
    public void deletePoint(IPoint point) {
        points.remove(points.stream().filter(x -> x.getId().equals(point.getId())).findFirst().orElse(null));
    }


    private void refreshListObjects() {
        listObjects
                .forEach(IListObject::update);
    }

    private void refreshPointList(IWidget obj) {
        for (IPoint point :
                points) {
            if (listObjects.stream().noneMatch(x -> x.getPoint().equals(point))) {
                IListObject lo = new ListObject(point, (IPointList) obj);
                listObjects.add(lo);
                ((VBox) obj).getChildren().add((Node) lo);

            }
        }
        if (points.size() < listObjects.size()) {
            listObjects.forEach(x -> {
                if (points.stream().noneMatch(y -> y.equals(x.getPoint())))
                    ((VBox) obj).getChildren().remove((Node) x);
            });
        }
    }

    private void refreshCanvas(IWidget obj) {
        GraphicsContext gc = getGraphicContext(obj);
        gc.clearRect(0, 0, ((WorkCanvas) obj).getWidth(), ((WorkCanvas) obj).getHeight());
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, ((WorkCanvas) obj).getWidth(), ((WorkCanvas) obj).getHeight());
        for (IPoint point : getPoints()) {
            IGraphPoint.applyData(point).draw(gc);
        }
    }

    private Long getLastId() {
        return getLastPoint() != null ? getLastPoint().getId() : 0L;
    }

    private int getLastColor() {
        return getLastPoint() != null ? getLastPoint().getPointColor().getColorId() : 0;
    }

    private IPoint getLastPoint() {
        if (points.size() > 0) return points.get(points.size() - 1);
        else return null;
    }

    private GraphicsContext getGraphicContext(IWidget canvas) {
        return ((WorkCanvas) canvas).getGraphicsContext2D();
    }

}
