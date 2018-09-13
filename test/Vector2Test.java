import Rendering.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector2Test {
    @Test
    public void testVector2(){
        Vector2 test = new Vector2(10,20);
        assertEquals(10, test.getX());
        assertEquals(20, test.getY());
    }

    @Test
    public void testVector2FromTo(){
        Vector2 from = new Vector2(10,20);
        Vector2 to = new Vector2(20,20);
        Vector2 test = new Vector2(from, to);
        assertEquals(10, test.getX());
        assertEquals(0, test.getY());
    }

    @Test
    public void testVector2Mult(){
        Vector2 multiplier = new Vector2(2,2);
        Vector2 test = new Vector2(10, 10);
        test.mult(multiplier);
        assertEquals(20, test.getX());
        assertEquals(20, test.getY());
    }

    @Test
    public void testVector2MultTwo(){
        Vector2 multiplier = new Vector2(0.5,0.5);
        Vector2 test = new Vector2(10, 10);
        test.mult(multiplier);
        assertEquals(5, test.getX());
        assertEquals(5, test.getY());
    }
}
