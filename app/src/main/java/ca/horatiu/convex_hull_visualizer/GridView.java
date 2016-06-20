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
    private ArrayList<Coordinate> points;
    private MainActivity main;
    private Settings settings;

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

        paint = new Paint();
        points = new ArrayList<Coordinate>();
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
        Log.d("EXECUTED", "executed");
        refresh();
        //canvas.drawCircle(x / 2, y / 2, radius, paint);
    }

    public void refresh(){
        paint.setColor(Color.BLUE);
        int xIter = xStart;
        int yIter = yStart;
        for(int x = 0; x < grid.getWidth(); x+= skip){
            for(int y = 0; y < grid.getHeight(); y += skip){
                //render if available
                if (grid.getValue(xIter, yIter)){
                    //render a circle, at coordinate (x, y)
                    Log.d("canvas", (canvas == null) + "");
                    canvas.drawCircle(x, y, skip/2, paint);
                    Log.d("Refresh", "Drawn!");
                }
                yIter++;
                Log.d("iter", xIter + " " + yIter);
            }
            yIter = 0; //this is the issue
            xIter++;
        }
        Log.d("Refresh", "Refrshed!");
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
