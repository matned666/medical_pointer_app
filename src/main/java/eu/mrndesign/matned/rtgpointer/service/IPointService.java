package eu.mrndesign.matned.rtgpointer.service;

import eu.mrndesign.matned.rtgpointer.model.IPoint;
import eu.mrndesign.matned.rtgpointer.widget.IWidget;

import java.util.List;
import java.util.Optional;

public interface IPointService {

    void refreshAll();
    void refresh(IWidget canvas);
    void applyWidgets(IWidget... widgets);

    List<IPoint> getPoints();
    Optional<IPoint> getPoint(double x, double y);
    void insertNewPoint(double x, double y);
    void selectPoint(IPoint point);
    void changePosition(IPoint point, double x, double y);
    void deletePoint(IPoint point);
}
