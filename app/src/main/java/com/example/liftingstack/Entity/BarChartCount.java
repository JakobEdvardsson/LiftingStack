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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class BarChartCount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        BarChart barChart = findViewById(R.id.barChart);



        ArrayList<Integer> pastTwelveMonths = new ArrayList<>();
        pastTwelveMonths.add(Integer.valueOf("05"));

        pastTwelveMonths.add(7);
        pastTwelveMonths.add(6);
        pastTwelveMonths.add(5);
        pastTwelveMonths.add(4);
        pastTwelveMonths.add(7);
        pastTwelveMonths.add(8);

        Collections.sort(pastTwelveMonths);

        HashMap<Integer, Integer> timesPerMonth = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            timesPerMonth.put(i, Collections.frequency(pastTwelveMonths, i));

        }
        System.out.println(timesPerMonth.toString());


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
        //TODO ändra ev x - axel (från 2023-1 till 2023.1 eller 1, eller skriv det på ett annat sätt)

        BarDataSet barDataSet = new BarDataSet(bar, "Number of training sessions");
        //barDataSet.setFormSize(20);
        //barDataSet.setBack
        barDataSet.setColors(Color.rgb(64,255,159));
        //barDataSet.setColors(Color.rgb(0,204,102));
       // barDataSet.setColors(Color.rgb(0,0,205));
        //00CC66
        //D3D3D3
       // barDataSet.setBarShadowColor();
        barDataSet.setValueTextColor((Color.BLACK));
        barDataSet.setValueTextSize(16f);
      //  barDataSet.setBarBorderColor((Color.rgb(0,204,102)));
       // barDataSet.text

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("");
        barChart.animateY(2000);


    }
}