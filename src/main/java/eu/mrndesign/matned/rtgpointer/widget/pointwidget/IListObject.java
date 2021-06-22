package eu.mrndesign.matned.rtgpointer.widget.pointwidget;

import eu.mrndesign.matned.rtgpointer.model.IPoint;

public interface IListObject extends IGraphObject{

    IPoint getPoint();

    static IListObject applyData(IPoint model){
        return new ListObject(model);
    }

}
