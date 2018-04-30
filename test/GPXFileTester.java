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
        ITrackpoint GPXTrackpoint = gpxfile.getTrackpoints().get(0);
        assertEquals(46.90106667, GPXTrackpoint.getLat());
        assertEquals(7.54641467, GPXTrackpoint.getLon());
        assertEquals(568.0, GPXTrackpoint.getEle());
        assertEquals(15, GPXTrackpoint.getTime().getHours());
        assertEquals(19, GPXTrackpoint.getTime().getMinutes());
        assertEquals(9, GPXTrackpoint.getTime().getSeconds());
    }
}
