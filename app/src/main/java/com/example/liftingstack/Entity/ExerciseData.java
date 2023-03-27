package com.example.liftingstack.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExerciseData {
    private Exercise exercise;
    private HashMap<Integer, List<Double>> exerciseData;
    //key = set, List[0] = rep, List[1] = weigh

    public ExerciseData(Exercise exercise) {
        this.exercise = exercise;
        exerciseData = new HashMap<>();
    }

    public HashMap<Integer, List<Double>> getExerciseData() {
        return exerciseData;
    }

    public void addExcerciseData(int set, double rep, double weigh) {
        List<Double> data = new ArrayList<>();
        data.add(rep);
        data.add(weigh);
        exerciseData.put(set, data);
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setExerciseData(HashMap<Integer, List<Double>> exerciseData) {
        this.exerciseData = exerciseData;
    }
}
