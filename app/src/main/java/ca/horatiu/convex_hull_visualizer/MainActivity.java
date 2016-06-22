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
        settings = new Settings(Settings.DEFAULT_START_X, Settings.DEFAULT_START_Y, Settings.DEFAULT_SKIP);
        gridRenderer = new GridView(this, grid, settings);
        setContentView(gridRenderer);

        //setContentView(R.layout.activity_main);

        scaleGestureDetector = new ScaleGestureDetector(this, new MyOnScaleGestureListener(this));
        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
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
        //calculate here? double tap to ..?
        Log.d("Sensor", "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
        float distanceY) {
        if (!Settings.SHOULD_MOVE)
            return true;
        Log.d("Sensor", "onScroll: " + e1.toString()+e2.toString());
        if (settings.getXStart()+(int)distanceX >= 0)
            setStartX(settings.getXStart()+(int)distanceX);
        if (settings.getYStart()+(int)distanceY >= 0)
            setStartY(settings.getYStart()+(int)distanceY);

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

        if (grid == null){
            grid = new Grid(gridRenderer.getWidth(), gridRenderer.getHeight());
        }
        Log.d("Grid", xIndex + " " + yIndex + " : " + xPos + " " + yPos);
        grid.setTrue(xIndex, yIndex); //update the grid this way... NOW CHANGE THE RENDERING FOR THE GRID SYSTEM!

        points.add(new Coordinate(xPos, yPos, gridRenderer.getSkip())); //change coordinates? ADD GET SKIP -> OK


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
