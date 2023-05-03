package com.example.liftingstack.Entity;

import java.util.ArrayList;

public class AllExerciseInstructions {
    private ArrayList<ExerciseInstructions> exercisesInstructionsList = new ArrayList<>();

    public ArrayList<ExerciseInstructions> getExercisesInstructionsList() {
        return exercisesInstructionsList;
    }

    public void setExercisesInstructionsList(ArrayList<ExerciseInstructions> exercisesInstructionsList) {
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
