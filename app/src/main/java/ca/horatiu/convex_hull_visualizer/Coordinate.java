package ca.horatiu.convex_hull_visualizer;
import java.util.HashMap;

/**
 * This class abstracts a coordinate.
 * @author Horatiu Lazu
 */

public class Coordinate implements Comparable<Coordinate> {
    /** x int This is the x coordinate of the coordinate. */
    private int x;
    /** y int This is the y coordinate of the coordinate. */
    private int y;
    /** skip int This is the skip value on the graph. */
    private int skip;
    /** angle double This is the angle of the coordinate and the x coordinate axis. */
    private double angle = -Double.MAX_VALUE;
    /** convertColor HashMap<String, String> This is the color converter from plain English to RGB */
    private static HashMap<String, String> convertColor = new HashMap<String, String>();

    /** This is the class constructor of a coordinate.
     *
     * @param x int This is the x coordinate.
     * @param y int This is the y coordinate.
     * @param skip int This is the value it skips on the graph.
     */
    public Coordinate(int x, int y, int skip){
        this.x = x;
        this.y = y;
        this.skip = skip;
    }

    /**
     * This is a simpler constructor for a non-dynamic graph.
     * @param x int This is the x coordinate.
     * @param y int This is the y coordinate.
     */
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * This method populates the color converter with items.
     */
    public static void populateColorConverter(){
        convertColor.put("Blue", "#2196f3");
        convertColor.put("Green", "#00e676");
        convertColor.put("Pink", "#d500f9");
        convertColor.put("Black", "#000000");
        convertColor.put("Gray", "#9e9e9e");
    }

    /** This method returns the coordinate RGB color of the selection
     * @return String This is the RGB value of the selection.
     * */
    public static String getCoordinateRGB(String selection){
        return convertColor.get(selection);
    }

    /**
     * This method sets an x value to the coordinate, if it were ever to be changed.
     * @param x int This is the x value.
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * This method sets a y value to the coordinate (if it were ever to be changed).
     * @param y int This is the y value.
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * This method returns the x value of the coordinate.
     * @return int Returns the x value.
     */
    public int getX(){
        return x;
    }

    /**
     * This method returns the y value of the coordinate.
     * @return int Returns the y value.
     */
    public int getY(){
        return y;
    }

    /**
     * This method returns the skip value on the graph.
     * @return int Returns the skip value.
     */
    public int getSkip(){return skip;}

    /**
     * This method returns the angle.
     * @return double Returns the angle.
     */
    public double getAngle(){
        return angle;
    }

    /**
     * This method sets the angle with the x axis after computing it.
     * @param angle double This is the angle calculated.
     */
    public void setAngle(double angle){
        this.angle = angle;
    }

    /**
     * This method sorts the coordinates in counter-clockwise order, by comparing their angles to the x axis.
     * @param o Coordinate This is the comparison coordinate.
     * @return int This is the end result sent to the comparable class.
     */
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

    /**
     * This method returns a String representation of a coordinate.
     * @return String This is the String representation of the coordinate.
     */
    public String toString(){
        return "(" + x + ", " + y + ") " + (angle * 180/Math.PI) + " degrees";
    }

}

