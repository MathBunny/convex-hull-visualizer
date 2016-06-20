package ca.horatiu.convex_hull_visualizer;

/**
 * Created by Horatiu on 19/06/2016.
 */
public class Settings {
    private int xStart;
    private int yStart;
    private int skip;

    public Settings(int xStart, int yStart, int skip){
        this.xStart = xStart;
        this.yStart = yStart;
        this.skip = skip;
    }

    public int getXStart(){
        return xStart;
    }

    public int getYStart(){
        return yStart;
    }

    public int getSkip(){
        return skip;
    }

    public void setXStart(int xStart){
        this.xStart = xStart;
    }

    public void setYStart(int yStart){
        this.yStart = yStart;
    }

    public void setSkip(int skip){
        this.skip = skip;
    }
}
