package com.example.liftingstack.Entity;

import java.util.ArrayList;
import java.util.List;

public class AllExerciseInstructions {
    private List<ExerciseInstructions> exercisesInstructionsList = new ArrayList<>();

    public List<ExerciseInstructions> getExercisesInstructionsList() {
        return exercisesInstructionsList;
    }

    public void setExercisesInstructionsList(List<ExerciseInstructions> exercisesInstructionsList) {
        this.exercisesInstructionsList = exercisesInstructionsList;
    }
}
