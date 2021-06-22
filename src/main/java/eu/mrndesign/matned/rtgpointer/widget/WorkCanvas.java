package eu.mrndesign.matned.rtgpointer.widget;

import eu.mrndesign.matned.rtgpointer.model.IPoint;
import eu.mrndesign.matned.rtgpointer.service.IPointService;
import eu.mrndesign.matned.rtgpointer.service.PointService;
import javafx.scene.canvas.Canvas;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class WorkCanvas extends Canvas implements IWorkCanvas {

    private double startX;
    private double startY;
    private final IPointService pointService;

    private final List<IWidget> allContainers;

    private Optional<IPoint> currentPoint;
    
    public WorkCanvas() {
        allContainers = new LinkedList<>();
        allContainers.add(this);
        this.pointService = PointService.getInstance();
        this.setWidth(CANVAS_WIDTH);
        this.setHeight(CANVAS_HEIGHT);
        setOnClickListener();
        setOnMousePressedListener();
        setOnMouseDragListener();
    }

//     An essential method to refresh all added canvas live.
//    @Override
//    public void applyCanvas(IWidget... canvas) {
//        allContainers.addAll(Arrays.asList(canvas));
//    }





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

//    private void refreshAll() {
//        allContainers.forEach(pointService::refresh);
//    }

    private void applyShape() {
        pointService.insertNewPoint(startX, startY);
    }


}
