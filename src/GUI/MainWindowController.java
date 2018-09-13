package GUI;

import Orienteering.Course;
import Rendering.CourseRenderer;
import Rendering.RouteRenderer;
import Rendering.Vector2;
import Time.ProxySplitTimes;
import gpxLib.GPSFileLoader;
import gpxLib.Trackpoint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import Map.*;
import Main.Orienteering_Tool;

import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.util.ArrayList;

import static java.awt.Color.GREEN;

public class MainWindowController {

    private CourseRenderer courserenderer;

    private double canvas_scaleY;
    private double canvas_scaleX;
    private double canvas_transY;
    private double canvas_transX;

    private double lastPosX;
    private double lastPosY;

    private ProxySplitTimes proxySplits;

    private boolean drawCourse = false;

    @FXML
    Label lbl_loadMapInfo;

    @FXML
    Label lbl_loadGPSInfo;

    @FXML
    AnchorPane anchor_map;

    @FXML
    Group group_course;

    @FXML
    Group group_route;

    @FXML
    ImageView image_map;

    @FXML
    Button btn_drawCourse;

    @FXML
    ListView<TextField> lvSplitList;

    @FXML
    public void initialize() {
        proxySplits = new ProxySplitTimes(Orienteering_Tool.splits, lvSplitList);
    }

    /**
     * Load File button, opens the map image file.
     * @param actionEvent
     */
    @FXML
    public void loadMapFile(ActionEvent actionEvent) {
        chooseImageFile();
    }

    @FXML
    public void loadGPSFile(ActionEvent actionevent){
        chooseGPSFile();
    }

    @FXML
    private void drawCourse(){
        if(!drawCourse){
            courserenderer = new CourseRenderer(group_course);
            btn_drawCourse.setText("Finish drawing");
            drawCourse = true;
        } else if(drawCourse){
            btn_drawCourse.setText("Draw Course");
            drawCourse = false;
        }

    }

    @FXML
    public void drawRoute(){
        RouteRenderer rr = new RouteRenderer(Orienteering_Tool.gpsfile.getTrackpointList(), group_route);
        rr.setStartingPoint(Orienteering_Tool.course.getStart().getPosition());
        rr.setEndingPoint(Orienteering_Tool.course.getControl(1).getPosition());
        rr.calculate();
        rr.draw(group_route);
    }

    @FXML
    public void lvEditStart(){
        System.out.println("fck");
    }
    @FXML
    public void lvEditCommit(){

    }
    @FXML
    public void lvEditCancel(){

    }

    private void setMapImage(){
        Image map = Orienteering_Tool.map.getImage();

        //Adjust size of objects
        double width = map.getWidth();
        double height = map.getHeight();
        anchor_map.setPrefSize(width, height);
        image_map.setFitHeight(map.getHeight());
        image_map.setFitWidth(map.getWidth());
        image_map.setImage(map);
    }

    private void chooseImageFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(Orienteering_Tool.mainStage);
        if (selectedFile != null) {
            try{
                Orienteering_Tool.map = new Map(selectedFile);
                lbl_loadMapInfo.setText("Map: " + Orienteering_Tool.map.getFile().getName());
            } catch (Exception e){
                lbl_loadMapInfo.setText("Could not read file :(");
            }
        }
        setMapImage();
    }

    private void chooseGPSFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open GPS File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("GPS Files", "*.gpx"),
                new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(Orienteering_Tool.mainStage);
        if (selectedFile != null) {
            try{
                GPSFileLoader loader = new GPSFileLoader(selectedFile);
                Orienteering_Tool.gpsfile = loader.getGPSFile();
                lbl_loadGPSInfo.setText("GPS: " + selectedFile.getName());
            } catch (Exception e){
                lbl_loadGPSInfo.setText("Could not read file :(");
            }
        }
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        if(drawCourse){
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
            Orienteering_Tool.course.addControl(new Vector2(x,y));
            courserenderer.update();

            proxySplits.addSplit();
        }
    }
}
