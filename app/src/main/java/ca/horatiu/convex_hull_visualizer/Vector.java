package ca.horatiu.convex_hull_visualizer;

/**
 * This class is used to encapsulate a vector.
 * @author Horatiu Lazu
 */

class Vector{
    /** x int This is the x coordinate. */
    private int x;
    /** y int This is the y coordinate. */
    private int y;

    /** This is the class constructor.
     * @param x This is the x coordinate.
     * @param y This is the y coordinate.
     */
    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    /** This method returns the x value.
     * @return int This is the x value.
     */
    public int getX(){
        return x;
    }

    /** This method returns the y value.
     * @return int This is the y value.
     */
    public int getY(){
        return y;
    }

    /** This method uses 2D cross product to determine if the vector turns left.
     * @return boolean If it turns left.
      * */
    public boolean turnsLeft(Vector v){
        if (getY() * v.getX() < getX() * v.getY()){ //<=
            System.out.println("Turns left: " + getY() * v.getX() + " " +  getX() * v.getY());
            return true;
        }
        System.out.println("Turns left: No! " + getY() * v.getX() + " " +  getX() * v.getY());
        return false;
    }
}

