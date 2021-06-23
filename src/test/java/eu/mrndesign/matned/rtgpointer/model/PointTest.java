package eu.mrndesign.matned.rtgpointer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void isPointFoundInRange(){
        IPoint point = new Point(0L, 123d, 123d, 6);

        assertTrue(point.isInRange(120d, 120d));
    }



}