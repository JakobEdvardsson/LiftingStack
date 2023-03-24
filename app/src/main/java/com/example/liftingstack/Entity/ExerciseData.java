package com.example.liftingstack.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExerciseData {
    private Exercise exercise;
    private HashMap<Integer, List<Double>> excerciseData;
    //key = set, list[0] = rep, List[1] = weigh

    public ExerciseData(Exercise exercise) {
        this.exercise = exercise;
        excerciseData = new HashMap<>();
    }

    public HashMap<Integer, List<Double>> getExcerciseData() {
        return excerciseData;
    }

    public void addExcerciseData(int set, double rep, double weigh) {
        List<Double> data = new ArrayList<>();
        data.add(rep);
        data.add(weigh);
        excerciseData.put(set, data);
    }
}
