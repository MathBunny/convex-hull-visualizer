package ca.horatiu.convex_hull_visualizer;

import java.util.ArrayList;

/**
 * Created by Horatiu on 19/06/2016.
 */
public class Settings {
    private int xStart;
    private int yStart;
    private int skip;
    ArrayList<Coordinate> points;

    public static boolean SHOULD_MOVE = false;
    public static boolean SHOULD_SCALE = false;
    public static int DEFAULT_START_X = 0;
    public static int DEFAULT_START_Y = 0;
    public static int DEFAULT_SKIP = 25;

    public Settings(int xStart, int yStart, int skip, ArrayList<Coordinate> points){
        this.xStart = xStart;
        this.yStart = yStart;
        this.skip = skip;
        this.points = points;
    }

    public Settings(int xStart, int yStart, int skip){
        this.xStart = xStart;
        this.yStart = yStart;
        this.skip = skip;
    }

    public ArrayList<Coordinate> getPoints(){
        return points;
    }

    public void setPoints(ArrayList<Coordinate> points){
        this.points = points;
    }

    public int getXStart(){
        return xStart;
    }

    public int getYStart(){
        return yStart;
    }

    public int getSkip(){
        return skip;
    }

    public void setXStart(int xStart){
        this.xStart = xStart;
    }

    public void setYStart(int yStart){
        this.yStart = yStart;
    }

    public void setSkip(int skip){
        this.skip = skip;
    }
}
