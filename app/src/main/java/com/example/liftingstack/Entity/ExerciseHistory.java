package com.example.liftingstack.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ExerciseHistory {
    private HashMap<Exercise, List<ExerciseData>> exerciseDataList = new HashMap<>();

    public void addExerciseDataToList(ExerciseData exerciseData) {
        //Kollar först från hashmappen om det specifika Exercise objektet finns, sedan lägger till det i arraylistan
        //Om det inte finns så skapas ett nytt element i hashmappen och en arraylist läggs till.
        if (exerciseDataList.containsKey(exerciseData.getExercise())){
            Objects.requireNonNull(exerciseDataList.get(exerciseData.getExercise())).add(exerciseData);
        }else {
            ArrayList<ExerciseData> data = new ArrayList<>();
            data.add(exerciseData);
            exerciseDataList.put(exerciseData.getExercise(), data);
        }

    }
    public void removeExerciseDataFromList(ExerciseData exerciseData) {
        Objects.requireNonNull(exerciseDataList.get(exerciseData.getExercise())).remove(exerciseData);
    }

    public HashMap<Exercise, List<ExerciseData>> getExerciseDataList() {
        return exerciseDataList;
    }
    public void setExerciseDataList(HashMap<Exercise, List<ExerciseData>> exerciseDataList) {
        this.exerciseDataList = exerciseDataList;
    }
}
