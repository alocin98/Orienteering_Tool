package Main;

import Orienteering.Course;
import Time.SplitTimes;
import gpxLib.GPSFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Map.*;

public class Orienteering_Tool extends Application {

    public static Map map;
    public static GPSFile gpsfile;
    public static Course course;
    public static SplitTimes splits;

    public static Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {

        splits = new SplitTimes();
        course = new Course();
        // just load fxml file and display it in the stage:

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/view/MainWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.mainStage = mainStage;
        mainStage.setScene(scene);
        mainStage.show();
    }
}
