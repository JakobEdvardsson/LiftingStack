package com.example.liftingstack.Entity;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Controller.LoadFromDevice;
import com.example.liftingstack.Controller.SaveToDevice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExerciseHistoryDataMap {
    private String exerciseId;
    private Map<String, ArrayList<String>> setDataMap = new HashMap<>();
    // key = which set, value list of reps and weight
    private Map<String, Map<String, ArrayList<String>>> dateDataMap = new HashMap<>();
    // key = date (String), value = setDataMap
    private Map<String, Map<String, Map<String, ArrayList<String>>>> exerciseHistoryMap = new HashMap<>();
    // key = exersice id (String), value = dateDataMap


    public ExerciseHistoryDataMap(AppCompatActivity activity) {
        exerciseHistoryMap = new LoadFromDevice().loadExerciseHashMapFromDevice(activity, "exerciseHistory");
        if(exerciseHistoryMap != null) {
            Log.i("TestHistory TestLoad000", exerciseHistoryMap.toString());
        } else {
            exerciseHistoryMap = new HashMap<>();
        }
    }

    public Map<String, Map<String, Map<String, ArrayList<String>>>> getExerciseHistoryMap() {
        return exerciseHistoryMap;
    }

    public String getExerciseId() {
        return exerciseId;
    }
    public Map<String, Map<String, ArrayList<String>>> getDateDataMap(String exerciseId) {
        return exerciseHistoryMap.get(exerciseId);
    }
    public Map<String, ArrayList<String>> getSetDataMap(String exerciseId, String dateString) {
        return exerciseHistoryMap.get(exerciseId).get(dateString);
    }




    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setSetDataMap(String set, String reps, String weight) {
        ArrayList<String> setStringArrayList = new ArrayList<>();
        setStringArrayList.add(reps);
        setStringArrayList.add(weight);

        setDataMap.put(set, setStringArrayList);
    }


    public void saveExerciseHistoryMap(AppCompatActivity activity, String exerciseId, Map<String, ArrayList<String>> setDataMap) {
        //loads the savefile and returns a hashmap which is saved in instancevariable hashmap



        //sets the date to today and parses it to a string
        LocalDate dateObject = LocalDate.now();
        String dateString = dateObject.toString();

        // puts the logged sets into hashmap with date as key
        dateDataMap.put(dateString, setDataMap);

        // puts above hashmap into hashmap with the exerciseId as key
        exerciseHistoryMap.put(exerciseId, dateDataMap);
        Log.i("TestHistory TestLoad222", exerciseHistoryMap.toString());

        // saves above hashmap as json to file
        new SaveToDevice().saveExerciseHashMapToDevice(exerciseHistoryMap, activity, "exerciseHistory");




 /*

        // below only for testing purposes
        dateDataMap = exerciseHistoryMap.get("1");
        setDataMap = dateDataMap.get("2023-05-18");
        exerciseHistoryMap = new LoadFromDevice().loadExerciseHashMapFromDevice(activity, "exerciseHistory");

        Log.i("TestHistory TestSet4", exerciseHistoryMap.toString());
        Log.i("TestHistory TestSet5", exerciseHistoryMap.get(exerciseId).toString());

*/
    }
}
