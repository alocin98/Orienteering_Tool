package gpxLib;

public interface IWaypoint {
    public double getLon();
    public double getLat();
    public double getDist();
    public Time getTime();
    public Time getDeltaSeconds();
}
