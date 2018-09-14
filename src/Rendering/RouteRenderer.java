package Rendering;

import gpxLib.Trackpoint;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


import java.util.ArrayList;

public class RouteRenderer {
    private ArrayList<Trackpoint> trackpoints;
    private Trackpoint first;
    private Trackpoint last;
    private Vector2 firstToLastReal;

    private Vector2 startingPoint;
    private Vector2 endingPoint;
    private Vector2 startToEndVector;

    private Group group;

    public RouteRenderer(ArrayList<Trackpoint> trackpoints, Group group){
        this.group = group;
        this.trackpoints = trackpoints;
        this.first = trackpoints.get(0);
        this.last = trackpoints.get(trackpoints.size()-1);
        this.firstToLastReal = new Vector2((last.getLat() - first.getLat()), (last.getLon()- first.getLon()));
    }

    public void setStartingPoint(Vector2 point){
        this.startingPoint = point;
    }

    public void setEndingPoint(Vector2 point){
        this.endingPoint = point;
        this.startToEndVector = new Vector2(startingPoint, endingPoint);
    }

    public void calculate(){
        //Get multiplier
        double x = (startToEndVector.getX() / firstToLastReal.getX());
        double y = (startToEndVector.getY() / firstToLastReal.getY());
        Vector2 multiplier = new Vector2(x,y);

        //Multiply each element
        for(int i = 0; i < trackpoints.size()-1; i++){
            Vector2 renderVector = new Vector2(trackpoints.get(i).getVectorToNext().getX(), trackpoints.get(i).getVectorToNext().getY());
            renderVector.mult(multiplier);
            trackpoints.get(i).setRenderVector(renderVector);
        }
    }

    public void draw(Group group){
        group.getChildren().clear();
        double x = this.startingPoint.getX();
        double y = this.startingPoint.getY();
        for(int i = 0; i < trackpoints.size()-1; i++){
            Vector2 renderVector = trackpoints.get(i).getRenderVector();
            Line line = new Line(x,y, x+renderVector.getX(), y+renderVector.getY());
            line.setStrokeWidth(2.0);
            line.setStroke(Color.RED);
            group.getChildren().add(line);
            x += renderVector.getX();
            y += renderVector.getY();
        }
    }
}
