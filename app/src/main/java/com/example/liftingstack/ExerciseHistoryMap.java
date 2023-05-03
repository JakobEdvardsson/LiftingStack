package com.example.liftingstack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liftingstack.Controller.LoadFromDevice;
import com.example.liftingstack.Controller.SaveToDevice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ExerciseHistoryMap extends AppCompatActivity {

    private EditText repsSet1;
    private EditText repsSet2;
    private EditText repsSet3;
    private EditText weightSet1;
    private EditText weightSet2;
    private EditText weightSet3;
    private HashMap<String, int[]> setDataMap = new HashMap<>();
    // key = vilket set(parsed integer, value = array[reps, vikt]
    private HashMap dateDataMap = new HashMap<>();
    // key = datum, value = setDataMap
    private HashMap<String, HashMap> exerciseHistoryMap = new HashMap<>();
    // key = exersice id, value = dateDataMap


    // **BNI** Vi kan använda en datepicker som det finns färdig kod för om vi vill att
    // man ska kunna välja vilket datum man loggar för, då behöver vi ha tre int som parametrar,
    // just nu kör vi på dagens datum.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_history_map);
        repsSet1 = findViewById(R.id.repsInputSet1);
        repsSet2 = findViewById(R.id.repsInputSet2);
        repsSet3 = findViewById(R.id.repsInputSet3);
        weightSet1 = findViewById(R.id.weightInputSet1);
        weightSet2 = findViewById(R.id.weightInputSet2);
        weightSet3 = findViewById(R.id.weightInputSet3);

    }

    public void saveExerciseHistory(View v) {
        // **skapa en exerciseHistoryMap i savefilen när övningen skapas
        //load exerciseHistoryFile
        // check if theres already a date for the specific date if so, add to the HashMap
        // else just create a new date HashMap
        // add as many sets as have been done in a for loop
        //exerciseHistoryMap = new LoadFromDevice().loadHashMapFromDevice(this, "exerciseHistory");

        int repsInt1 = Integer.parseInt(repsSet1.getText().toString());
        int weightInt1 = Integer.parseInt(weightSet1.getText().toString());
        int repsInt2 = Integer.parseInt(repsSet2.getText().toString());
        int weightInt2 = Integer.parseInt(weightSet2.getText().toString());

        for (int i = 1; i < 2; i++) {
            setSetDataMap(1, repsInt1, weightInt1);
            setSetDataMap(2, repsInt2, weightInt2);
        }
        setExerciseHistoryMap("1");


        // call setExerciseHistoryMap
    }

    public void setSetDataMap(int set, int reps, int weight) {
        int setArray[] = {reps, weight};
        String setString = Integer.toString(set);
        Log.i("TestHistory TestSet1",setArray.toString());
        Log.i("TestHistory TestSet2",setString);
        setDataMap.put(setString, setArray);
        Log.i("TestHistory TestSet3", " "+setDataMap.toString());

    }

    public void setExerciseHistoryMap(String exerciseId) {
        LocalDate date = LocalDate.now();
        dateDataMap.put(date, setDataMap);
        exerciseHistoryMap.put(exerciseId, dateDataMap);
        new SaveToDevice().saveHashMapToDevice(exerciseHistoryMap, this, "exerciseHistory");
        exerciseHistoryMap = new LoadFromDevice().loadHashMapFromDevice(this, "exerciseHistory");


        dateDataMap = exerciseHistoryMap.get("1"); //funkar ej pga någon casting -- BNI


        Log.i("TestHistory TestSet4", dateDataMap.toString());
        //setDataMap = dateDataMap.get(1);

    }
}