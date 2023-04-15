package com.example.liftingstack.Entity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.liftingstack.R;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> bar = new ArrayList<>();
        bar.add(new BarEntry(2022, 10));
        bar.add(new BarEntry(2022, 11));
        bar.add(new BarEntry(2022, 12));
        bar.add(new BarEntry(2022, 13));
        bar.add(new BarEntry(2022, 5));
        bar.add(new BarEntry(2022, 6));
        bar.add(new BarEntry(2022, 7));
        bar.add(new BarEntry(2022, 8));

        BarDataSet barDataSet = new BarDataSet(bar, "Number of training sessions");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor((Color.BLACK));
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar");


    }
}