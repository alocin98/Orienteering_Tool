package Rendering;

import Main.Orienteering_Tool;
import Orienteering.Course;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class CourseRenderer {

    private Group group;
    private Course course;

    public CourseRenderer(Group group_map) {
        this.group = group_map;
        this.course = Orienteering_Tool.course;
    }

    public void update(){
        group.getChildren().clear();
        for(int i = 0; i < course.getControlList().size(); i++){
            int x = (int) course.getControl(i).getPosition().getX();
            int y = (int) course.getControl(i).getPosition().getY();
            switch(course.getControl(i).getType()){
                case START:
                    // First move to starting point
                    MoveTo moveTo = new MoveTo();
                    moveTo.setX(100.0f);
                    moveTo.setY(50.0f);
                    group.getChildren().add(controlShape(x, y, 20));
                    break;
                case NORMAL:
                    group.getChildren().add(controlShape(x,y, 20));
                    break;
                case FINISH:
                    group.getChildren().add(controlShape(x,y,15));
                    group.getChildren().add(controlShape(x,y,25));
                    break;
            }
        }
    }

    private Polygon startShape(int x, int y){
        Polygon start = new Polygon();
        start.getPoints().addAll(new Double[]{
                Double.valueOf(x-10), Double.valueOf(y),
                Double.valueOf(x), Double.valueOf(y+20),
                Double.valueOf(x+10), Double.valueOf(y) });
        start.setStroke(Color.RED);
        start.setStrokeWidth(3.0);
        start.setFill(Color.TRANSPARENT);

        return start;
    }

    private Circle controlShape(int x, int y, int r){
        Circle control = new Circle(x,y,r);
        control.setStroke(Color.RED);
        control.setStrokeWidth(3.0);
        control.setFill(Color.TRANSPARENT);
        return control;
    }
}
