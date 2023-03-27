package com.example.liftingstack.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type Exercise data.
 * This class is data for the exercise object such as the number of reps and weight for each set.
 */
public class ExerciseData {
    private Exercise exercise;
    private HashMap<Integer, List<Double>> exerciseData;
    //key = set, List[0] = rep, List[1] = weigh

    /**
     * Instantiates a new Exercise data.
     *
     * @param exercise the exercise
     */
    public ExerciseData(Exercise exercise) {
        this.exercise = exercise;
        exerciseData = new HashMap<>();
    }

    /**
     * Gets exercise data.
     *
     * @return the exercise data
     */
    public HashMap<Integer, List<Double>> getExerciseData() {
        return exerciseData;
    }

    /**
     * Add excercise data.
     *
     * @param set   the set
     * @param rep   the rep
     * @param weigh the weigh
     */
    public void addExcerciseData(int set, double rep, double weigh) {
        List<Double> data = new ArrayList<>();
        data.add(rep);
        data.add(weigh);
        exerciseData.put(set, data);
    }

    /**
     * Gets exercise.
     *
     * @return the exercise
     */
    public Exercise getExercise() {
        return exercise;
    }

    /**
     * Sets exercise.
     *
     * @param exercise the exercise
     */
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    /**
     * Sets exercise data.
     *
     * @param exerciseData the exercise data
     */
    public void setExerciseData(HashMap<Integer, List<Double>> exerciseData) {
        this.exerciseData = exerciseData;
    }
}
