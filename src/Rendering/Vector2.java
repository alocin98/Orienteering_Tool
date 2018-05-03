package Rendering;

public class Vector2 {

    private double x, y;

    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 from, Vector2 to){
        this.x = to.getX() - from.getX();
        this.y = to.getY() - from.getY();
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double Y){
        this.y = y;
    }

    public void mult(Vector2 v){
        x = v.getX() * x;
        y = v.getY() * y;
    }

    public double dist(){
        return Math.sqrt((x*x)+(y*y));
    }

    public String toString(){
        return "x: "+ x +" y: "+ y;
    }
}
