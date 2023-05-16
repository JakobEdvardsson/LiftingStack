package com.example.liftingstack.Controller;

import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.Entity.ExerciseInstruction;

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
        ExerciseInstruction exerciseInstruction = new ExerciseInstruction(name, description);
        allExerciseInstructions.getExercisesInstructionsList().add(exerciseInstruction);
    }

    /**
     * Delete exercise.
     * Deletes an exercise from the list of exercises.
     *
     * @param exerciseInstruction the exercise instructions
     */
    public void deleteExercise(ExerciseInstruction exerciseInstruction) {
        allExerciseInstructions.getExercisesInstructionsList().remove(exerciseInstruction);
    }
}
