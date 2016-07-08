package ca.horatiu.convex_hull_visualizer;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;

/** This class detects pinch/zoom to change the weight of the edges and nodes.
 * @author Horatiu Lazu
 */
public class MyOnScaleGestureListener extends SimpleOnScaleGestureListener{
    MainActivity main;

    /**
     * This is the class constructor.
     * @param main This is the new main reference variable.
     */
    public MyOnScaleGestureListener(MainActivity main){
        this.main = main;
    }

    /** This method is called whenever a pinch/zoom happened.
     * It adjusts the skip value and scale factor accordingly.
     * @param detector This is the detector reference.
     * @return boolean This indicates if any pinch/zoom happened.
     */
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
        }

        //IGNORE ALL RESIZING FOR NOW!!!!! NO MORE!!!
        if (Settings.SHOULD_SCALE)
            main.setSkip(skipValue);
        main.refresh();
        //main.setSkip(((int)detector.getScaleFactor() > 0)?(main.getSettings().getSkip()+1):(main.getSettings().getSkip()-1));
        return true;
    }

    /**
     * This method determines if any scaling has occured, and if it started it returns true.
     * @param detector
     * @return
     */
    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    /** This method is called when scaling has stopped.
     * @param detector
     */
    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {}


}
