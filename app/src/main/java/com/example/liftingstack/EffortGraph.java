package com.example.liftingstack;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.liftingstack.Entity.ExerciseGraph;
import com.example.liftingstack.ExerciseActivities.GraphAlgorithm;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class EffortGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effort_graph);
        setupGraph(new GraphAlgorithm().getEffortGraphData(this));
    }

    public void setupGraph(ArrayList<Entry> loadData) {
        //LineChart
        LineChart lineChart = findViewById(R.id.effortLinechart);


        //The line
        LineData line = new LineData();
        int greenColor = Color.rgb(0, 204, 102);
        int blueColor = Color.rgb(0, 0, 205);


        if (loadData.size() > 0) {
            //TODO Change "Total load" to something relevant to the graph
            LineDataSet dataSet = new LineDataSet(loadData, null);

            //Styling for the line
            dataSet.setLineWidth(3f); // makes the lines a bit thicker
            dataSet.setValueTextSize(15f); // size of the text showing values in chart
            dataSet.setCircleRadius(5f);


            dataSet.setColor(blueColor);
            dataSet.setCircleColor(greenColor);
            dataSet.setValueTextColor(greenColor);


            line.addDataSet(dataSet);
            lineChart.setData(line);
        }else {
            lineChart.clear();
        }


        //Description
        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description);



        //Styling for the chart

        XAxis xAxis = lineChart.getXAxis();
        YAxis yAxisLeft = lineChart.getAxisLeft();
        YAxis yAxisRight = lineChart.getAxisRight();


        //xAxis
        xAxis.setTextColor(greenColor);
        xAxis.setTextSize(15f);
        xAxis.setValueFormatter(new EffortGraph.MyValueFormatter());

        line.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###,##0.0");
                return decimalFormat.format(value);
            }});

        if (loadData.size() < 5) {
            xAxis.setLabelCount(loadData.size(), false);
        }else {
            xAxis.setLabelCount(5, false);
        }
        xAxis.setGranularity(1f);


        //AxisRight
        yAxisRight.setTextColor(greenColor);
        //yAxisRight.setXOffset(15f);
        yAxisRight.setTextSize(15f);

        //AxisLeft
        yAxisLeft.setTextColor(greenColor);
        //yAxisLeft.setXOffset(15f);
        yAxisLeft.setTextSize(15f);


        yAxisRight.setGranularity(1f);
        yAxisRight.setGranularityEnabled(true);

        yAxisLeft.setGranularity(1f);
        yAxisLeft.setGranularityEnabled(true);


        lineChart.setExtraOffsets(5, 10, 5, 10);

        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);



        description.setTextColor(greenColor);
        description.setTextSize(15f);
        description.setXOffset(15f);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        lineChart.invalidate(); // refresh
    }

    private class MyValueFormatter extends ValueFormatter {

        private DecimalFormat mFormat;

        public MyValueFormatter() {
            //mFormat = new DecimalFormat("###,###,###.0"); // use one decimal
            mFormat = new DecimalFormat("###,###,###"); // use one decimal

        }

        @Override
        public String getFormattedValue(float value) {
            return "" + mFormat.format(value);
        }
    }
}