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
    private Map<String, ArrayList<String>> setMap = new HashMap<>();
    // key = which set, value list of reps and weight
    private Map<String, Map<String, ArrayList<String>>> sessionMap = new HashMap<>();
    // key = training session per specific date, value = setMap

    private Map<String, Map<String, Map<String, ArrayList<String>>>> dateMap = new HashMap<>();
    // key = date (String), value = dateSessionMap
    private Map<String, Map<String, Map<String, Map<String, ArrayList<String>>>>> exerciseHistoryMap = new HashMap<>();
    // key = exersice id (String), value = dateDataMap


    public ExerciseHistoryDataMap(AppCompatActivity activity) {
        exerciseHistoryMap = new LoadFromDevice().loadExerciseHashMapFromDevice(activity, "exerciseHistory");
        if (exerciseHistoryMap != null) {
            Log.i("TestHistory TestLoad000", exerciseHistoryMap.toString());
        } else {
            exerciseHistoryMap = new HashMap<>();
        }
    }

    public Map<String, Map<String, Map<String, Map<String, ArrayList<String>>>>> getExerciseHistoryMap() {
        return exerciseHistoryMap;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public Map<String, Map<String, Map<String, ArrayList<String>>>> getDateMap(String exerciseId) {
        return exerciseHistoryMap.get(exerciseId);
    }

    public Map<String, ArrayList<String>> getSetMap(String exerciseId, String dateString, String session) {
        return exerciseHistoryMap.get(exerciseId).get(dateString).get(session);
    }


    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    //TODO kolla ifall man tränat tidigare samma dag
    public void setSetMap(String set, String reps, String weight) {
        ArrayList<String> setStringArrayList = new ArrayList<>();
        setStringArrayList.add(reps);
        setStringArrayList.add(weight);

        setMap.put(set, setStringArrayList);
    }


    public void saveExerciseHistoryMap(AppCompatActivity activity, String exerciseId, Map<String, ArrayList<String>> setMap) {
        //loads the savefile in the constructor, which returns a hashmap which is saved in instancevariable hashmap

        //kolla ifall man tränat tidigare samma dag
        if (exerciseHistoryMap.get(dateMap) != null){

            int nbrOfSessions = exerciseHistoryMap.get(dateMap).get(sessionMap).size();
            int sessionKey = nbrOfSessions + 1;
            String sessionString = Integer.toString(sessionKey);
            sessionMap.put(sessionString, setMap);
        } else {
            sessionMap.put("1", setMap);
        }





        //sets the date to today and parses it to a string
        LocalDate dateObject = LocalDate.now();
        String dateString = dateObject.toString();


        // puts the logged sets into hashmap with date as key
        dateMap.put(dateString, sessionMap);

        // puts above hashmap into hashmap with the exerciseId as key
        exerciseHistoryMap.put(exerciseId, dateMap);
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
