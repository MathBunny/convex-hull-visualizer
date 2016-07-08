package ca.horatiu.convex_hull_visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Horatiu on 18/06/2016.
 */

public class GridView extends View{
    /** paint Paint This is the paint variable. */
    private Paint paint;
    /** grid Grid This is the grid used on the UI. */
    private Grid grid;
    /** canvas Canvas This is the canvas reference. */
    private Canvas canvas;
    /** DOT_COLOR String This is the color of the default dot color. */
    public static final String DOT_COLOR = "#f44336";
    /** mDetector GestureDetectorCompat This is the gesture detector for multi-touch gestures. */
    private GestureDetectorCompat mDetector;
    /** main MainActivity This is the reference to the main activity. */
    private MainActivity main;
    /** settings Settings This is the settings reference, so we can identify chosen settings. */
    private Settings settings;
    /** points ArrayList<Coordinate> These are the points that are currently in the view. */
    private ArrayList<Coordinate> points;
    /** width int This is the width of the canvas. */
    public static int width; //size of the canvas
    /** height int This is the height of the canvas. */
    public static int height;
    /** xStart int This is the x starting point. */
    private int xStart = 0;
    /** yStart int This is the y starting point. */
    private int yStart = 0;
    /** skip int This is the skip value on the grid. */
    private static int skip = 10;

    /** This is the class constructor for a GridView
     *
     * @param context
     * @param grid Grid This is the Grid reference so it can populate items there.
     * @param settings Settings This is the Settings reference so it can show proper visuals.
     */
    public GridView(Context context, Grid grid, Settings settings) {
        super(context);
        this.grid = grid;
        this.settings = settings;

        xStart = settings.getXStart();
        yStart = settings.getYStart();
        skip = settings.getSkip();
        this.points = settings.getPoints();

        paint = new Paint();
        points = new ArrayList<Coordinate>();
    }

    /** This is the onDraw method which draws the graphics.
     *
     * @param canvas This is the canvas reference variable.
     */
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        if (grid == null)
            grid = new Grid(getWidth(), getHeight());
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint); // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor(DOT_COLOR));
        refresh();
    }

    /** This method draws the hull. */
    public void drawHull(){
        Hull hull = MainActivity.getHull();
        if (hull == null || hull.getPoints().length == 0)
            return;
        paint.setColor(Color.parseColor(Settings.EDGE_COLOR));
        paint.setStrokeWidth(Settings.EDGE_WEIGHT); //generate points and then wrap around!
        for(int x = 0; x < hull.getPoints().length-1; x++)
            canvas.drawLine(hull.getPoints()[x].getX(), hull.getPoints()[x].getY(), hull.getPoints()[x+1].getX(), hull.getPoints()[x+1].getY(), paint);
        canvas.drawLine(hull.getPoints()[0].getX(), hull.getPoints()[0].getY(), hull.getPoints()[hull.getPoints().length-1].getX(), hull.getPoints()[hull.getPoints().length-1].getY(), paint); //this is the last line.
    }

    /** This method refreshes the canvas.
     * If skip is permitted then the graph is drawn to scale. Otherwise, it goes through the LinkedList populating the screen.
     */
    public void refresh(){
        if (canvas == null)
            return;

        canvas.drawColor(Color.WHITE); //clear?
        drawHull();
        paint.setColor(Color.parseColor(Settings.NODE_COLOR));
        int xIter = xStart;
        int yIter = yStart;

        width = getWidth();
        height = getHeight();

        int iter = (Settings.SHOULD_MOVE ? (skip) : (1));
        if (Settings.SHOULD_MOVE) { //the rest of the variables are useless!
            for (int x = 0; x < grid.getWidth(); x += iter) {
                for (int y = 0; y < grid.getHeight(); y += iter) {
                    //render if available
                    if (Settings.SHOULD_MOVE) {
                        if (grid.getValue(xIter, yIter)) { //xIter, yIter
                            //render a circle, at coordinate (x, y)
                            canvas.drawCircle(x, y, skip / 2, paint);
                            //Log.d("Tap!", x + " " + y);
                        }
                    } else {
                        if (grid.getValue(x, y)) { //xIter, yIter
                            //render a circle, at coordinate (x, y)
                            canvas.drawCircle(x, y, skip / 2, paint);
                            //Log.d("Tap!", x + " " + y);
                        }
                    }

                    yIter++;
                }
                yIter = 0; //this is the issue
                xIter++;
            }
        }
        else{ //use a LinkedList implementation instead .. lol
            for(Coordinate a : grid.getPoints()){
                canvas.drawCircle(a.getX(), a.getY(), Settings.SKIP_VALUE /2, paint); //skip / 2 or Settings.SKIP?
            }
        }

        //invalidate();
    }

    /** This method returns the xStart.
     *
     * @return int Returns the x start.
     */
    public int getXStart(){
        return xStart;
    }

    /**
     * This method returns the y start.
     * @return int This is the y start.
     */
    public int getYStart(){
        return yStart;
    }

    /** This method returns the skip value.
     *
     * @return int This is the skip value.
     */
    public static int getSkip(){
        return skip;
    }

    /** This method sets the skip value.
     *
     * @param skip
     */
    public static void setSkip(int skip){
        GridView.skip = skip;
    }

}
