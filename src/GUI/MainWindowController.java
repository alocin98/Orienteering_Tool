package GUI;

import Orienteering.Course;
import Rendering.RouteRenderer;
import Rendering.Vector2;
import gpxLib.GPSFileLoader;
import gpxLib.Trackpoint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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

    private double canvas_scaleY;
    private double canvas_scaleX;
    private double canvas_transY;
    private double canvas_transX;

    private double lastPosX;
    private double lastPosY;

    private boolean drawCourse = false;

    @FXML
    Label lbl_loadMapInfo;

    @FXML
    Label lbl_loadGPSInfo;

    @FXML
    Canvas canvas_map;

    @FXML
    Button btn_drawCourse;

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
            btn_drawCourse.setText("Finish drawing");
            drawCourse = true;
        } else if(drawCourse){
            btn_drawCourse.setText("Draw Course");
            drawCourse = false;
        }

    }

    private void setCanvas(){
        Image map = Orienteering_Tool.map.getImage();
        canvas_map.setHeight(map.getHeight());
        canvas_map.setWidth(map.getWidth());
        canvas_map.getGraphicsContext2D().drawImage(Orienteering_Tool.map.getImage(), 0,0);
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
        setCanvas();
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

    public void moveAround(MouseEvent mouseEvent) {
        double deltaX = mouseEvent.getX() - lastPosX;
        double deltaY = mouseEvent.getY() - lastPosY;
        canvas_transX = lastPosX + deltaX;
        canvas_transY = lastPosY + deltaY;
        lastPosX = canvas_transX;
        lastPosY = canvas_transY;
        setPosition();
    }

    public void zoomInOut(ZoomEvent zoomEvent) {
        canvas_scaleX += zoomEvent.getTotalZoomFactor()-1;
        canvas_scaleY += zoomEvent.getTotalZoomFactor()-1;
        canvasSetScale();
    }

    public void zoomFinish(ZoomEvent zoomEvent) {
        canvasSetScale();
    }

    public void zoomStart(ZoomEvent zoomEvent) {
        canvasSetScale();
    }
    private void canvasSetScale(){
        canvas_map.setScaleX(canvas_scaleX);
        canvas_map.setScaleY(canvas_scaleY);
    }
    private void setPosition(){
        canvas_map.setTranslateX(canvas_transX);
        canvas_map.setTranslateY(canvas_transY);
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        if(drawCourse){
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
            Orienteering_Tool.course.addControl(new Vector2(x,y));
            updateCourseRender();
        }
    }

    private void updateCourseRender() {
        Course course = Orienteering_Tool.course;
        GraphicsContext gc = canvas_map.getGraphicsContext2D();
        gc.setLineWidth(3.0);
        gc.setStroke(Color.RED);
        for(int i = 0; i < course.getControlList().size(); i++){
            int x = (int) course.getControl(i).getPosition().getX();
            int y = (int) course.getControl(i).getPosition().getY();
            gc = canvas_map.getGraphicsContext2D();
            gc.strokeOval(x-25, y-25, 50,50);
        }
    }
}
