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

    public static Time startTime;
    public static Date date;

    //Calculated Data
    private Vector2 vectorToNext;
    private Vector2 mirrorVector;
    private double dist;
    private Time pace;
    private int deltaTime;

    //Next Trackpoint
    private Trackpoint next;


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
     * @return                  The Time of the location
     */
    public Time getRealTime() {
        return realTime;
    }

    /**
     * Sets the overall starting tame of the track.
     * @param time              The starting time of the track.
     */
    public static void setStartTime(Time time){
        Trackpoint.startTime = time;
    }

    /**
     *
     * @param date
     */
    public static void setDate(String date){
        int year = 44;
        int month = 44;
        int day = 44;
        final String DATA_PATTERN = "[\\d]{4}[-][\\d]{2}[-][\\d]{2}";
        final String YYYY_PATTERN = "[\\d]{4}";
        final String MMDD_PATTERN = "[\\d]{2}";
        Pattern datePattern = Pattern.compile(DATA_PATTERN);
        Matcher dateMatcher = datePattern.matcher(date);
        if(dateMatcher.find()){
            String date_String = dateMatcher.group(0);
            datePattern = Pattern.compile(YYYY_PATTERN);
            dateMatcher = datePattern.matcher(date_String);
            if(dateMatcher.find())
                year = Integer.parseInt(dateMatcher.group(0));
            datePattern = Pattern.compile(MMDD_PATTERN);
            dateMatcher = datePattern.matcher(date_String);
            if(dateMatcher.find())
                month = Integer.parseInt(dateMatcher.group());
            if(dateMatcher.find())
                day = Integer.parseInt(dateMatcher.group());
            Trackpoint.date = new Date(year, month, day);

        }
    }

    /**
     * @return                  The Elevation (in meters above sea) of the location
     */
    public double getEle() {
        return elevation;
    }

    /**
     * Returns the next trackpoint (following this one in the file)
     * @return              The Trackpoint following this one according to the file
     */
    public Trackpoint getNext() {
        return next;
    }

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
        calculate(next);
        this.next = next;
    }

    /**
     * Returns the distance to the next point in km.
     * @return          the distance to the next point in km.
     */
    public double getDist(){
        return dist;
    }

    /**
     * Returns the pace in min/km
     * @return          The pace at this point im min/km
     */
    public Time getPace(){
        return pace;
    }

    /**
     * @return              The time from this point to the next one
     */
    public int getDeltaTime(){
        return deltaTime;
    }

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
        mirrorVector = vectorToNext;
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
