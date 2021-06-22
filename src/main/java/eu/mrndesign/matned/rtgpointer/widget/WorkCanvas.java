package eu.mrndesign.matned.rtgpointer.widget;

import eu.mrndesign.matned.rtgpointer.model.IPoint;
import eu.mrndesign.matned.rtgpointer.service.IPointService;
import eu.mrndesign.matned.rtgpointer.service.PointService;
import javafx.scene.canvas.Canvas;

import java.util.Optional;


public class WorkCanvas extends Canvas implements IWorkCanvas {

    private double startX;
    private double startY;
    private final IPointService pointService;


    private Optional<IPoint> currentPoint;
    
    public WorkCanvas() {
        this.pointService = PointService.getInstance();
        this.setWidth(CANVAS_WIDTH);
        this.setHeight(CANVAS_HEIGHT);
        setOnClickListener();
        setOnMousePressedListener();
        setOnMouseDragListener();
    }


    private void setOnMouseDragListener() {
        this.setOnMouseDragged(event -> {
            startX = event.getX();
            startY = event.getY();
            currentPoint
                    .ifPresentOrElse(
                            x ->{
                                x.setX(startX);
                                x.setY(startY);
                                pointService.refreshAll();
                            },
                            pointService::refreshAll);
        });
    }

    private void setOnClickListener() {
        this.setOnMouseClicked(event -> {
            startX = event.getX();
            startY = event.getY();
            currentPoint
                    .ifPresentOrElse(
                            pointService::selectPoint,
                            () -> {
                                applyShape();
                                pointService.refreshAll();
                            });
        });
    }

    private void setOnMousePressedListener() {
        this.setOnMousePressed(event->{
            startX = event.getX();
            startY = event.getY();
            currentPoint=pointService.getPoint(startX, startY);
        });
    }

    private void applyShape() {
        pointService.insertNewPoint(startX, startY);
    }


}
