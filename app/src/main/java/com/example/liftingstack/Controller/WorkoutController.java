package com.example.liftingstack.Controller;

import com.example.liftingstack.Entity.Workout;

/**
 * The type Workout controller.
 */
public class WorkoutController {
    private Workout workout;

    /**
     * Create workout.
     */
    public void createWorkout() {
        workout = new Workout();
    }

    /**
     * Add workout comment.
     *
     * @param comment the comment
     */
    public void addWorkoutComment(String comment) {
        workout.setComment(comment);
    }

    /**
     * Gets workout.
     *
     * @return the workout
     */
    public Workout getWorkout() {
        return workout;
    }
}
