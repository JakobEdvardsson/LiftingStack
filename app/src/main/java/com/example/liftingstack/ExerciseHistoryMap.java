package com.example.liftingstack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.time.LocalDate;
import java.util.HashMap;

public class ExerciseHistoryMap extends AppCompatActivity {

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
    }

    public void saveExerciseHistory(View v) {
        // **skapa en exerciseHistoryMap i savefilen när övningen skapas
        //load exerciseHistoryFile
        // check if theres already a date for the specific date if so, add to the HashMap
        // else just create a new date HashMap
        // add as many sets as have been done in a for loop

        // call setExerciseHistoryMap
    }

    public void setSetDataMap(int set, int reps, int weight) {
        int setArray[] = {reps, weight};
        String setString = Integer.toString(set);
        setDataMap.put(setString, setArray);
    }

    public void setExerciseHistoryMap(int exerciseId, int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        dateDataMap.put(date, setDataMap);

    }
}