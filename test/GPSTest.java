import gpxLib.*;
import org.junit.jupiter.api.Test;
import Time.Time;

import java.io.File;
import java.util.Calendar;

import static java.util.Calendar.*;
import static org.junit.jupiter.api.Assertions.*;

public class GPSTest {
    GPSFile gpsfile;

    protected void init(){
        File file = new File("/Users/nicolasmuller/Prog/Orienteering_Tool/test/Test_gpx_polar");
        GPSFileLoader loader = new GPSFileLoader(file);
        gpsfile = loader.getGPSFile();
    }
}
