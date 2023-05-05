package com.example.liftingstack.Controller;

import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.Entity.ExerciseInstructions;

/**
 * The type Exercise instruction controller.
 * This class is responsible for creating and deleting exercises as well as storing them in a list.
 */
public class ExerciseInstructionController {
    private AllExerciseInstructions allExerciseInstructions;

    /**
     * Instantiates a new Exercise instruction controller.
     * Creates a new list of exercises.
     */
    public ExerciseInstructionController() {
       // allExerciseInstructions = new AllExerciseInstructions();
    }

    /**
     * Create exercise.
     * Creates a new exercise and adds it to the list of exercises.
     *
     * @param name        the name
     * @param description the description
     */
    public void createExercise(String name, String description) {
        ExerciseInstructions exerciseInstructions = new ExerciseInstructions(name, description);
        allExerciseInstructions.getExercisesInstructionsList().add(exerciseInstructions);
    }

    /**
     * Delete exercise.
     * Deletes an exercise from the list of exercises.
     *
     * @param exerciseInstructions the exercise instructions
     */
    public void deleteExercise(ExerciseInstructions exerciseInstructions) {
        allExerciseInstructions.getExercisesInstructionsList().remove(exerciseInstructions);
    }
}
