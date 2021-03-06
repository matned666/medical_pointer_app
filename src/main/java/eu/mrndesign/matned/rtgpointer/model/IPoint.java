package eu.mrndesign.matned.rtgpointer.model;


public interface IPoint {



    boolean isInRange(Double x, Double y);
    Long getId();
    String getName();
    Double getX();
    Double getY();
    boolean isSelected();
    void setX(Double x);
    void setY(Double y);
    void setSelected();
    PointColor getPointColor();

    void setColor(PointColor byNum);
}
