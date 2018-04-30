import gpxLib.*;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GPXFileTester {
    @Test
    public void testFileInit(){
        File file = new File("/Users/nicolasmuller/Prog/Orienteering_Tool/test/Test_gpx_polar");
        GPXFile gpxfile = new GPXFile(file);
    }

    @Test
    public void testTrackpointParse(){
        File file = new File("/Users/nicolasmuller/Prog/Orienteering_Tool/test/Test_gpx_polar");
        GPXFile gpxfile = new GPXFile(file);
        Trackpoint GPXTrackpoint = gpxfile.getTrackpoints().get(0);
        assertEquals(46.90106667, GPXTrackpoint.getLat());
        assertEquals(7.54641467, GPXTrackpoint.getLon());
        assertEquals(568.0, GPXTrackpoint.getEle());
        assertEquals(15, GPXTrackpoint.getTime().getHours());
        assertEquals(19, GPXTrackpoint.getTime().getMinutes());
        assertEquals(9, GPXTrackpoint.getTime().getSeconds());
    }

    @Test
    public void testTrackpointDistance(){
        File file = new File("/Users/nicolasmuller/Prog/Orienteering_Tool/test/Test_gpx_polar");
        GPXFile gpxfile = new GPXFile(file);
        Trackpoint GPXTrackpoint = gpxfile.getTrackpoints().get(1);
        assertEquals(46.90106667, GPXTrackpoint.getLat());
        assertEquals(7.54641467, GPXTrackpoint.getLon());
        Trackpoint TrackpointTwo = gpxfile.getTrackpoints().get(2);
        assertEquals(46.90109217, TrackpointTwo.getLat());
        assertEquals(7.54640683, TrackpointTwo.getLon());
        assertEquals(0.0028973584364006193, GPXTrackpoint.getDist());

    }

    @Test
    public void testTrackpointDeltaTime(){
        File file = new File("/Users/nicolasmuller/Prog/Orienteering_Tool/test/Test_gpx_polar");
        GPXFile gpxfile = new GPXFile(file);
        Trackpoint GPXTrackpoint = gpxfile.getTrackpoints().get(1);
        assertEquals(46.90106667, GPXTrackpoint.getLat());
        assertEquals(7.54641467, GPXTrackpoint.getLon());
        assertEquals(1, GPXTrackpoint.getDeltaTime());

    }

    @Test
    public void testTrackpointPace(){
        File file = new File("/Users/nicolasmuller/Prog/Orienteering_Tool/test/Test_gpx_polar");
        GPXFile gpxfile = new GPXFile(file);
        Trackpoint GPXTrackpoint = gpxfile.getTrackpoints().get(1260);
        assertEquals(0, GPXTrackpoint.getPace().getHours());
        assertEquals(3, GPXTrackpoint.getPace().getMinutes());
        assertEquals(14, GPXTrackpoint.getPace().getSeconds());

    }
}
