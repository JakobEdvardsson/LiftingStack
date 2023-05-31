package com.example.liftingstack.ExerciseActivities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Controller.LoadFromDevice;
import com.example.liftingstack.Entity.ExerciseHistoryDataMap;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Map;

public class GraphAlgorithm {

    public ArrayList<Entry> getGraphData(int typeOfGraphData, AppCompatActivity activity, String idForExercise) {
        Map<String, Map<String, Map<String, Map<String, ArrayList<String>>>>> exerciseHistoryMap = new ExerciseHistoryDataMap(activity).getExerciseHistoryMap();
        Map<String, Map<String, Map<String, ArrayList<String>>>> dateDataMap = exerciseHistoryMap.get(idForExercise);

        ArrayList<Entry> totalLoadYValues = new ArrayList<>();



        int counter = 1;


        if (dateDataMap != null) {
            for (Map<String, Map<String, ArrayList<String>>> dateMap : dateDataMap.values()) {
                for (Map<String, ArrayList<String>> sessionMap : dateMap.values()) {

                    switch (typeOfGraphData) {
                        case 0:
                            //TotalWeight
                            totalLoadYValues.add(new Entry(counter, calculateTotalWeight(sessionMap)));
                            break;
                        case 1:
                            //OneRepMax
                            totalLoadYValues.add(new Entry(counter, calculateOneRepMaxData(sessionMap)));
                            break;
                        case 2:
                            //Average Weight Per Set
                            totalLoadYValues.add(new Entry(counter, calculateAverageWeightPerSet(sessionMap)));
                            break;
                        case 3:
                            //Max Weight
                            totalLoadYValues.add(new Entry(counter, calculateMaxWeight(sessionMap)));
                            break;
                        case 4:
                            //Total Reps
                            totalLoadYValues.add(new Entry(counter, calculateTotalReps(sessionMap)));
                            break;

                    }
                    counter++;
                }
            }
        }

        return totalLoadYValues;
    }

    private float calculateTotalReps(Map<String, ArrayList<String>> sessionMap) {

        int totalReps = 0;

        for (ArrayList<String> setMap : sessionMap.values()) {
            totalReps += Integer.parseInt(setMap.get(0));

        }


        return totalReps;
    }

    public ArrayList<Entry> getEffortGraphData(AppCompatActivity activity) {
        Map<Integer, ArrayList<Integer>> loggedEffort = new LoadFromDevice().loadEffortFromDevice(activity, "effortLogged");


        ArrayList<Entry> totalLoadYValues = new ArrayList<>();


        int effort;

        int counter = 1;


        if (loggedEffort != null) {
            for (ArrayList<Integer> sessionAndEffort : loggedEffort.values()) {


                for (int i = 0; i < sessionAndEffort.size(); i+=2) {



                        effort = sessionAndEffort.get(1+i);
                        totalLoadYValues.add(new Entry(counter, effort));
                        counter++;
                    }
                }
            }
            return totalLoadYValues;
        }

    /*
    To get number of reps use: Integer.parseInt(setMap.get(0));
    To get weight use: Float.parseFloat(setMap.get(1));
    */
        public float calculateTotalWeight (Map < String, ArrayList < String >> sessionMap){
            float totalWeight = 0;

            for (ArrayList<String> setMap : sessionMap.values()) {

                int totalReps = Integer.parseInt(setMap.get(0));
                totalWeight += Float.parseFloat(setMap.get(1)) * totalReps;

            }

            return totalWeight;
        }

        public float calculateOneRepMaxData (Map < String, ArrayList < String >> sessionMap){

            int totalReps = 0;
            float totalWeight = 0;
            int sets = 0;

            for (ArrayList<String> setMap : sessionMap.values()) {
                sets++;
                totalReps += Integer.parseInt(setMap.get(0));
                totalWeight += Float.parseFloat(setMap.get(1));

            }
            float avgWeightPerSet = totalWeight / sets;
            float aveWeight = totalWeight / totalReps;


            return (float) (avgWeightPerSet / (1.0278 - (0.0278 * totalReps)));
        }

        public float calculateAverageWeightPerSet (Map < String, ArrayList < String >> sessionMap){

            float totalWeight = 0;
            int sets = 0;

            for (ArrayList<String> setMap : sessionMap.values()) {
                sets++;
                int totalReps = Integer.parseInt(setMap.get(0));
                totalWeight += Float.parseFloat(setMap.get(1)) * totalReps;
            }

            return (totalWeight / sets);


        }
        public float calculateMaxWeight (Map < String, ArrayList < String >> sessionMap){
            float maxWeight = 0;

            for (ArrayList<String> setMap : sessionMap.values()) {
                if (Float.parseFloat(setMap.get(1)) > maxWeight)
                    maxWeight = Float.parseFloat(setMap.get(1));
            }
            return maxWeight;
        }
    }
