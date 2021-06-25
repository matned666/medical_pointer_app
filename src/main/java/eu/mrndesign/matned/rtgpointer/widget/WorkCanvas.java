package eu.mrndesign.matned.rtgpointer.widget;

import eu.mrndesign.matned.rtgpointer.model.IPoint;
import eu.mrndesign.matned.rtgpointer.service.IPointService;
import eu.mrndesign.matned.rtgpointer.service.PointService;
import javafx.scene.canvas.Canvas;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static eu.mrndesign.matned.rtgpointer.utils.Variables.CANVAS_HEIGHT;
import static eu.mrndesign.matned.rtgpointer.utils.Variables.CANVAS_WIDTH;


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
            pointService.setActualTime(Timestamp.valueOf(LocalDateTime.now()).getTime());
            startX = event.getX();
            startY = event.getY();
            if (startX > CANVAS_WIDTH) startX = CANVAS_WIDTH;
            if (startX < 0) startX = 0;
            if (startY > CANVAS_HEIGHT) startY = CANVAS_HEIGHT;
            if (startY < 0) startY = 0;
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
            pointService.setActualTime(Timestamp.valueOf(LocalDateTime.now()).getTime());
            startX = event.getX();
            startY = event.getY();
            currentPoint=pointService.getPoint(startX, startY);
        });
    }

    private void applyShape() {
        pointService.insertNewPoint(startX, startY);
    }


}
