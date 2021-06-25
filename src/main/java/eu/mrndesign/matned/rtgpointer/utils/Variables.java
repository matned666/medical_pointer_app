package eu.mrndesign.matned.rtgpointer.utils;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;

public class Variables {

    public static final int CANVAS_WIDTH = 490;
    public static final int CANVAS_HEIGHT = 400;
    public static final int POINT_RADIUS = 5;
    public static final String MAIN_PICTURE = "src/main/resources/chest.jpg";


    public static final Background BACKGROUND_BLACK = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));

    public static Background BACKGROUND_IMAGE(String pathToImage){
        File file = new File(pathToImage);
        Image image = new Image(file.toURI().toString());
        return new Background(new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(image.getWidth() >= image.getHeight()? 1 : getHeight(image), image.getWidth() <= image.getHeight()? 1: getWidth(image), true, true, false, false)));

    }

    private static double getWidth(Image image) {
        return image.getHeight()/image.getWidth();
    }

    private static double getHeight(Image image) {
        return image.getWidth()/image.getHeight();
    }
}
