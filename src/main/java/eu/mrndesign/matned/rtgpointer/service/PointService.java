package eu.mrndesign.matned.rtgpointer.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import eu.mrndesign.matned.rtgpointer.utils.Variables;
import eu.mrndesign.matned.rtgpointer.widget.pointwidget.IGraphPoint;
import eu.mrndesign.matned.rtgpointer.widget.pointwidget.IListObject;
import eu.mrndesign.matned.rtgpointer.model.IPoint;
import eu.mrndesign.matned.rtgpointer.model.Point;
import eu.mrndesign.matned.rtgpointer.widget.*;
import eu.mrndesign.matned.rtgpointer.widget.pointwidget.ListObject;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * PointService singleton class
 * created to have the same instance for all 4 canvas
 */

public class PointService implements IPointService {

    private static IPointService instance;

    public static IPointService getInstance() {
        if (instance == null) {
            synchronized (PointService.class) {
                if (instance == null)
                    instance = new PointService();
            }
        }
        return instance;
    }


    private final List<IPoint> points;
    private final List<IListObject> listObjects;
    private final List<IWidget> widgets;
    private Label memoryInfoLabel;
    private Label otherInfoLabel;
    private long actualTime;
    private Stage primaryStage;
    private AnchorPane[] screens;

    private PointService() {
        if (instance != null) {
            throw new IllegalStateException("Cannot create new instance, please use getInstance method instead.");
        }else {
            instance = this;
            points = new ArrayList<>();
            listObjects = new ArrayList<>();
            widgets = new ArrayList<>();
        }
    }

    @Override
    public void appendOtherWidgets(Labeled... w){
        this.memoryInfoLabel = (Label) w[0];
        this.otherInfoLabel = (Label) w[1];
        Button loadPointsButton = (Button) w[2];
        Button clearPointsButton = (Button) w[3];
        Button savePointsButton = (Button) w[4];
        Button newPictureButton = (Button) w[5];
        this.memoryInfoLabel.setText("<<Actual memory usage information>>");
        this.otherInfoLabel.setText("<<Other information>>");
        loadPointsButton.setOnMouseClicked(this::loadPointsButtonAction);
        savePointsButton.setOnMouseClicked(this::savePointsButtonAction);
        clearPointsButton.setOnMouseClicked(this::clearPointsButtonAction);
        newPictureButton.setOnMouseClicked(this::newPictureButtonAction);
    }

    @Override
    public void refreshAll() {

        for (IWidget w : widgets) {
            refresh(w);
        }
        memoryInfoLabel.setText(checkMemoryUsage());
        otherInfoLabel.setText(checkOtherInformation(Timestamp.valueOf(LocalDateTime.now()).getTime() - actualTime));

    }

    @Override
    public void refresh(IWidget object) {
        if (object instanceof IWorkCanvas) refreshCanvas(object);
        else if (object instanceof IPointList) refreshPointList(object);
        refreshListObjects();

    }

    @Override
    public void applyWidgets(IWidget... widgets) {
        this.widgets.addAll(Arrays.asList(widgets));
    }

    @Override
    public List<IPoint> getPoints() {
        return points;
    }

    @Override
    public Optional<IPoint> getPoint(double x, double y) {
        return points.stream().filter(point -> point.isInRange(x, y)).findFirst();
    }

    @Override
    public void insertNewPoint(double x, double y) {
        points.add(new Point(getLastId() + 1, x, y, getLastColor() + 1));
    }

    @Override
    public void selectPoint(IPoint point) {
        if (point != null) {
            points.stream().filter(IPoint::isSelected).forEach(IPoint::setSelected);
            point.setSelected();
        }
    }

    @Override
    public void setActualTime(long actualTime) {
        this.actualTime = actualTime;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void appendPictureWidgets(AnchorPane... screen) {
        this.screens = screen;
    }

    @Override
    public void removePoint(IPoint point) {
        points.remove(point);
        refreshAll();
    }

    private void newPictureButtonAction(MouseEvent x) {
        actualTime = Timestamp.valueOf(LocalDateTime.now()).getTime();
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        Arrays.asList(screens).forEach(d->d.setBackground(Variables.BACKGROUND_IMAGE(selectedFile.getAbsolutePath())));
    }

    private void clearPointsButtonAction(MouseEvent x) {
        actualTime = Timestamp.valueOf(LocalDateTime.now()).getTime();
        points.clear();
        refreshAll();
    }

    private void savePointsButtonAction(MouseEvent x) {
        actualTime = Timestamp.valueOf(LocalDateTime.now()).getTime();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            saveTextToFile(new Gson().toJson(points), file);
        }
    }

    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            alertDialog(e.getMessage());

        }
    }

    private void loadPointsButtonAction(MouseEvent x) {
        actualTime = Timestamp.valueOf(LocalDateTime.now()).getTime();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            clearPointsButtonAction(x);
            loadFromFile(file);
        }
        refreshAll();
    }

    private void loadFromFile(File file) {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(file) ){

            while (scanner.hasNext())
                content.append(scanner.next());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            alertDialog(e.getMessage());
        }

        try {
            points.clear();
            listObjects.clear();
            points.addAll(new Gson().fromJson(content.toString(), new TypeToken<List<Point>>(){}.getType()));

        }catch (Exception e){
            e.printStackTrace();
            alertDialog(e.getMessage());
        }
        refreshAll();
    }

    private void alertDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    private String checkOtherInformation(long refreshRateInMS) {
        return "Actual refresh time in ms: " + refreshRateInMS + " " +
                "Number of points: " + points.size();
    }

    private String checkMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();

        NumberFormat format = NumberFormat.getInstance();

        StringBuilder sb = new StringBuilder();
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();

        sb.append("Actual memory usage: ");
        sb.append("free memory: ").append(format.format(freeMemory / 1024)).append(" ");
        sb.append("allocated memory: ").append(format.format(allocatedMemory / 1024)).append(" ");
        sb.append("max memory: ").append(format.format(maxMemory / 1024)).append(" ");
        sb.append("total free memory: ").append(format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024)).append("\n");

        System.out.println(sb);

        return sb.toString();

    }

    private void refreshListObjects() {
        listObjects
                .forEach(IListObject::update);
    }

    private void refreshPointList(IWidget obj) {
        for (IPoint point :
                points) {
            if (listObjects.stream().noneMatch(x -> x.getPoint().equals(point))) {
                IListObject lo = new ListObject(point, (IPointList) obj);
                listObjects.add(lo);
                ((VBox) obj).getChildren().add((Node) lo);

            }
        }
        if (points.size() < listObjects.size()) {
            listObjects.forEach(x -> {
                if (points.stream().noneMatch(y -> y.equals(x.getPoint())))
                    ((VBox) obj).getChildren().remove((Node) x);
            });
        }
    }

    private void refreshCanvas(IWidget obj) {
        GraphicsContext gc = getGraphicContext(obj);
        gc.clearRect(0, 0, ((WorkCanvas) obj).getWidth(), ((WorkCanvas) obj).getHeight());
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, ((WorkCanvas) obj).getWidth(), ((WorkCanvas) obj).getHeight());
        for (IPoint point : getPoints()) {
            IGraphPoint.applyData(point).draw(gc);
        }
    }

    private Long getLastId() {
        return getLastPoint() != null ? getLastPoint().getId() : 0L;
    }

    private int getLastColor() {
        return getLastPoint() != null ? getLastPoint().getPointColor().getColorId() : 0;
    }

    private IPoint getLastPoint() {
        if (points.size() > 0) return points.get(points.size() - 1);
        else return null;
    }

    private GraphicsContext getGraphicContext(IWidget canvas) {
        return ((WorkCanvas) canvas).getGraphicsContext2D();
    }

}
