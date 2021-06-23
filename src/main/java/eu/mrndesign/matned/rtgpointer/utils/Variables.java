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
    public static final String MAIN_PICTURE = "src/main/resources/turtle-x-ray.jpg";


    public static final Background BACKGROUND_BLACK = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));

    private static final File file = new File(Variables.MAIN_PICTURE);
    private static final Image image = new Image(file.toURI().toString());
    public static final BackgroundImage rtgImage = new BackgroundImage(image,
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
            new BackgroundSize(100, 100, true, true, true, true));
    public static final Background BACKGROUND_RTG_IMAGE = new Background(rtgImage);
}
