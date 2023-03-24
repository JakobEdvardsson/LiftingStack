package com.example.liftingstack.Entity;

import java.time.ZonedDateTime;
import java.util.List;

public class Workout {
    private List<ExerciseData> completedWorkouts;
    private ZonedDateTime workoutTime;
    private String comment;

    public Workout() {
        this.workoutTime =  ZonedDateTime.now();
    }

    public List<ExerciseData> getCompletedWorkouts() {
        return completedWorkouts;
    }

    public void setCompletedWorkouts(List<ExerciseData> completedWorkouts) {
        this.completedWorkouts = completedWorkouts;
    }
    public void addCompletedWorkouts(ExerciseData completedExercise) {
        this.completedWorkouts.add(completedExercise);
    }

    public ZonedDateTime getWorkoutTime() {
        return workoutTime;
    }

    public void setWorkoutTime(ZonedDateTime workoutTime) {
        this.workoutTime = workoutTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
