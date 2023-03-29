package com.example.liftingstack.Controller;

import com.example.liftingstack.Entity.Exercise;
import com.example.liftingstack.Entity.ExerciseData;

public class ExerciseDataController {

    public ExerciseData createExerciseData(Exercise exercise) {
        return new ExerciseData(exercise);
    }
    public void addExerciseData(ExerciseData exerciseData, int set, double rep, double weigh) {
        exerciseData.addExcerciseData(set, rep, weigh);
    }
}
