package com.example.liftingstack.Entity;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * The type Workout.
 * This class is used to store exerciseData for a specific workout.
 */
public class WorkoutData {
    private List<ExerciseData> completedWorkouts;
    private ZonedDateTime workoutTime;
    private String comment;

    /**
     * Instantiates a new Workout.
     */
    public WorkoutData(String comment) {
        this.comment = comment;
        this.workoutTime =  ZonedDateTime.now();
    }

    /**
     * Gets completed workouts.
     *
     * @return the completed workouts
     */
    public List<ExerciseData> getCompletedWorkouts() {
        return completedWorkouts;
    }

    /**
     * Sets completed workouts.
     *
     * @param completedWorkouts the completed workouts
     */
    public void setCompletedWorkouts(List<ExerciseData> completedWorkouts) {
        this.completedWorkouts = completedWorkouts;
    }


    /**
     * Gets workout time.
     *
     * @return the workout time
     */
    public ZonedDateTime getWorkoutTime() {
        return workoutTime;
    }

    /**
     * Sets workout time.
     *
     * @param workoutTime the workout time
     */
    public void setWorkoutTime(ZonedDateTime workoutTime) {
        this.workoutTime = workoutTime;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
