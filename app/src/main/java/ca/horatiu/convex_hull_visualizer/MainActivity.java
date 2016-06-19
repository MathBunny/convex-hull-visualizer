package ca.horatiu.convex_hull_visualizer;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

public class MainActivity extends ActionBarActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private GestureDetectorCompat mDetector;
    ScaleGestureDetector scaleGestureDetector;
    GridView gridRenderer;
    private Grid grid;

    public void setGrid(Grid grid){
        this.grid = grid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gridRenderer = new GridView(this, grid);
        setContentView(gridRenderer);
        //setContentView(R.layout.activity_main);

        scaleGestureDetector = new ScaleGestureDetector(this, new ca.horatiu.convex_hull_visualizer.MyOnScaleGestureListener(this));
        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);
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

        //add here...
        int xPos = (int)event.getX();
        int yPos = (int)event.getY();





        int xIndex = (xPos/gridRenderer.getSkip() + gridRenderer.getXStart());
        int yIndex = (yPos/gridRenderer.getSkip() + gridRenderer.getYStart());
        Log.d("SensorVal", xIndex + " " + yIndex);
        if (grid == null){
            grid = new Grid(gridRenderer.getWidth(), gridRenderer.getHeight());
        }
        grid.setTrue(xIndex, yIndex);
        gridRenderer = new GridView(this, grid);
        setContentView(gridRenderer);


        return true;
    }
}
