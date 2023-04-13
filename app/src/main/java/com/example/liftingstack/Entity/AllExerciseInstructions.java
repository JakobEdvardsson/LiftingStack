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
    public void removeExerciseInstructions(ExerciseInstructions exerciseInstructions)
    {
        exercisesInstructionsList.remove(exerciseInstructions);
    }
    public void addExerciseInstructions(ExerciseInstructions exerciseInstructions)
    {
     /*   for (ExerciseInstructions e : exercisesInstructionsList)
        {
            if (e.getExerciseName().equals(exerciseInstructions.getExerciseName()))
                return;
        }*/
        exercisesInstructionsList.add(exerciseInstructions);
    }
}
