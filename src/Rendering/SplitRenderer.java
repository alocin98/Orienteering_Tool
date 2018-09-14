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
    private Vector2 startToEndVector, firstToLastReal;

    private Trackpoint startTrkpt, endTrkpt;


    public SplitRenderer(GPSFile gpsfile, Time startTime, Time endTime, Vector2 startVector, Vector2 endVector){
        this.gpsfile = gpsfile;

        this.startTime = startTime;
        this.endTime = endTime;
        this.startVector = startVector;
        this.endVector = endVector;

        findFirstLastTrackpoint();

        this.startToEndVector = new Vector2(startVector, endVector);
        this.firstToLastReal = new Vector2((endTrkpt.getLat() - startTrkpt.getLat()), (endTrkpt.getLon()- endTrkpt.getLon()));
    }

    public void calculateRenderVectors(){

        //Get multiplier
        double x = (startToEndVector.getX() / firstToLastReal.getX());
        double y = (startToEndVector.getY() / firstToLastReal.getY());
        Vector2 multiplier = new Vector2(x,y);

        //Multiply each element
        Trackpoint tmp = startTrkpt;
        while(tmp.getNext() != endTrkpt){
            Vector2 renderVector = new Vector2(tmp.getVectorToNext().getX(), tmp.getVectorToNext().getY());
            renderVector.mult(multiplier);
            tmp.setRenderVector(renderVector);
            tmp = tmp.getNext();
        }
    }

    private void findFirstLastTrackpoint(){
        startTrkpt = gpsfile.findByTime(startTime);
        endTrkpt = gpsfile.findByTime(endTime);
    }

    //------------------GET METHODS-----------------------
    public Trackpoint getStartTrackpoint(){
        return startTrkpt;
    }
    public Trackpoint getEndTrackpoint(){
        return endTrkpt;
    }

}
