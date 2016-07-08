package ca.horatiu.convex_hull_visualizer;

/**
 * This is a utility class that determines the cross product.
 * @author Horatiu Lazu
 */

public class CrossProduct{
    /** This method determines the cross product in 2D.
     * @param p1 Coordinate This is the first comparison coordinate.
     * @param p2 COordinate This is the second comparison coordinate.
     * @return int This is the result of cross product.
     */
    public static int cross(Coordinate p1, Coordinate p2){
        return p1.getX() * p2.getY() - p1.getY() * p2.getX();
    }
}

