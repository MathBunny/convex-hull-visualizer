package ca.horatiu.convex_hull_visualizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.app.Activity;


import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends Activity implements AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener {
    LinearLayout upper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner nodeColor = (Spinner) findViewById(R.id.spinner2);
        Spinner edgeColor = (Spinner) findViewById(R.id.spinner);
        SeekBar sizeSeek = (SeekBar) findViewById(R.id.seekBar);
        sizeSeek.setOnSeekBarChangeListener(this); //ok??
        sizeSeek.setProgress(Settings.SKIP_VALUE);

        SeekBar edgeSeek = (SeekBar) findViewById(R.id.edgeThickness);
        edgeSeek.setOnSeekBarChangeListener(this); //ok??
        edgeSeek.setProgress(Settings.EDGE_WEIGHT);

        //nodeColor.set

        // Spinner click listener
        nodeColor.setOnItemSelectedListener(this);
        edgeColor.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Blue");
        categories.add("Green");
        categories.add("Pink");
        categories.add("Black");
        categories.add("Gray");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        nodeColor.setAdapter(dataAdapter2);
        edgeColor.setAdapter(dataAdapter);

        String defaultPosition = Settings.NODE_COLOR_NAME; //the value you want the position for
        ArrayAdapter myAdap = (ArrayAdapter) nodeColor.getAdapter(); //cast to an ArrayAdapter
        int spinnerPosition = myAdap.getPosition(defaultPosition);
        nodeColor.setSelection(spinnerPosition);

        defaultPosition = Settings.EDGE_COLOR_NAME; //the value you want the position for
        myAdap = (ArrayAdapter) edgeColor.getAdapter(); //cast to an ArrayAdapter
        spinnerPosition = myAdap.getPosition(defaultPosition);
        edgeColor.setSelection(spinnerPosition);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Log.d("Val", item.toString() + Coordinate.getCoordinateRGB(item));

        if (parent.getId() == R.id.spinner){ //this would be the first spinner :-)
            Settings.EDGE_COLOR = Coordinate.getCoordinateRGB(item);
            Settings.EDGE_COLOR_NAME = item;
        }
        else{
            Settings.NODE_COLOR = Coordinate.getCoordinateRGB(item);
            Settings.NODE_COLOR_NAME = item;
        }
        refresh();
    }


    public void refresh(){
        upper = (LinearLayout) findViewById(R.id.LinearLayout01);
        upper.removeAllViews();
        upper.addView(new GridSettingsSample(this));
        Log.d("ShouldUpdate", "Updated!");
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.seekBar) {
            Settings.SKIP_VALUE = progress;
            refresh(); //refresh everything!
        }
        else{ //edge weight!
            Settings.EDGE_WEIGHT = progress;
            refresh();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
