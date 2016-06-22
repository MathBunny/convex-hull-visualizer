package ca.horatiu.convex_hull_visualizer;

import java.util.LinkedList;

/**
 * Created by Horatiu on 18/06/2016.
 */

public class Grid {
    private boolean [][] matrix;
    private int width;
    private int height;
    private Coordinate [] hull;
    private LinkedList<Coordinate> points;

    public Grid(int width, int height){
        points = new LinkedList<Coordinate>();
        matrix = new boolean[width][height];
        this.width = width;
        this.height = height;
    }

    public LinkedList<Coordinate> getPoints(){
        return points;
    }

    public void setPoints(LinkedList<Coordinate> points){
        this.points = points;
    }

    public void setHull(Coordinate [] hull){
        this.hull = hull;
    }

    public Coordinate [] getHull(){
        return hull;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public boolean getValue(int x, int y){
        return matrix[x][y];
    }

    public void setTrue(int x, int y){
        matrix[x][y] = true;
    }

    public void setFalse(int x, int y){
        matrix[x][y] = false;
    }
}
