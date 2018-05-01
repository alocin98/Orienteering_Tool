package Orienteering;

import Rendering.Vector2;
import gpxLib.Time;

public class Control {

    protected Vector2 position;
    protected int controlNumber;
    protected Time time;
    protected Control nextControl;

    public Control(Vector2 position, Time time){
        this.position = position;
        this.time = time;
    }

    public void setNext(Control next){
        this.nextControl = next;
    }

    public Vector2 position(){
        return this.position;
    }

    public Control getNext() {
        return nextControl;
    }

    public Time getTime(){
        return time;
    }

    public void setTime(Time time){
        this.time = time;
    }

    public void setControlNumber(int controlNumber){
        this.controlNumber = controlNumber;
    }

    public String getRepresentation(){
        return "";
    }
    public boolean isStart(){
        return false;
    }
    public boolean isFinish(){
        return false;
    }
    public boolean isValid(){
        return true;
    }
}
