package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import Map.*;
import Main.Orienteering_Tool;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.io.File;

public class MainWindowController {

    private double canvas_scaleY;
    private double canvas_scaleX;
    private double canvas_transY;
    private double canvas_transX;

    private double lastPosX;
    private double lastPosY;

    @FXML
    Label lbl_loadMapInfo;

    @FXML
    Canvas canvas_map;

    /**
     * Load File button, opens the map image file.
     * @param actionEvent
     */
    @FXML
    public void loadFile(ActionEvent actionEvent) {
        chooseImageFile();
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
}
