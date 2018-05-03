
import Orienteering.ControlType;
import Orienteering.Course;
import Rendering.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {

    @Test
    public void courseTest(){
        Course course = new Course();
        course.addControl(new Vector2(10,20));
        course.addControl(new Vector2(12,22));
        course.addControl(new Vector2(14,24));
        course.addControl(new Vector2(16,26));
        course.addControl(new Vector2(18,28));
        assertEquals(ControlType.START, course.getControl(0).getType());
        assertEquals(ControlType.FINISH, course.getControl(course.getControlList().size()-1).getType());
    }

    @Test
    public void addControlTest(){
        Course course = new Course();
        course.addControl(new Vector2(10,20));
        course.addControl(new Vector2(12,22));
        assertEquals(ControlType.START, course.getControl(0).getType());
        assertEquals(ControlType.FINISH, course.getControl(1).getType());

        course.addControl(new Vector2(14,24));
        assertEquals(ControlType.START, course.getControl(0).getType());
        assertEquals(ControlType.NORMAL, course.getControl(1).getType());
        assertEquals(ControlType.FINISH, course.getControl(2).getType());

        course.addControl(new Vector2(16,26));
        assertEquals(ControlType.START, course.getControl(0).getType());
        assertEquals(ControlType.NORMAL, course.getControl(1).getType());
        assertEquals(ControlType.NORMAL, course.getControl(2).getType());
        assertEquals(ControlType.FINISH, course.getControl(3).getType());
    }

    @Test
    public void controlVectorTest(){
        Course course = new Course();
        course.addControl(new Vector2(10,20));
        course.addControl(new Vector2(12,22));
        assertEquals("x: 2.0 y: 2.0", course.getControl(0).getVectorToNext().toString());
        course.addControl(new Vector2(20,21));
        assertEquals("x: 8.0 y: -1.0", course.getControl(1).getVectorToNext().toString());
        course.addControl(new Vector2(50,50));
        assertEquals("x: 30.0 y: 29.0", course.getControl(2).getVectorToNext().toString());

    }
}
