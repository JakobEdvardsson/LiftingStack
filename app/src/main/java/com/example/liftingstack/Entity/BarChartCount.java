package com.example.liftingstack.Entity;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.liftingstack.Controller.LoadFromDevice;
import com.example.liftingstack.ProgramsActivities.StartedPrograms.StartedProgramActivity;
import com.example.liftingstack.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.*;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.components.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BarChartCount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void onStart() {
        super.onStart();
        updateGraph();
    }

    public void updateGraph() {

        // get todays date, convert to int and remove last two digits (the days from the date)

        LocalDate dateToday = LocalDate.now();

        String stringDateToday = dateToday.toString().replace("-", "");
        int dateIntegerToday = Integer.parseInt(stringDateToday) / 100;


        // get todays date minus one year, convert to int and remove last two digits (the days from the date)

        LocalDate dateOneYearBack = dateToday.minusMonths(12);
        String stringOneYearBack = dateOneYearBack.toString().replace("-", "");
        int dateIntegerOneYearBack = Integer.parseInt(stringOneYearBack) / 100;


        //Load datesLogged and check how many times logged each month for the last 12 months
        ArrayList<Integer> datesLogged = new LoadFromDevice().loadDatesLoggedFromDevice(this, "datesLogged");


        // create a list with the days stripped away from the date
        ArrayList<Integer> monthsLogged = new ArrayList<>();
        for (int i = 0; i < datesLogged.size(); i++) {
            //strip away the days from the logged entries in savefile loggedDates
            monthsLogged.add(datesLogged.get(i)/100);
        }

        // create a BarEntry list which holds the data for the chart
        ArrayList<BarEntry> bar = new ArrayList<>();


        // check how many months has been logged for each of the past twelve months and set the value into BarEntry list
        for (int i = 0; i < 12; i++) {
            int timesThisMonth = Collections.frequency(monthsLogged, Integer.parseInt(dateToday.minusMonths(i).toString().replace("-",""))/100);
            bar.add(new BarEntry(12-i, timesThisMonth));
        }

        // set the bars to the graph and display in GUI
        BarChart barChart = findViewById(R.id.barChart);
        BarDataSet barDataSet = new BarDataSet(bar, "");
        barDataSet.setColors(Color.rgb(64,255,159));
        barDataSet.setValueTextColor((Color.rgb(0,204,102)));
        barDataSet.setValueTextSize(16f);

       barChart.getDescription().setText("");
       // barChart.getDescription().setTextSize(16f);
        barChart.animateY(2000);
        barChart.setNoDataText("No sessions logged past twelve months");
        BarData barData = new BarData(barDataSet);

        barData.addDataSet(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###,##0");
                return decimalFormat.format(value);
            }});
    }

}