package com.example.liftingstack.Entity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.liftingstack.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartCount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> bar = new ArrayList<>();
        bar.add(new BarEntry(1, 10));
        bar.add(new BarEntry(2, 2));
        bar.add(new BarEntry(3, 5));
        bar.add(new BarEntry(4, 13));
        bar.add(new BarEntry(5, 0));
        bar.add(new BarEntry(6, 0));
        bar.add(new BarEntry(7, 0));
        bar.add(new BarEntry(8, 0));
        bar.add(new BarEntry(9, 0));
        bar.add(new BarEntry(10, 0));
        bar.add(new BarEntry(11, 0));
        bar.add(new BarEntry(12, 0));
        //ändra ev x - axel (från 2023-1 till 2023.1 eller 1, eller skriv det på etta annat sätt)

        BarDataSet barDataSet = new BarDataSet(bar, "Number of training sessions");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor((Color.BLACK));
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Number of training sessions");
        barChart.animateY(2000);

    }
}