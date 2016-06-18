package ca.horatiu.convex_hull_visualizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GridView(this));
        //setContentView(R.layout.activity_main);
    }
}
