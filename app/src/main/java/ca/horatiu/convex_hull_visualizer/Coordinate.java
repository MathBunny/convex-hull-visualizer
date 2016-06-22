package ca.horatiu.convex_hull_visualizer;

/**
 * Created by Horatiu on 18/06/2016.
 */

public class Coordinate implements Comparable<Coordinate> {
    private int x;
    private int y;
    private int skip;
    private double angle = -Double.MAX_VALUE;

    public Coordinate(int x, int y, int skip){
        this.x = x;
        this.y = y;
        this.skip = skip;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getSkip(){return skip;}

    public double getAngle(){
        return angle;
    }

    public void setAngle(double angle){
        this.angle = angle;
    }

    public int compareTo(Coordinate o){
        if (this.angle-o.angle < 0){
            return -1;
        }
        else if (this.angle == o.angle){
            if (this.y < o.getY()){
                return -1;
            }
            return 1;
        }
        return 1;
    }

    public String toString(){
        return "(" + x + ", " + y + ") " + (angle * 180/Math.PI) + " degrees";
    }

}

