package com.example.liftingstack.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Workout history.
 * This class is used to store a list of workouts.
 */
public class WorkoutHistory {
    private List<WorkoutData> workoutData = new ArrayList<>();

    /**
     * Gets workouts.
     *
     * @return the workouts
     */
    public List<WorkoutData> getWorkouts() {
        return workoutData;
    }

    /**
     * Add exercise data to list.
     *
     * @param workoutData the workout
     */
    public void addWorkoutDataToList(WorkoutData workoutData) {
        this.workoutData.add(workoutData);
    }

    /**
     * Remove exercise data from list.
     *
     * @param workoutData the workout
     */
    public void removeWorkoutDataFromList(WorkoutData workoutData) {
        this.workoutData.remove(workoutData);
    }

    /**
     * Sets workouts.
     *
     * @param workoutData the workouts
     */
    public void setWorkouts(List<WorkoutData> workoutData) {
        this.workoutData = workoutData;
    }
}
