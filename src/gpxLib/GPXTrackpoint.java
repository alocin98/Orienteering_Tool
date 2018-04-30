package gpxLib;

/**
 * A Trackpoint stores the location data loaded from the file. It contains the Lon and Lat at the given time.
 * If Elevation data is present, it also stores that.
 */
public class GPXTrackpoint implements ITrackpoint {
    private double latitude, longitude, elevation;
    private Time time;

    /**
     * Stores the data into this class.
     * @param lat               The Latitude (Coordinate in degrees) of the location
     * @param lon               The Longitude (Coordinate in degrees) of the location
     * @param elevation         The Elevation (in meters above sealevel) of the location
     * @param time              The Time of the location
     */
    public GPXTrackpoint(double lat, double lon, double elevation, Time time){
        this.latitude = lat;
        this.longitude = lon;
        this.time = time;
        this.elevation = elevation;
    }

    /**
     * @return                  The Latitude (Coordinate in degrees) of the location
     */
    @Override
    public double getLon() {
        return longitude;
    }

    /**
     * @return                  The Longitude (Coordinate in degrees) of the location
     */
    @Override
    public double getLat() {
        return latitude;
    }

    /**
     * @return                  The Time of the location
     */
    @Override
    public Time getTime() {
        return time;
    }

    /**
     * @return                  The Elevation (in meters above sea) of the location
     */
    @Override
    public double getEle() {
        return elevation;
    }
}
