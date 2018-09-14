package Rendering;

import gpxLib.GPSFile;
import Time.Time;
import gpxLib.Trackpoint;

import java.util.ArrayList;
import java.util.List;

public class SplitRenderer {

    private GPSFile gpsfile;

    private Time startTime, endTime;
    private Vector2 startVector, endVector;

    private Trackpoint startTrkpt, endTrkpt;


    public SplitRenderer(GPSFile gpsfile, Time startTime, Time endTime, Vector2 startVector, Vector2 endVector){
        this.gpsfile = gpsfile;

        this.startTime = startTime;
        this.endTime = endTime;
        this.startVector = startVector;
        this.endVector = endVector;

        findFirstLastTrackpoint();
    }

    private void findFirstLastTrackpoint(){
        startTrkpt = gpsfile.findByTime(startTime);
        endTrkpt = gpsfile.findByTime(endTime);
    }

}
