package com.example.liftingstack.Entity;

import java.util.ArrayList;
import java.util.List;

public class WorkoutHistory {
    private List<Workout> workouts = new ArrayList<>();

    public List<Workout> getWorkouts() {
        return workouts;
    }
    public void addExerciseDataToList(Workout workout) {
        workouts.add(workout);
    }
    public void removeExerciseDataFromList(Workout workout) {
        workouts.remove(workout);
    }
    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }
}
