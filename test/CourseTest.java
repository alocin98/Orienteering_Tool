import Orienteering.Control;
import Orienteering.Course;
import Orienteering.CourseGenerator;
import Rendering.Vector2;
import gpxLib.Time;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseTest {

    @Test
    public void createCourseTest(){
        CourseGenerator cg = new CourseGenerator();
        Control ctrl = new Control(new Vector2(0.0,0.0), new Time(0));
        cg.addControl(new Vector2(0.0,0.0), new Time(0));
        cg.addControl(new Vector2(1,1), new Time(3));
        cg.addControl(new Vector2(2,2), new Time(6));
        cg.addControl(new Vector2(3,3), new Time(9));
        cg.addControl(new Vector2(4,4), new Time(12));
        Course course = cg.extractCourse();
    }

    @Test
    public void CourseValidTest(){
        CourseGenerator cg = new CourseGenerator();
        cg.addControl(new Vector2(0.0,0.0), new Time(0));
        cg.addControl(new Vector2(1.0,1.0), new Time(3));
        cg.addControl(new Vector2(2.0,2.0), new Time(6));
        cg.addControl(new Vector2(3.0,3.0), new Time(9));
        cg.addControl(new Vector2(4.0,4.0), new Time(12));
        Course course = cg.extractCourse();
        assertTrue(course.getControl(0).isStart());
        assertTrue(course.getFinish().isFinish());
        assertFalse(course.getControl(2).isFinish());
    }
}
