package ca.horatiu.convex_hull_visualizer;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;

import ca.horatiu.convex_hull_visualizer.MainActivity;

/**
 * Created by Horatiu on 14/06/2016.
 */
public class MyOnScaleGestureListener extends SimpleOnScaleGestureListener{

    MainActivity main;

    public MyOnScaleGestureListener(MainActivity main){
        this.main = main;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        Log.d("size", detector.getScaleFactor() + "");
        main.setSkip((int)detector.getScaleFactor());
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {}
}
