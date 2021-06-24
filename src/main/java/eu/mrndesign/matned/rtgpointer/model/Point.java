package eu.mrndesign.matned.rtgpointer.model;

import eu.mrndesign.matned.rtgpointer.utils.Variables;

import java.util.Objects;

import static eu.mrndesign.matned.rtgpointer.utils.Variables.POINT_RADIUS;

public class Point implements IPoint {

    private static final String DEFAULT_POINT_NAME = "Point";
    private String name;
    private final Long pointId;
    private Double x;
    private Double y;
    private final PointColor color;
    private boolean isSelected;

    public Point(Long pointId, Double x, Double y, int colorNumber) {
        this.pointId = pointId;
        this.x = x;
        this.y = y;
        this.color = PointColor.getByNum(colorNumber);
        this.name = DEFAULT_POINT_NAME;
        isSelected = false;
    }

    @Override
    public boolean isInRange(Double x, Double y) {
        return x >= this.x - POINT_RADIUS &&
                x <= this.x + POINT_RADIUS &&
                y >= this.y - POINT_RADIUS &&
                y <= this.y + POINT_RADIUS;
    }

    @Override
    public Long getId() {
        return pointId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getX() {
        return x;
    }

    @Override
    public Double getY() {
        return y;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setX(Double x) {
        this.x = x;
    }

    @Override
    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public void setSelected() {
        this.isSelected = !this.isSelected;
    }

    @Override
    public PointColor getPointColor() {
        return color;
    }

    @Override
    public String save() {
        return new StringBuffer()
                .append(name).append(Variables.STRINGSEPARATOR)
                .append(pointId).append(Variables.STRINGSEPARATOR)
                .append(x).append(Variables.STRINGSEPARATOR)
                .append(y).append(Variables.STRINGSEPARATOR)
                .append(color.getColorName()).append(Variables.STRINGSEPARATOR)
                .append(isSelected ? 1 : 0).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return isSelected == point.isSelected && Objects.equals(name, point.name) && Objects.equals(pointId, point.pointId) && Objects.equals(x, point.x) && Objects.equals(y, point.y) && color == point.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pointId, x, y, color, isSelected);
    }

}
