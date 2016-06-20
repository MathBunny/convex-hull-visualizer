package ca.horatiu.convex_hull_visualizer;

/**
 * Created by Horatiu on 19/06/2016.
 */
import java.util.*;


class PolarOrder implements Comparator<Coordinate>{
    public int compare(Coordinate p1, Coordinate p2){
        if(p1.getY()==0&&p1.getX()>0) return 1; //angle of p1 is 0, thus p2>p1
        if(p2.getY()==0&&p2.getX()>0) return -1; //angle of p2 is 0 , thus p1>p2
        if(p1.getY()>0&&p2.getY()<0) return 1; //p1 is between 0 and 180, p2 between 180 and 360
        if(p1.getY()<0&&p2.getY()>0) return -1;
        return (CrossProduct.cross(p1,p2)); //return true if p1 is clockwise from p2
    }
}
