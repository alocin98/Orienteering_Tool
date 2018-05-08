package gpxLib;

import Rendering.Vector2;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Trackpoint stores the location data loaded from the file. It contains the Lon and Lat at the given time.
 * If Elevation data is present, it also stores that.
 */
public class Trackpoint {

    //GPS Data
    private double latitude, longitude, elevation;
    private Time realTime, timeFromStart;

    //Head-File
    private GPSFile headfile;

    //Calculated Data
    private Vector2 vectorToNext;
    private double dist;
    private Time pace;
    private int deltaTime;

    //Next Trackpoint
    private Trackpoint next;

    private Vector2 renderVector;


    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

    /**
     * Stores the data into this class.
     * @param lat               The Latitude (Coordinate in degrees) of the location
     * @param lon               The Longitude (Coordinate in degrees) of the location
     * @param elevation         The Elevation (in meters above sealevel) of the location
     * @param time              The Time of the location
     */
    public Trackpoint(double lat, double lon, double elevation, Time time){
        this.latitude = lat;
        this.longitude = lon;
        this.realTime = time;
        this.elevation = elevation;
    }


    //------------Setter Methods--------------------------
    /**
     * Sets the next Trackpoint and calculates data with its helper method:
     * -vector to next
     * -distance to next
     * -time to next
     * -actual pace
     *
     * @param next          The trackpoint following this one
     */
    public void setNext(Trackpoint next) {
        this.next = next;
        calculate(next);
    }

    /**
     * Sets Pointer to the headfile, which is a GPSFile
     * @param gpsfile           The GPSFile this trackpoint belongs to.
     * @see GPSFile
     */
    public void setHeadFile(GPSFile gpsfile){
        this.headfile = gpsfile;
        int timeInSeconds = this.getRealTime().getTimeInSeconds() - headfile.getStartTime().getTimeInSeconds();
        this.timeFromStart = new Time(timeInSeconds);
    }

    /**
     * Sets the position of this point on the map. should be calculated during crafting process.
     * @param mapPosition           The position on th emap
     */
    public void setRenderVector(Vector2 renderVector){
        this.renderVector = renderVector;
    }


    //------------Getter Methods--------------------------
    /**
     * @return                  The Latitude (Coordinate in degrees) of the location
     */
    public double getLon() {
        return longitude;
    }

    /**
     * @return                  The Longitude (Coordinate in degrees) of the location
     */
    public double getLat() {
        return latitude;
    }

    /**
     * @return                  The Elevation (in meters above sea) of the location
     */
    public double getEle() {
        return elevation;
    }

    /**
     * @return                  The Time of the location
     */
    public Time getRealTime() {
        return realTime;
    }

    /**
     * @return              The time from this point to the next one
     */
    public int getDeltaTime(){
        return deltaTime;
    }

    /**
     * Gets the time passed from Start to this point.
     * @return          The time passed from Start to this point.
     */
    public Time getTimeFromStart(){
        return this.timeFromStart;
    }

    /**
     * Returns the next trackpoint (following this one in the file)
     * @return              The Trackpoint following this one according to the file
     */
    public Trackpoint getNext() {
        return next;
    }

    /**
     * Returns the distance to the next point in km.
     * @return          the distance to the next point in km.
     */
    public double getDist(){
        return this.dist;
    }

    /**
     * Returns the pace in min/km
     * @return          The pace at this point im min/km
     */
    public Time getPace(){
        return pace;
    }

    /**
     * Returns a vector from this to the next trackpoint in the file.
     * @return      a vector from this to the next trackpoint in the file.
     */
    public Vector2 getVectorToNext(){
        return vectorToNext;
    }

    /**
     * Returns the position of this point on the map.
     * Should be calculated during the crafting process somewhere.
     * @return      The position of this point on the map.
     */
    public Vector2 getRenderVector(){
        return this.renderVector;
    }


    //-----------Helper Methods--------------------------
    /**
     * Helper method to calculate:
     * -vector to next
     * -distance to next
     * -time to next
     * -actual pace
     *
     * @param next          The next Trackpoint
     */
    private void calculate(Trackpoint next) {
        double x = next.getLat() - this.getLat();
        double y = next.getLon() - this.getLon();
        vectorToNext = new Vector2(x, y);
        dist = distance(this.latitude, this.longitude, next.getLat(), next.getLon());
        deltaTime = next.getRealTime().getTimeInSeconds() - this.realTime.getTimeInSeconds();
        pace = new Time((int)(1/dist) * deltaTime);
    }

    /**
     * Helpermethod to calculate distance between two points on the earth
     * @param startLat              starting Latitude
     * @param startLong             starting Longitude
     * @param endLat                ending Latitude
     * @param endLong               ending Longitude
     * @return                      The distance between the two points in KM
     */
    public static double distance(double startLat, double startLong, double endLat, double endLong) {

        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    /**
     * Helper method to calculate distance
     * @param val
     * @return
     */
    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

}
