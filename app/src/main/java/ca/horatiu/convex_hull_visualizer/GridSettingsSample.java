package ca.horatiu.convex_hull_visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * This method draws a sample grid in the settings.
 * Created by Horatiu on 22/06/2016.
 */
public class GridSettingsSample  extends View {
    /** width int This is the width of the sample. */
    private int width;
    /** height int This is the height of the sample. */
    private int height;
    /** paint Paint This is the paint.*/
    Paint paint;

    /**
     * This method sets up the draw style and colors, along with height and width.
     * @param canvas Canvas This is the canvas where the graphics are drawn.
     */
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);

        this.width = getWidth();
        this.height = getHeight();

        drawSample(canvas);
    }

    /**
     * This method draws the sample with a triangle and given settings.
     * @param canvas Canvas This is the canvas where the lines are drawn.
     */
    public void drawSample(Canvas canvas){
        /* These are the points */
        paint.setStrokeWidth(Settings.EDGE_WEIGHT);
        Coordinate a = new Coordinate(width/2, 100);
        Coordinate b = new Coordinate(70, height-height/4);
        Coordinate c = new Coordinate(width-70, height/2+20);

        //now draw the three lines
        Log.d("Color", Settings.EDGE_COLOR);
        paint.setColor(Color.parseColor(Settings.EDGE_COLOR));
        canvas.drawLine(a.getX(), a.getY(), b.getX(), b.getY(), paint);
        canvas.drawLine(b.getX(), b.getY(), c.getX(), c.getY(), paint);
        canvas.drawLine(c.getX(), c.getY(), a.getX(), a.getY(), paint);

        paint.setColor(Color.parseColor(Settings.NODE_COLOR));
        drawPoint(canvas, a);
        drawPoint(canvas, b);
        drawPoint(canvas, c);

    }

    /** This method draws a point (circle) on the canvas.
     *
     * @param canvas Canvas This is the canvas.
     * @param point Coordinate This is the coordinate of the point.
     */
    public void drawPoint(Canvas canvas, Coordinate point){
        canvas.drawCircle(point.getX(), point.getY(), Settings.SKIP_VALUE/2, paint);
    }

    /**
     * This is the class constructor of the class.
     * @param context
     */
    public GridSettingsSample(Context context) {
        super(context);
    }
}
