package com.example.liftingstack.Entity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.liftingstack.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartCount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        PieChart pieChart = findViewById(R.id.pieChart);

        ArrayList<PieEntry> trainingSessions = new ArrayList<>();
        trainingSessions.add(new PieEntry(10, "January"));
        trainingSessions.add(new PieEntry(11, "February"));
        trainingSessions.add(new PieEntry(12, "Mars"));
        trainingSessions.add(new PieEntry(13, "April"));
        trainingSessions.add(new PieEntry(5, "May"));
        trainingSessions.add(new PieEntry(6, "June"));
        trainingSessions.add(new PieEntry(7, "July"));
        trainingSessions.add(new PieEntry(8, "August"));

        PieDataSet pieDataSet = new PieDataSet(trainingSessions, "Number of training sessions");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Number of training sessions");
        pieChart.animate();


    }
}