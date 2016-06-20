package ca.horatiu.convex_hull_visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

    private int xStart = 0;
    private int yStart = 0;
    private int skip = 10;

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
        int x = getWidth();
        int y = getHeight();
        int radius;
        radius = 100;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor(DOT_COLOR));
        refresh();
        //canvas.drawCircle(x / 2, y / 2, radius, paint);
    }

    public void drawHull(){
        Log.d("OK", "OK!");
        Hull hull = MainActivity.getHull();
        if (hull == null)
            return;
        paint.setColor(Color.GREEN);
        //generate points and then wrap around!
        for(int x = 0; x < hull.getPoints().length-1; x++){
            canvas.drawLine(hull.getPoints()[x].getX(), hull.getPoints()[x].getY(), hull.getPoints()[x+1].getX(), hull.getPoints()[x+1].getY(), paint);
        }
        //Last line :-)
        canvas.drawLine(hull.getPoints()[0].getX(), hull.getPoints()[0].getY(), hull.getPoints()[hull.getPoints().length-1].getX(), hull.getPoints()[hull.getPoints().length-1].getY(), paint);
    }

    public void refresh(){
        if (canvas == null)
            return;
        paint.setColor(Color.BLUE);
        int xIter = xStart;
        int yIter = yStart;
        for(int x = 0; x < grid.getWidth(); x+= skip){
            for(int y = 0; y < grid.getHeight(); y += skip){
                //render if available
                if (grid.getValue(xIter, yIter)){
                    //render a circle, at coordinate (x, y)
                    canvas.drawCircle(x, y, skip/2, paint);
                }
                yIter++;
            }
            yIter = 0; //this is the issue
            xIter++;
        }
        drawHull();
        invalidate();
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

    public int getSkip(){
        return skip;
    }

}
