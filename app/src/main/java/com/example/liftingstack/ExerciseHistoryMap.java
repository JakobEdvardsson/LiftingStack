package com.example.liftingstack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.HashMap;

public class ExerciseHistoryMap extends AppCompatActivity {

    private EditText repsSet1;
    private TextView repsSet2;
    private TextView repsSet3;
    private TextView weightSet1;
    private TextView weightSet2;
    private TextView weightSet3;
    private HashMap<String, Object> setDataMap;
    // key = vilket set(parsed integer, value = array[reps, vikt]
    private HashMap<LocalDate, Object> dateDataMap;
    // key = datum, value = setDataMap
    private HashMap<String, Object> exerciseHistoryMap;
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

        int repsInt1 = Integer.parseInt(repsSet1.getText().toString());
        int weightInt1 = Integer.parseInt(weightSet1.getText().toString());

        for (int i = 0; i < 1; i++) {
            setSetDataMap(i, repsInt1, weightInt1);
        }



        // call setExerciseHistoryMap
    }

    public void setSetDataMap(int set, int reps, int weight) {
        int setArray[] = {reps, weight};
        String setString = Integer.toString(set);
        setDataMap.put(setString, setArray);
        Log.i("TestSet1", " "+setDataMap.toString());
    }

    public void setExerciseHistoryMap(int exerciseId, int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        dateDataMap.put(date, setDataMap);

    }
}