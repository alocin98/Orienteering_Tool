import gpxLib.Time;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTester {

    @Test
    public void testTimeString(){
        Time time = new Time("2018-04-26T16:58:33.000Z");
        assertEquals("16:58:33", time.toString());
        assertEquals(16, time.getHours());
        assertEquals(58, time.getMinutes());
        assertEquals(33, time.getSeconds());
    }

    @Test
    public void testTimeStringTwo(){
        Time time = new Time("><time>2018-04-21T15:02:16.000Z</ti");
        assertEquals("15:02:16", time.toString());
        assertEquals(15, time.getHours());
        assertEquals(02, time.getMinutes());
        assertEquals(16, time.getSeconds());
    }
}
