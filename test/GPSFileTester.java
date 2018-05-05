import gpxLib.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static java.util.Calendar.*;
import static org.junit.jupiter.api.Assertions.*;

public class GPSFileTester {
    GPSFile gpsfile;

    private void init(){
        File file = new File("/Users/nicolasmuller/Prog/Main.Orienteering_Tool/test/Test_gpx_polar");
        GPSFileLoader loader = new GPSFileLoader(file);
        gpsfile = loader.getGPSFile();
    }

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
}
