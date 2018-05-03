package Orienteering;

import Rendering.Vector2;

import java.util.ArrayList;

public class Course {

    private ArrayList<Control> controls;
    private Control start;
    private Control finish;

    public Course(ArrayList<Control> controls){
        this.controls = controls;
    }

    public Course(){
        controls = new ArrayList<Control>();
    }

    public void addControl(Control c){
        controls.add(c);
        update();
    }

    public void addControl(Vector2 position){
        controls.add(new Control(position));
        update();
    }

    public void removeControl(int number){
        controls.remove(number);
        update();
    }

    public Control getControl(int i){
        return controls.get(i);
    }

    public ArrayList<Control> getControlList(){
        return this.controls;
    }

    public Control getStart(){
        return controls.get(0);
    }

    public Control getFinish(){
        return controls.get(controls.size()-1);
    }

    private void update(){

        //set Start
        this.start = controls.get(0);
        start.changeType(ControlType.START);

        //set Finish
        this.finish = controls.get(controls.size()-1);
        finish.changeType(ControlType.FINISH);

        //set Normal
        if(controls.size() >= 3) {
            for (int i = 1; i <= controls.size() - 2; i++)
                controls.get(i).changeType(ControlType.NORMAL);
        }

        //Connect controls right and set Numbers
        for(int i = 0; i < controls.size()-1; i++){
            Control current = controls.get(i);
            current.setCourse(this);
            current.setControlNumber(i);
            if(current.getType() != ControlType.FINISH)
                current.setNext(controls.get(i + 1));
        }

    }

}
