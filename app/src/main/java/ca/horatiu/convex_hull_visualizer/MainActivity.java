package ca.horatiu.convex_hull_visualizer;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private GestureDetectorCompat mDetector;
    ScaleGestureDetector scaleGestureDetector;
    GridView gridRenderer;
    private Grid grid;
    private Settings settings;
    private ArrayList<Coordinate> points = new ArrayList<Coordinate>();
    private static Hull hull; //ehh, seems a bit sketchy lol.

    public void setGrid(Grid grid){
        this.grid = grid;
    }

    public Settings getSettings(){
        return settings;
    }

    public void setStartX(int x){
        settings.setXStart(x);
        refresh();
    }

    public void setStartY(int y){
        settings.setYStart(y);
        refresh();
    }

    public void setSkip(int skip){
        settings.setSkip(skip);
        refresh();
    }

    public static Hull getHull(){
        return hull;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = new Settings(0, 0, 10);
        gridRenderer = new GridView(this, grid, settings);
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
        //compute?
        Coordinate [] arr = new Coordinate[points.size()];
        for(int x = 0; x < points.size(); x++){
            arr[x] = points.get(x);
        }
        GrahamScan solve = new GrahamScan(arr); //return the size?
        hull = new Hull(solve.solve());
        refresh();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d("Sensor", "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d("SensorTap", "onSingleTapConfirmed: " + event.toString());

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
        points.add(new Coordinate(xPos, yPos)); //change coordinates?
        gridRenderer = new GridView(this, grid, settings);
        setContentView(gridRenderer);

        return true;
    }

    private void refresh(){
        if (grid == null){
            grid = new Grid(gridRenderer.getWidth(), gridRenderer.getHeight());
        }
        gridRenderer = new GridView(this, grid, settings);
        setContentView(gridRenderer);
    }
}
