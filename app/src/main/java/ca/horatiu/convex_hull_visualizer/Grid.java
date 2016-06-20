package ca.horatiu.convex_hull_visualizer;

/**
 * Created by Horatiu on 18/06/2016.
 */

public class Grid {
    private boolean [][] matrix;
    private int width;
    private int height;
    private Coordinate [] hull;

    public Grid(int width, int height){
        matrix = new boolean[width][height];
        this.width = width;
        this.height = height;
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
