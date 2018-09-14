package GPSTestFolder;

import GPSTestFolder.GPSTest;
import Time.Time;
import gpxLib.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrackpointTester extends GPSTest {

    @Test
    public void testTrackpointParse(){
        init();
        Trackpoint GPXTrackpoint = gpsfile.getPointAt(0);
        assertEquals(46.90106667, GPXTrackpoint.getLat());
        assertEquals(7.54641467, GPXTrackpoint.getLon());
        assertEquals(568.0, GPXTrackpoint.getEle());
        assertEquals(15, GPXTrackpoint.getRealTime().getHours());
        assertEquals(19, GPXTrackpoint.getRealTime().getMinutes());
        assertEquals(9, GPXTrackpoint.getRealTime().getSeconds());
    }

    @Test
    public void testTrackpointDistance(){
        init();
        Trackpoint GPXTrackpoint = gpsfile.getPointAt(1);
        assertEquals(46.90106667, GPXTrackpoint.getLat());
        assertEquals(7.54641467, GPXTrackpoint.getLon());
        Trackpoint TrackpointTwo = gpsfile.getPointAt(2);
        assertEquals(46.90109217, TrackpointTwo.getLat());
        assertEquals(7.54640683, TrackpointTwo.getLon());
        assertEquals(0.0028973584364006193, GPXTrackpoint.getDist());

    }

    @Test
    public void testTrackpointDeltaTime(){
        init();
        Trackpoint GPXTrackpoint = gpsfile.getPointAt(1);
        assertEquals(46.90106667, GPXTrackpoint.getLat());
        assertEquals(7.54641467, GPXTrackpoint.getLon());
        assertEquals(1, GPXTrackpoint.getDeltaTime());

    }

    @Test
    public void testTrackpointPace(){
        init();
        Trackpoint GPXTrackpoint = gpsfile.getPointAt(1260);
        assertEquals(0, GPXTrackpoint.getPace().getHours());
        assertEquals(3, GPXTrackpoint.getPace().getMinutes());
        assertEquals(14, GPXTrackpoint.getPace().getSeconds());

    }

    @Test
    public void testStartTime(){
        init();
        Trackpoint GPXTrackpoint = gpsfile.getPointAt(948);
        assertEquals(gpsfile.getStartTime().toString(), new Time("2018-04-25T15:19:09.000Z").toString());
        Trackpoint anyOther = gpsfile.getPointAt(3012);
        assertEquals(gpsfile.getStartTime().toString(), new Time("2018-04-25T15:19:09.000Z").toString());
    }

    @Test
    public void testTotalTime(){
        init();
        double dist = 0;
        for(int i = 0; i < gpsfile.getTrackpointList().size(); i++){
            dist += gpsfile.getTrackpointList().get(i).getDist();
        }
        assertEquals(8.729878672263073, dist);
    }
}
