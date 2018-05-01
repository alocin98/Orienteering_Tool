package Orienteering;

import Rendering.Vector2;
import gpxLib.Time;

import java.util.ArrayList;

public class CourseGenerator {
    private ArrayList<Control> controlList;
    private Course course;

    public CourseGenerator(){
        controlList = new ArrayList<Control>();
    }

    public void addControl(Control ctrl){
        controlList.add(ctrl);
    }

    public void addControl(Vector2 position, Time time){
        controlList.add(new Control(position, time));
    }

    public Course extractCourse(){
        final int FINISH = controlList.size()-1;
        final int START = 0;

        //Cast Abstract controls to real ones and connect finish to invalid
        Start start = new Start(controlList.get(START));
        Finish finish = new Finish(controlList.get(FINISH));
        finish.setNext(new InvalidControl());
        finish.controlNumber = FINISH;

        //Feed them into an array
        Control[] courseArray = new Control[controlList.size()];
        courseArray[START] = start;
        for(int i = 1; i < FINISH-1; i++) {
            courseArray[i] = (Control) controlList.get(i);
            courseArray[i].setControlNumber(i);
        }

        //Cast Finish
        courseArray[FINISH] = finish;

        //Create course
        return new Course(courseArray);
    }
}
