package com.example.liftingstack.ExerciseActivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Controller.ImageHandler;
import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.Entity.ExerciseHistoryDataMap;
import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class ExerciseInstructionsPage extends AppCompatActivity {
    private ImageView imageView;
    private ExerciseInstruction currentExerciseInstruction;
    private AllExerciseInstructions allExerciseInstructions;
    private String idForExercise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_instructions_page);

        allExerciseInstructions = new AllExerciseInstructions(this);
        idForExercise = getIntent().getStringExtra("ExerciseID");


        boolean exerciseFound = false;
        //Get the exercise from the list
        for (ExerciseInstruction exercise : allExerciseInstructions.getExercisesInstructionsList()) {
            if (exercise.getId().equals(idForExercise)) {
                currentExerciseInstruction = exercise;
                exerciseFound = true;
                break;
            }
        }

        if (!exerciseFound) {
            currentExerciseInstruction = new ExerciseInstruction("New Exercise", "New Description");
        }


        TextView exerciseName = findViewById(R.id.selectedProgramName);
        exerciseName.setText(currentExerciseInstruction.getExerciseName());

        EditText exerciseDescription = findViewById(R.id.ExerciseDescription);
        exerciseDescription.setText(currentExerciseInstruction.getExerciseDescription());


        imageView = findViewById(R.id.exerciseImage);
        if (currentExerciseInstruction.getImage() != null) {
            imageView.setImageBitmap(new ImageHandler().convertBase64ToBitmap(currentExerciseInstruction.getImage()));
        }

        //LineChart
        //TODO Going to get changed so that we can have different graphs
        //setupGraph(getTotalLoadData());
        setupSpinner();
    }


    public void setupSpinner() {
        Spinner spinner = findViewById(R.id.typeOfExerciseGraphSpinner);

        String[] items = {"Total Weight Lifted", "One Rep Max", "Average Weight lifted Per Set", "Max Weight" , "Averate sets", "Average Reps"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle item selection
                setupGraph(new GraphAlgorithm().getGraphData(position,ExerciseInstructionsPage.this, idForExercise));
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
            LineDataSet dataSet = new LineDataSet(loadData, "Calculated Per Workout");

            //Styling for the line
            dataSet.setLineWidth(3f); // makes the lines a bit thicker
            dataSet.setValueTextSize(15f); // size of the text showing values in chart
            dataSet.setCircleRadius(5f);


            dataSet.setColor(Color.rgb(0, 204, 102));
            dataSet.setValueTextColor(Color.rgb(0, 204, 102));
            dataSet.setCircleColor(Color.rgb(0, 204, 102));


            line.addDataSet(dataSet);
            lineChart.setData(line);
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
        xAxis.setValueFormatter(new MyValueFormatter());
        xAxis.setLabelCount(2, true);

        //AxisRight
        yAxisRight.setTextColor(colorForText);
        //yAxisRight.setXOffset(15f);
        yAxisRight.setTextSize(15f);

        //AxisLeft
        yAxisLeft.setTextColor(colorForText);
        //yAxisLeft.setXOffset(15f);
        yAxisLeft.setTextSize(15f);


        yAxisRight.setGranularity(1.0f);
        yAxisRight.setGranularityEnabled(true);

        yAxisLeft.setGranularity(1.0f);
        yAxisLeft.setGranularityEnabled(true);


        lineChart.setExtraOffsets(5, 10,5,10);

        lineChart.getLegend().setTextColor(colorForText);
        lineChart.getLegend().setTextSize(15f);


        lineChart.getDescription().setTextColor(colorForText);
        lineChart.getDescription().setTextSize(15f);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        lineChart.invalidate(); // refresh
    }


    public void rotateImage(View v) {
        try {
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            ImageHandler imageHandler = new ImageHandler();
            Bitmap bitmap = imageHandler.convertBase64ToBitmap(currentExerciseInstruction.getImage());
            // Rotate the bitmap
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            currentExerciseInstruction.setImage(imageHandler.convertImageToBase64(bitmap));
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSaveClick(View v) {

        currentExerciseInstruction.setExerciseName(((TextView) findViewById(R.id.selectedProgramName)).getText().toString());
        currentExerciseInstruction.setExerciseDescription(((EditText) findViewById(R.id.ExerciseDescription)).getText().toString());

        if (!allExerciseInstructions.getExercisesInstructionsList().contains(currentExerciseInstruction)) {
            allExerciseInstructions.addExerciseInstructions(currentExerciseInstruction);
        } else {
            for (ExerciseInstruction exercise : allExerciseInstructions.getExercisesInstructionsList()) {
                if (exercise.getId().equals(idForExercise)) {
                    exercise.setExerciseName(currentExerciseInstruction.getExerciseName());
                    exercise.setExerciseDescription(currentExerciseInstruction.getExerciseDescription());
                    exercise.setImage(currentExerciseInstruction.getImage());
                    break;
                }
            }
        }
        allExerciseInstructions.saveExercisesInstructionsList(this);
        Toast.makeText(this, "Exercise saved", Toast.LENGTH_SHORT).show();


        finish();
    }

    public void imageChooser(View v) {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        chooseImage.launch(i);
    }

    ActivityResultLauncher<Intent> chooseImage
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                            //Resizing the Bitmap to fit the ImageView
                            Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                                    selectedImageBitmap, 300, 300, false);

                            currentExerciseInstruction.setImage(new ImageHandler().convertImageToBase64(resizedBitmap));
                            imageView.setImageBitmap(resizedBitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    private class MyValueFormatter extends ValueFormatter {

        private DecimalFormat mFormat;

        public MyValueFormatter() {
            //mFormat = new DecimalFormat("###,###,###.0"); // use one decimal
            mFormat = new DecimalFormat("###,###,###"); // use one decimal
        }

        @Override
        public String getFormattedValue(float value) {
            return  mFormat.format(value);
        }
    }


}