package ca.horatiu.convex_hull_visualizer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PorterDuff;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Button;


import java.util.ArrayList;
import java.util.LinkedList;

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
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        settings = new Settings(Settings.DEFAULT_START_X, Settings.DEFAULT_START_Y, Settings.DEFAULT_SKIP);
        Coordinate.populateColorConverter();
        gridRenderer = new GridView(this, grid, settings);
        setContentView(R.layout.activity_main);

        LinearLayout upper = (LinearLayout) findViewById(R.id.LinearLayout01);

        upper.addView(gridRenderer);

        scaleGestureDetector = new ScaleGestureDetector(this, new MyOnScaleGestureListener(this));
        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);

        final Button settings = (Button) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
            }
        });
    }

    public void settings(View v){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void reset(View v){
        if (points == null) //safety? no lol still crashes. odd..
            return;
        for(int x = 0; x < points.size(); x++){
            grid.setFalse(points.get(x).getX(), points.get(x).getY());
        }
        points = new ArrayList<Coordinate>();
        if (grid == null) //should this ever happen?
            return;
        grid.setPoints(new LinkedList<Coordinate>());
        hull = new Hull(new Coordinate[]{});

        refresh();
        Log.d("Refresh", "Refreshed!");
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
        Log.d("SensorTap", "Tap!" + event.toString());
        //add here...
        int xPos = (int)event.getRawX(); //why? wtf lol
        int yPos = (int)event.getRawY() - 150;
        yPos = Math.max(0, yPos);

        /* Error trap :) */
        if (xPos > GridView.width){
            xPos = GridView.width-1;
        }

        if (yPos > GridView.height){
            yPos = GridView.height-1;
        }

        int xIndex = (xPos/ GridView.getSkip() + gridRenderer.getXStart());
        int yIndex = (yPos/ GridView.getSkip() + gridRenderer.getYStart());

        if (grid == null){
            grid = new Grid(gridRenderer.getWidth(), gridRenderer.getHeight());
        }
        Log.d("Grid", xIndex + " " + yIndex + " : " + xPos + " " + yPos);
        if (Settings.SHOULD_MOVE)
            grid.setTrue(xIndex, yIndex); //update the grid this way... NOW CHANGE THE RENDERING FOR THE GRID SYSTEM!
        else
            grid.setTrue(xPos, yPos); //good enough

        points.add(new Coordinate(xPos, yPos, GridView.getSkip())); //change coordinates? ADD GET SKIP -> OK
        grid.getPoints().add(new Coordinate(xPos, yPos, 1)); //standard...
        gridRenderer = new GridView(this, grid, settings);
        setContentView(R.layout.activity_main);
        LinearLayout upper = (LinearLayout) findViewById(R.id.LinearLayout01);
        upper.addView(gridRenderer);
        return true;
    }

    public void refresh(){
        if (grid == null){
            grid = new Grid(gridRenderer.getWidth(), gridRenderer.getHeight());
        }
        gridRenderer = new GridView(this, grid, settings);
        gridRenderer = new GridView(this, grid, settings);
        setContentView(R.layout.activity_main);

        LinearLayout upper = (LinearLayout) findViewById(R.id.LinearLayout01);

        upper.addView(gridRenderer);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refresh();
    }
}
