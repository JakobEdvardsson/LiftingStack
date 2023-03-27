package com.example.liftingstack.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Workout history.
 * This class is used to store a list of workouts.
 */
public class WorkoutHistory {
    private List<Workout> workouts = new ArrayList<>();

    /**
     * Gets workouts.
     *
     * @return the workouts
     */
    public List<Workout> getWorkouts() {
        return workouts;
    }

    /**
     * Add exercise data to list.
     *
     * @param workout the workout
     */
    public void addExerciseDataToList(Workout workout) {
        workouts.add(workout);
    }

    /**
     * Remove exercise data from list.
     *
     * @param workout the workout
     */
    public void removeExerciseDataFromList(Workout workout) {
        workouts.remove(workout);
    }

    /**
     * Sets workouts.
     *
     * @param workouts the workouts
     */
    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }
}
