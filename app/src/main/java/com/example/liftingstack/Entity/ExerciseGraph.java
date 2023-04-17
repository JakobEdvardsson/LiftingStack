package com.example.liftingstack.Entity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.w3c.dom.Entity;

import java.util.ArrayList;

public class ExerciseGraph extends AppCompatActivity {

    private static final String TAG = "ExerciseGraph";
    private LineChart lineChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_graph);

        lineChart = (LineChart) findViewById(R.id.linechart);

       // lineChart.setOnChartGestureListener(ExerciseGraph.this);
       // lineChart.setOnChartValueSelectedListener(ExerciseGraph.this);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        // test values below
        yValues.add(new Entry(0, 60f));
        yValues.add(new Entry(1, 50f));
        yValues.add(new Entry(2, 70f));
        yValues.add(new Entry(3, 30f));
        yValues.add(new Entry(4, 50f));
        yValues.add(new Entry(5, 60f));

        LineDataSet set1 = new LineDataSet(yValues, "Data Set 1"); // change name later

        set1.setFillAlpha(110);

        set1.setColor(Color.rgb(127,255,212)); // change to a nice blue/green shade later
        set1.setLineWidth(3f); // makes the lines a bit thicker
        set1.setValueTextSize(10f); // size of the text showing values in chart
        set1.setValueTextColor(Color.rgb(160,160,160));
        set1.setCircleColor(Color.rgb(127,255,212)); // might need to change later
        set1.setCircleRadius(5f); // might need to change later

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        lineChart.setData(data);
    }
}
