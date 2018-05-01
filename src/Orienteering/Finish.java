package Orienteering;

import Rendering.Vector2;
import gpxLib.Time;

public class Finish extends Control {
    public Finish(Vector2 position, Time time) {
        super(position, time);
    }

    public Finish(Control ctrl) {
        super(ctrl.position(), ctrl.getTime());
    }

    @Override
    public String getRepresentation() {
        return "FINISH: " + super.getTime();
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
