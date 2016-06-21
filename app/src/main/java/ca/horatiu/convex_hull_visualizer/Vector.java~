package ca.horatiu.convex_hull_visualizer;

/**
 * Created by Horatiu on 19/06/2016.
 */


class Vector{
    private int x;
    private int y;

    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    /* Use cross product */
    public boolean turnsLeft(Vector v){
        if (getY() * v.getX() < getX() * v.getY()){
            return true;
        }
        return false;
    }
}

