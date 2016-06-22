package ca.horatiu.convex_hull_visualizer;

/**
 * Created by Horatiu on 19/06/2016.
 */

public class CrossProduct{
    public static int cross(Coordinate p1, Coordinate p2){
        return p1.getX() * p2.getY() - p1.getY() * p2.getX();
    }
}

