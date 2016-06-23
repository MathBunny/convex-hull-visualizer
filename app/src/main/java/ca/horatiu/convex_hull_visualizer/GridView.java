package ca.horatiu.convex_hull_visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Horatiu on 18/06/2016.
 */

public class GridView extends View{
    private Paint paint;
    private Grid grid;
    private Canvas canvas;
    public static final String DOT_COLOR = "#f44336";
    private GestureDetectorCompat mDetector;
    private MainActivity main;
    private Settings settings;
    private ArrayList<Coordinate> points;

    public static int width; //size of the canvas
    public static int height;

    private int xStart = 0;
    private int yStart = 0;
    private static int skip = 10;

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

    public ArrayList<Coordinate> getPoints(){
        return points;
    }

    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        this.canvas = canvas;
        if (grid == null)
            grid = new Grid(getWidth(), getHeight());
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor(DOT_COLOR));
        refresh();
    }

    public void drawGrid(){
        
    }

    public void drawHull(){
        Hull hull = MainActivity.getHull();
        if (hull == null || hull.getPoints().length == 0)
            return;
        paint.setColor(Color.parseColor(Settings.EDGE_COLOR));
        //generate points and then wrap around!
        for(int x = 0; x < hull.getPoints().length-1; x++){ //test || NEW!
            //canvas.drawLine(hull.getPoints()[x].getX()/getSkip() + xStart, hull.getPoints()[x].getY()/getSkip() + yStart, hull.getPoints()[x+1].getX()/getSkip() + xStart, hull.getPoints()[x+1].getY()/getSkip() + yStart, paint); //you need to fix this by taking into considering xStart and everything!
            canvas.drawLine(hull.getPoints()[x].getX(), hull.getPoints()[x].getY(), hull.getPoints()[x+1].getX(), hull.getPoints()[x+1].getY(), paint);
        }
        //Last line :-)
        //canvas.drawLine(hull.getPoints()[0].getX()/getSkip() + xStart, hull.getPoints()[0].getY()/getSkip() + yStart, hull.getPoints()[hull.getPoints().length-1].getX()/getSkip() + xStart, hull.getPoints()[hull.getPoints().length-1].getY()/getSkip() + yStart, paint);
        canvas.drawLine(hull.getPoints()[0].getX(), hull.getPoints()[0].getY(), hull.getPoints()[hull.getPoints().length-1].getX(), hull.getPoints()[hull.getPoints().length-1].getY(), paint);
    }

    public void refresh(){
        if (canvas == null)
            return;
        canvas.drawColor(Color.WHITE); //clear?
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
                canvas.drawCircle(a.getX(), a.getY(), settings.SKIP_VALUE/2, paint); //skip / 2 or Settings.SKIP?
            }
        }
        drawHull();
        //invalidate();
    }

    public Grid getGrid(){
        return grid;
    }

    public int getXStart(){
        return xStart;
    }

    public int getYStart(){
        return yStart;
    }

    public static int getSkip(){
        return skip;
    }

    public static void setSkip(int skip){
        GridView.skip = skip;
    }

}
