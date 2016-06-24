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

        if (detector.getScaleFactor() > 1.0){
            if (skipValue < 100)
                 skipValue++;

            if (Settings.EDGE_WEIGHT < 80){
                Settings.EDGE_WEIGHT++;
            }
            if (Settings.SKIP_VALUE < 80){
                Settings.SKIP_VALUE++;
            }

            //skipValue = 20;
        }
        else{
            if (skipValue > 5)
                skipValue--;

            if (Settings.EDGE_WEIGHT >= 1){
                Settings.EDGE_WEIGHT--;
            }
            if (Settings.SKIP_VALUE >= 1){
                Settings.SKIP_VALUE--;
            }
            //skipValue = 5;
        }

        //IGNORE ALL RESIZING FOR NOW!!!!! NO MORE!!!
        if (Settings.SHOULD_SCALE)
            main.setSkip(skipValue);
        main.refresh();
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
