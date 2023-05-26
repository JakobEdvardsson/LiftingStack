package com.example.liftingstack.Entity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Controller.ImageHandler;
import com.example.liftingstack.ExerciseActivities.GraphAlgorithm;
import com.example.liftingstack.R;
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

public class ExerciseGraph extends AppCompatActivity {

    private AllExerciseInstructions allExerciseInstructions;
    private ArrayList<String> exerciseInstructionsNames = new ArrayList<>();

    private ExerciseInstruction currentExerciseInstruction;
    private String idForExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_graph);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        allExerciseInstructions = new AllExerciseInstructions(this);

        for (ExerciseInstruction exerciseInstruction : allExerciseInstructions.getExercisesInstructionsList()) {
            exerciseInstructionsNames.add(exerciseInstruction.getExerciseName());
        }
        setupExerciseSpinner();
        setupGraphSpinner();


        if(getIntent().getStringExtra("ExerciseName") != null) {
            String exerciseName = getIntent().getStringExtra("ExerciseName");
            for (int i = 0; i < exerciseInstructionsNames.size(); i++) {
                if (exerciseInstructionsNames.get(i).equals(exerciseName)) {
                    Spinner spinner = findViewById(R.id.chooseExerciseSpinner);
                    spinner.setSelection(i);
                    break;
                }
            }
        }

    }


    public void setupExerciseSpinner() {
        Spinner spinner = findViewById(R.id.chooseExerciseSpinner);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, exerciseInstructionsNames);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentExerciseInstruction = allExerciseInstructions.getExercisesInstructionsList().get(position);
                idForExercise = currentExerciseInstruction.getId();
                setupGraph(new GraphAlgorithm().getGraphData(position, ExerciseGraph.this, idForExercise));

                ImageView imageView = findViewById(R.id.exerciseImage);
                if (currentExerciseInstruction.getImage() != null) {
                    imageView.setImageBitmap(new ImageHandler().convertBase64ToBitmap(currentExerciseInstruction.getImage()));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle empty selection
            }
        });

    }


    public void setupGraphSpinner() {
        Spinner spinner = findViewById(R.id.typeOfExerciseGraphSpinner1);

        String[] items = {"Total Weight Lifted", "One Rep Max", "Average Weight lifted Per Set", "Max Weight", "Effort"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TODO just for testing, remove later, and remove else below (else if -> if)
                if(position == 4) {
                    setupGraph(new GraphAlgorithm().getEffortGraphData(ExerciseGraph.this));
                }
                // Handle item selection
                else if (currentExerciseInstruction != null) {
                    setupGraph(new GraphAlgorithm().getGraphData(position, ExerciseGraph.this, idForExercise));
                }
                // Perform actions based on the selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle empty selection
            }
        });

    }


    public void setupGraph(ArrayList<Entry> loadData) {
        //LineChart
        LineChart lineChart = findViewById(R.id.linechart);


        //The line
        LineData line = new LineData();


        if (loadData.size() > 0) {
            //TODO Change "Total load" to something relevant to the graph
            LineDataSet dataSet = new LineDataSet(loadData, null);

            //Styling for the line
            dataSet.setLineWidth(3f); // makes the lines a bit thicker
            dataSet.setValueTextSize(15f); // size of the text showing values in chart
            dataSet.setCircleRadius(5f);


            dataSet.setColor(Color.rgb(0, 204, 102));
            dataSet.setValueTextColor(Color.rgb(0, 204, 102));
            dataSet.setCircleColor(Color.rgb(0, 204, 102));


            line.addDataSet(dataSet);
            lineChart.setData(line);
        }else {
            lineChart.clear();
        }


        //Description
        Description description = new Description();
        description.setText(currentExerciseInstruction.getExerciseName());
        lineChart.setDescription(description);

        int colorForText = Color.rgb(255, 20, 147);

        //Styling for the chart

        XAxis xAxis = lineChart.getXAxis();
        YAxis yAxisLeft = lineChart.getAxisLeft();
        YAxis yAxisRight = lineChart.getAxisRight();


        //xAxis
        xAxis.setTextColor(colorForText);
        xAxis.setTextSize(15f);
        xAxis.setValueFormatter(new ExerciseGraph.MyValueFormatter());

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
        yAxisRight.setTextColor(colorForText);
        //yAxisRight.setXOffset(15f);
        yAxisRight.setTextSize(15f);

        //AxisLeft
        yAxisLeft.setTextColor(colorForText);
        //yAxisLeft.setXOffset(15f);
        yAxisLeft.setTextSize(15f);


        yAxisRight.setGranularity(3f);
        yAxisRight.setGranularityEnabled(true);

        yAxisLeft.setGranularity(1f);
        yAxisLeft.setGranularityEnabled(true);


        lineChart.setExtraOffsets(5, 10, 5, 10);

        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);



        description.setTextColor(colorForText);
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
            return mFormat.format(value);
        }
    }
}
