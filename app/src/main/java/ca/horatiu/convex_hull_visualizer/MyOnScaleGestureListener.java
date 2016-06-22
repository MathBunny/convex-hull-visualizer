package ca.horatiu.convex_hull_visualizer;
import android.util.Log;
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
        int skipValue = main.getSettings().getSkip();

        if ((int)detector.getScaleFactor() > 0){
            if (skipValue < 100)
                 skipValue++;
            //skipValue = 20;
        }
        else{
            if (skipValue > 5)
                skipValue--;
            //skipValue = 5;
        }

        //IGNORE ALL RESIZING FOR NOW!!!!! NO MORE!!!
        if (Settings.SHOULD_SCALE)
            main.setSkip(skipValue);
        //main.setSkip(((int)detector.getScaleFactor() > 0)?(main.getSettings().getSkip()+1):(main.getSettings().getSkip()-1));
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {}
}
