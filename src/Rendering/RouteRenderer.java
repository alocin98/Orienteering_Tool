package Rendering;

import gpxLib.Trackpoint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;


import java.util.ArrayList;

public class RouteRenderer {
    private ArrayList<Trackpoint> points;
    private Trackpoint first;
    private Trackpoint last;

    private Vector2 startingPoint;
    private Vector2 endingPoint;
    private Vector2 startToEndVector;
    private Path path;

    public RouteRenderer(ArrayList<Trackpoint> points){
        this.points = points;
        this.first = points.get(0);
        this.last = points.get(points.size()-1);
        drawPath();
    }

    public void setStartingPoint(Vector2 point){
        this.startingPoint = point;
    }

    public void setEndingPoint(Vector2 point){
        this.endingPoint = point;
    }

    private void drawPath(){
        //Initialize Path
        path = new Path();
        MoveTo moveto = new MoveTo();
        moveto.setX(startingPoint.getX());
        moveto.setY(startingPoint.getY());
        path.getElements().add(moveto);

        //Draw Trackpoints
        for(int i = 0; i < points.size(); i++){
            LineTo lineTo = new LineTo();
            lineTo.setX(points.get(i).getVectorToNext().getX());
            lineTo.setY(points.get(i).getVectorToNext().getY());
            path.getElements().add(lineTo);
        }

        //Calculate Vector from Start to End
        this.startToEndVector = new Vector2(startingPoint, endingPoint);
    }

}
