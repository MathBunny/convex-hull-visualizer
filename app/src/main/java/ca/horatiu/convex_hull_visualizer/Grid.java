package ca.horatiu.convex_hull_visualizer;

import java.util.LinkedList;

/**
 * This class encapsulates a Grid.
 * @author Horatiu Lazu
 */

public class Grid {
    /** matrix [] [] This boolean array dictates if there is a coordinate in that location. It is only used if skip is turned on! */
    private boolean [][] matrix;
    /** width int This is the width of the grid. */
    private int width;
    /** height int This is the height of the grid. */
    private int height;
    /** hull Coordinate [] This array consists of the points that make up the hull */
    private Coordinate [] hull;
    /** points LinkedList<Coordinate> This stores a LinkedList with all of the points in the hull. */
    private LinkedList<Coordinate> points;

    /**
     * This class constructor builds a grid with specific dimensions.
     * @param width int This is the width of the grid.
     * @param height int This is the height of the grid.
     */
    public Grid(int width, int height){
        points = new LinkedList<Coordinate>();
        matrix = new boolean[width][height];
        this.width = width;
        this.height = height;
    }

    /**
     * This method returns the points.
     * @return LinkedList<Coordinate> These are the points in a LinkedList form.
     */
    public LinkedList<Coordinate> getPoints(){
        return points;
    }

    /**
     * This method sets the points to a specific value.
     * @param points LinkedList<Coordinate> These are the new points.
     */
    public void setPoints(LinkedList<Coordinate> points){
        this.points = points;
    }

    /** This method sets the hull.
     *
     * @param hull Coordinate [] This is the new hull.
     */
    public void setHull(Coordinate [] hull){
        this.hull = hull;
    }

    /**
     * This method gets the hull.
     * @return Coordinate [] This is the hull.
     */
    public Coordinate [] getHull(){
        return hull;
    }

    /** This method returns the width of the grid.
     *
     * @return int This is the width of the grid.
     */
    public int getWidth(){
        return width;
    }

    /** This method returns the height of the grid.
     *
     * @return int This is the height of the grid.
     */
    public int getHeight(){
        return height;
    }

    /** This method returns the value in the matrix for when skip is turned on.
     * @param x int This is the x coordinate.
     * @param y int This is the y coordinate.
     * @return boolean This determines if there is any coordinate in that specific location.
     */
    public boolean getValue(int x, int y){
        return matrix[x][y];
    }

    /**
     * This method sets the specific location to be true (adds a coordinate there).
     * @param x int This is the x coordinate.
     * @param y int This is the y coordinate.
     */
    public void setTrue(int x, int y){
        matrix[x][y] = true;
    }

    /**
     * This method sets the specific location to be false (removes a coordinate there).
     * @param x int This is the x coordinate.
     * @param y int This is the y coordinate.
     */

    public void setFalse(int x, int y){
        matrix[x][y] = false;
    }
}
