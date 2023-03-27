package com.example.liftingstack.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * The type Exercise history.
 * This class is used to store exerciseData for a specific exercise.
 */
public class ExerciseHistory {
    private HashMap<Exercise, List<ExerciseData>> exerciseDataList = new HashMap<>();

    /**
     * This method is used to add exerciseData to the exerciseDataList.
     * If the exerciseDataList already contains the exercise, the exerciseData is added to the list.
     * If the exerciseDataList does not contain the exercise, a new list is created and the exerciseData is added to the list.
     * The list is then added to the exerciseDataList.
     *
     * @param exerciseData the exercise data
     */
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

    /**
     * This method is used to remove exerciseData from the exerciseDataList.
     * @param exerciseData the exercise data
     */
    public void removeExerciseDataFromList(ExerciseData exerciseData) {
        Objects.requireNonNull(exerciseDataList.get(exerciseData.getExercise())).remove(exerciseData);
    }

    /**
     * Gets exercise data list.
     *
     * @return the exercise data list
     */
    public HashMap<Exercise, List<ExerciseData>> getExerciseDataList() {
        return exerciseDataList;
    }

    /**
     * Sets exercise data list.
     *
     * @param exerciseDataList the exercise data list
     */
    public void setExerciseDataList(HashMap<Exercise, List<ExerciseData>> exerciseDataList) {
        this.exerciseDataList = exerciseDataList;
    }
}
