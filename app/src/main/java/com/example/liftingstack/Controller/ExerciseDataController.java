package com.example.liftingstack.Controller;

import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.Entity.ExerciseData;

public class ExerciseDataController {

    public ExerciseData createExerciseData(ExerciseInstructions exerciseInstructions) {
        return new ExerciseData(exerciseInstructions);
    }
    public void addExerciseData(ExerciseData exerciseData, int set, double rep, double weigh) {
        exerciseData.addExcerciseData(set, rep, weigh);
    }
}
