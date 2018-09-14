import gpxLib.*;
import org.junit.jupiter.api.Test;
import Time.Time;

import java.io.File;
import java.util.Calendar;

import static java.util.Calendar.*;
import static org.junit.jupiter.api.Assertions.*;

public class GPSFileTester extends GPSTest{

    @Test
    public void initTest(){
        init();
    }

    @Test
    public void distTest(){
        init();
        assertEquals(8.729878672263073, gpsfile.getLength());
    }

    @Test
    public void timeTest(){
        init();
        assertEquals("00:57:25", gpsfile.getOverallTime().toString());
    }

    @Test
    public void firstLastTest(){
        init();
        assertEquals(gpsfile.getPointAt(0), gpsfile.startingPoint());
        assertEquals(gpsfile.getPointAt(gpsfile.size()-1), gpsfile.endingPoint());
    }

    @Test
    public void dateTest(){
        init();
        assertEquals(2018, gpsfile.getDate().get(Calendar.YEAR));
        assertEquals(25, gpsfile.getDate().get(Calendar.DAY_OF_MONTH));
        assertEquals(4, gpsfile.getDate().get(Calendar.MONTH));
    }

    @Test
    public void findByTimeOneMinuteTest(){
        init();
        Time time = new Time(0,1,0);
        Trackpoint trkpt = gpsfile.findByTime(time);
        assertEquals(0, trkpt.getTimeFromStart().getHours());
        assertEquals(1, trkpt.getTimeFromStart().getMinutes());
        assertEquals(0, trkpt.getTimeFromStart().getSeconds());
    }

    @Test
    public void findByTimeThreeMinutesFiveteenTest(){
        init();
        Time time = new Time(0,3,15);
        Trackpoint trkpt = gpsfile.findByTime(time);
        assertEquals(0, trkpt.getTimeFromStart().getHours());
        assertEquals(3, trkpt.getTimeFromStart().getMinutes());
        assertEquals(15, trkpt.getTimeFromStart().getSeconds());
    }
}
