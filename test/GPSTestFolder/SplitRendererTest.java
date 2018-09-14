package GPSTestFolder;

import GPSTestFolder.GPSTest;
import Rendering.SplitRenderer;
import Rendering.Vector2;
import Time.Time;
import gpxLib.Trackpoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SplitRendererTest extends GPSTest {

    Vector2 a;
    Vector2 b;

    @Override
    protected void init() {
        super.init();
        a = new Vector2(0,0);
        b = new Vector2(10,10);
    }

    @Test
    public void initTest(){
        init();
    }

    @Test
    public void findTrackpointsTest(){
        init();
        Time firstSplit = new Time(0,1,0);
        Time secondSplit = new Time(0,2,0);
        SplitRenderer spltrdr = new SplitRenderer(gpsfile, firstSplit, secondSplit, a,b);
        assertEquals(1, spltrdr.getStartTrackpoint().getTimeFromStart().getMinutes());
        assertEquals(2, spltrdr.getEndTrackpoint().getTimeFromStart().getMinutes());
    }

    @Test
    public void calculateRenderVectorsTest(){
        init();
        Time firstSplit = new Time(0,1,0);
        Time secondSplit = new Time(0,2,0);
        SplitRenderer spltrdr = new SplitRenderer(gpsfile, firstSplit, secondSplit, a,b);
        spltrdr.calculateRenderVectors();
        Trackpoint tmp = spltrdr.getStartTrackpoint();
        double deltaX = 0;
        double deltaY = 0;
        while(tmp.getNext() != spltrdr.getEndTrackpoint()){
            deltaX += tmp.getRenderVector().getX();
            deltaY += tmp.getRenderVector().getY();
            tmp = tmp.getNext();
        }
        assertEquals(10, deltaX);
        assertEquals(10, deltaY);
    }


}
