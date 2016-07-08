package ca.horatiu.convex_hull_visualizer;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by Horatiu on 19/06/2016.
 */
public class Settings {
    /** xStart int This is the starting x value. */
    private int xStart;
    /** yStart int This is the starting y value. */
    private int yStart;
    /** skip int This is the skip value. */
    private int skip;
    /** points ArrayList This is the points array. */
    ArrayList<Coordinate> points;

    /** SHOULD_MOVE boolean This variable dictates if the grid is scrollable. */
    public static boolean SHOULD_MOVE = false;
    /** SHOULD_SCALE boolean This variable dictates if the grid should scale. */
    public static boolean SHOULD_SCALE = false;
    /** DEFAULT_START_X int This variable dictates the default starting x value. */
    public static int DEFAULT_START_X = 0;
    /** DEFAULT_START_Y int This variable dictates the default starting y value. */
    public static int DEFAULT_START_Y = 0;
    /** DEFAULT_SKIP int This variable dictates the default skip value. */
    public static int DEFAULT_SKIP = 25;
    /** NODE_COLOR String This variable dictates the default node color. */
    public static String NODE_COLOR ="#2196f3"; //update this
    /** EDGE_COLOR String This is the color of the edges. */
    public static String EDGE_COLOR = "#00e676";
    /** NODE_COLOR_NAME String This is the name of the color of the nodes. */
    public static String NODE_COLOR_NAME = "Blue";
    /** EDGE_COLOR_NAME String This is the name of the color of the edges. */
    public static String EDGE_COLOR_NAME = "Green";
    /** SKIP_VALUE int This is the default skip value. */
    public static int SKIP_VALUE = 25;
    /** EDGE_WEIGHT int This is the default edge weight. */
    public static int EDGE_WEIGHT = 5;

    /**
     * This is a class constructor for settings, which initializes multiple variables.
     * @param xStart int This is the starting x value.
     * @param yStart int This is the starting y value.
     * @param skip int This is the default skip value.
     * @param points ArrayList This is the collection of points.
     */
    public Settings(int xStart, int yStart, int skip, ArrayList<Coordinate> points){
        this.xStart = xStart;
        this.yStart = yStart;
        this.skip = skip;
        this.points = points;
    }

    /**
     * This is a class constructor for Settings.
     * @param xStart This is the starting x value.
     * @param yStart This is the starting y value.
     * @param skip This is the skip value.
     */
    public Settings(int xStart, int yStart, int skip){
        this.xStart = xStart;
        this.yStart = yStart;
        this.skip = skip;
    }

    /** This method returns the points.
     * @return ArrayList This is the list of coordinates.
     */
    public ArrayList<Coordinate> getPoints(){
        return points;
    }

    /** This method sets the points.
     * @param points ArrayList These are the points being set.
     */
    public void setPoints(ArrayList<Coordinate> points){
        this.points = points;
    }

    /** This method returns the starting x coordinate.
     * @return This is the x coordinate.
     */
    public int getXStart(){
        return xStart;
    }
    /** This method returns the starting y coordinate.
     * @return This is the y coordinate.
     */
    public int getYStart(){
        return yStart;
    }

    /** This method returns the skip value.
     * @return This is the skp value.
     */
    public int getSkip(){
        return skip;
    }

    /** This method sets the x start.
     * @param xStart int This is the new starting x coordinate.
     */
    public void setXStart(int xStart){
        this.xStart = xStart;
    }

    /** This method sets the y start.
     * @param yStart int This is the new starting y coordinate.
     */
    public void setYStart(int yStart){
        this.yStart = yStart;
    }
    /** This method sets the skip.
     * @param skip int This is the new skip value.
     */
    public void setSkip(int skip){
        this.skip = skip;
    }
}
