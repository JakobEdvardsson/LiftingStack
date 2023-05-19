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
    private Map<String, ArrayList<String>> setMap = new HashMap<>();
    // key = which set, value list of reps and weight
    private Map<String, Map<String, ArrayList<String>>> sessionMap = new HashMap<>();
    // key = training session per specific date, value = setMap

    private Map<String, Map<String, Map<String, ArrayList<String>>>> dateMap = new HashMap<>();
    // key = date (String), value = sessionMap
    private Map<String, Map<String, Map<String, Map<String, ArrayList<String>>>>> exerciseHistoryMap;
    // key = exersice id (String), value = dateMap


    public ExerciseHistoryDataMap(AppCompatActivity activity) {
        exerciseHistoryMap = new LoadFromDevice().loadExerciseHashMapFromDevice(activity, "exerciseHistory");
        if (exerciseHistoryMap != null) {
            Log.i("TestHistory Loaded:", exerciseHistoryMap.toString());
        } else {
            exerciseHistoryMap = new HashMap<>();
        }
    }

    public Map<String, Map<String, Map<String, Map<String, ArrayList<String>>>>> getExerciseHistoryMap() {
        return exerciseHistoryMap;
    }

    public Map<String, Map<String, Map<String, ArrayList<String>>>> getDateMap(String exerciseId) {
        return exerciseHistoryMap.get(exerciseId);
    }

    public Map<String, Map<String, ArrayList<String>>> getSessionMap(String exerciseId, String date) {
        return exerciseHistoryMap.get(exerciseId).get(date);
    }

    public Map<String, ArrayList<String>> getSetMap(String exerciseId, String dateString, String session) {
        return exerciseHistoryMap.get(exerciseId).get(dateString).get(session);
    }

    public void saveExerciseHistoryMap(AppCompatActivity activity, String exerciseId, Map<String, ArrayList<String>> setMap) {
        //class loads the savefile in the constructor, which returns a hashmap which is saved in instancevariable exerciseHistoryMap

        //sets the date to today and parses it to a string
        LocalDate dateObject = LocalDate.now();
        String dateString = dateObject.toString();

        this.setMap = setMap;

        //kolla ifall man tränat övningen tidigare aktuell dag
        if (exerciseHistoryMap.get(exerciseId) != null) {
            //om man har tränat samma dag:
            dateMap = exerciseHistoryMap.get(exerciseId);
            sessionMap = dateMap.get(dateString);

            int nbrOfSessions = sessionMap.size();
            int sessionKey = nbrOfSessions + 1;
            String sessionString = Integer.toString(sessionKey);

            sessionMap.put(sessionString, setMap);
        } else {
            // om man inte tränat övningen tidigare aktuell dag
            sessionMap.put("1", setMap);
        }
        // uppdatera exerciseHistoryMap med den nya datan
        dateMap.put(dateString, sessionMap);
        exerciseHistoryMap.put(exerciseId, dateMap);

        Log.i("TestHistory saved:", exerciseHistoryMap.toString());

        // saves above hashmap as json to file
        new SaveToDevice().saveExerciseHashMapToDevice(exerciseHistoryMap, activity, "exerciseHistory");
    }
}
