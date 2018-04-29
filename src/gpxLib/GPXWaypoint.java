package gpxLib;

import java.util.ArrayList;

public class GPXWaypoint implements IWaypoint {
    private double latitude, longitude;
    private double distance;
    private Time time;

    public GPXWaypoint(double lat, double lon, double dist, Time time){
        this.latitude = lat;
        this.longitude = lon;
        this.distance = dist;
        this.time = time;
    }

    @Override
    public double getLon() {
        return longitude;
    }

    @Override
    public double getLat() {
        return latitude;
    }

    @Override
    public double getDist() {
        return distance;
    }

    @Override
    public Time getTime() {
        return time;
    }

    @Override
    public Time getDeltaSeconds() {
        return time;
    }
}
