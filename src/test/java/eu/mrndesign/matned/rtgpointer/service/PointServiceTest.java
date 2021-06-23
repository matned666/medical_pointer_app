package eu.mrndesign.matned.rtgpointer.service;

import eu.mrndesign.matned.rtgpointer.widget.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class PointServiceTest {

    private List<IWidget> widgets;
    private final IPointService pointService = PointService.getInstance();
    private IWorkCanvas canvas;
    private IPointList pointList;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        canvas = new WorkCanvas();
        pointList = new PointList();
        pointService.applyWidgets(canvas, pointList);

        Field widgetsListField = PointService.class.getDeclaredField("widgets");
        widgetsListField.setAccessible(true);
        widgets = (List<IWidget>) widgetsListField.get(pointService);
    }


    @Test
    void applyWidgets() {

        assertEquals(widgets.get(0), canvas);
        assertEquals(widgets.get(1), pointList);
    }

    @Test
    void getPoint() {
        pointService.insertNewPoint(100, 100);

        assertEquals(pointService.getPoint(98, 98).orElse(null), pointService.getPoints().get(0));

    }

    @Test
    void insertNewPoint() {
        pointService.insertNewPoint(100, 100);

        assertEquals(100, (double) pointService.getPoints().get(0).getX());
    }

}