package GPSTestFolder;

import GPSTestFolder.GPSTest;
import Rendering.SplitRenderer;
import Rendering.Vector2;
import Time.Time;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SplitRendererTest extends GPSTest {

    Vector2 a = new Vector2(1,2);
    Vector2 b = new Vector2(2,3);


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

    }


}
