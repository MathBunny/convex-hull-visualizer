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

public class GridView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private Paint paint;
    private Grid grid;
    public static final String DOT_COLOR = "#f44336";
    private GestureDetectorCompat mDetector;
    private ArrayList<Coordinate> points;

    private int xStart = 0;
    private int yStart = 0;
    private int skip = 50;

    public GridView(Context context) {
        super(context);
        paint = new Paint();
        grid = new Grid(getWidth(), getHeight());
        points = new ArrayList<Coordinate>();
    }

    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
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
        canvas.drawCircle(x / 2, y / 2, radius, paint);
    }

    public void refresh(Canvas canvas){
        int xIter = 0;
        int yIter = 0;
        for(int x = xStart; x < grid.getWidth(); x+= skip){
            for(int y = yStart; y < grid.getHeight(); y += skip){
                //render if available
                if (grid.getValue(xIter, yIter)){
                    //render a circle, at coordinate (x, y)
                    canvas.drawCircle(x, y, skip/2, paint);
                }
                yIter++;
            }
            xIter++;

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d("Sensor","onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        Log.d("Sensor", "onFling: " + event1.toString()+event2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d("Sensor", "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        Log.d("Sensor", "onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d("Sensor", "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d("Sensor", "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d("Sensor", "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d("Sensor", "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d("Sensor", "onSingleTapConfirmed: " + event.toString());
        return true;
    }

}
