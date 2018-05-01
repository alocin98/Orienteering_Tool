package Orienteering;

import Rendering.Vector2;
import gpxLib.Time;

public class InvalidControl extends Control{

    public InvalidControl() {
        super(new Vector2(0,0), new Time(0));
    }

    @Override
    public String getRepresentation() {
        return null;
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
