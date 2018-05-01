package Orienteering;

public class Course {
    private Control[] controls;

    public Course(Control[] controls){
        this.controls = controls;
    }

    public Control getControl(int i){
        return controls[i];
    }

    public Control getStart(){
        return controls[0];
    }

    public Control getFinish(){
        return controls[controls.length-1];
    }

}
