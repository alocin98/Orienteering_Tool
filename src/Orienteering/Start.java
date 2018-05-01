package Orienteering;

import Rendering.Vector2;
import gpxLib.Time;

public class Start extends Control{

    public Start(Vector2 position, Time time) {
        super(position, time);
        super.controlNumber = 0;
    }

    public Start(Control ctrl){
        super(ctrl.position(), ctrl.getTime());
    }

    @Override
    public String getRepresentation() {
        return "START: " + super.getTime();
    }

    @Override
    public boolean isStart() {
        return true;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
