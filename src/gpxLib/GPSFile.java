package gpxLib;

import Time.Time;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Class to load a .GPX File into a list of trackpoints. GPX is written in xml and DOM library is used to parse.
 *
 * @see Trackpoint
 */
public class GPSFile {

    private ArrayList<Trackpoint> trackpoints;

    private Time startTime, overallTime;
    private GregorianCalendar date;
    private Trackpoint startingPoint;
    private Trackpoint endingPoint;
    private double distance;
    private int size;

    /**
     * Creates a new File using the given trackpoints (usually parsed with the GPSFileLoader)
     * @param trackpoints           The trackpoints of this GPSFile.
     * @see GPSFileLoader
     */
    public GPSFile(ArrayList<Trackpoint> trackpoints, GregorianCalendar date) {
        this.trackpoints = trackpoints;
        startingPoint = trackpoints.get(0);
        endingPoint = trackpoints.get(trackpoints.size()-1);
        this.size = trackpoints.size();
        this.startTime = startingPoint.getRealTime();
        this.date = date;
        calculateTimeDistance();
        connectPointsToFile();
    }


    /**
     * Finds the trackpoint nearest to the given time.
     * @param time      Time to search for.
     * @return
     */
    public Trackpoint findByTime(Time time){
        int closest = 99999999;
            for(Trackpoint trkpt : trackpoints){
                int timeDif = time.getTimeInSeconds() - trkpt.getTimeFromStart().getTimeInSeconds();
                if(Math.abs(timeDif) < Math.abs(closest) && timeDif != 0)
                    closest = timeDif;
                else
                    return trkpt;
            }
            return new Trackpoint(0,0,0,time);
    }

    //-------------------- Getter methods ------------------------
    public Time getStartTime(){
        return this.startTime;
    }

    public Time getOverallTime(){
        return this.overallTime;
    }

    public GregorianCalendar getDate(){
        return this.date;
    }

    public Trackpoint startingPoint(){
        return this.startingPoint;
    }

    public Trackpoint endingPoint(){
        return this.endingPoint;
    }

    public int size(){
        return this.size;
    }

    public double getLength(){
        return this.distance;
    }

    public Trackpoint getPointAt(int index){
        return trackpoints.get(index);
    }

    public ArrayList<Trackpoint> getTrackpointList(){
        return this.trackpoints;
    }


    //-------------------- Helper Methods -------------------------
    /**
     * Connects Points to this file.
     */
    private void connectPointsToFile() {
        for(int i = 0; i < size; i++)
            getPointAt(i).setHeadFile(this);
    }

    /**
     * Calculates the overall Time and Distance.
     */
    private void calculateTimeDistance() {
        int seconds = endingPoint.getRealTime().getTimeInSeconds() - startingPoint.getRealTime().getTimeInSeconds();
        this.overallTime = new Time(seconds);
        double dist = 0;
        for(int i = 0; i < size; i++) {
            dist += getPointAt(i).getDist();
        }
        this.distance = dist;
    }

}
