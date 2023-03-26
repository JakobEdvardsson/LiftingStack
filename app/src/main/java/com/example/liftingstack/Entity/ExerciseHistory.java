package com.example.liftingstack.Entity;

import java.util.ArrayList;
import java.util.List;

public class ExerciseHistory {
    private List<ExerciseData> exerciseDataList = new ArrayList<>();

    public List<ExerciseData> getExerciseDataList() {
        return exerciseDataList;
    }
    public void addExerciseDataToList(ExerciseData exerciseData) {
        exerciseDataList.add(exerciseData);
    }
    public void removeExerciseDataFromList(ExerciseData exerciseData) {
        exerciseDataList.remove(exerciseData);
    }
    public void setExerciseDataList(List<ExerciseData> exerciseDataList) {
        this.exerciseDataList = exerciseDataList;
    }
}
