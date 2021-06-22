package eu.mrndesign.matned.rtgpointer.exception;

public class PointNotFoundException extends RuntimeException{

    public PointNotFoundException() {
        super(ExceptionMessages.POINT_NOT_FOUND_EXCEPTION);
    }
}
