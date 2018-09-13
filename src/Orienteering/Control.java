package Orienteering;

import Rendering.Vector2;

public class Control {

    public Course course;

    private Vector2 position;
    private Vector2 vectorToNext;
    private ControlType type;
    private int controlNumber;
    private Control nextControl;

    public Control(Vector2 position){
        this.position = position;
        this.type = ControlType.NORMAL;
    }

    public Control(Vector2 position, ControlType type){
        this.position = position;
        this.type = type;
    }

    public Control(Vector2 position, ControlType type, Course course){
        this.position = position;
        this.type = type;
        this.course = course;
    }

    public void setNext(Control next){
        this.nextControl = next;
        updateVectorToNext();
    }

    public void setControlNumber(int controlNumber){
        this.controlNumber = controlNumber;
    }

    public void setCourse(Course course){
        this.course = course;
    }

    public void changePosition(Vector2 position){
        this.position = position;
        updateVectorToNext();
    }

    public void changeType(ControlType type){
        this.type = type;
    }

    public Vector2 getPosition(){
        return this.position;
    }

    public Control getNext() {
        return nextControl;
    }

    public String getRepresentation(){
        return "";
    }

    public ControlType getType(){
        return this.type;
    }

    public Vector2 getVectorToNext(){
        return this.vectorToNext;
    }

    private void updateVectorToNext(){
        this.vectorToNext = new Vector2(this.getPosition(), nextControl.getPosition());
    }
}
