package com.example.liftingstack.Controller;

import com.example.liftingstack.Entity.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseController {
    private List<Exercise> exercisesList;

    public ExerciseController() {
        exercisesList = new ArrayList<>();
    }
    public void createExercise(String name, String description) {
        Exercise exercise = new Exercise(name, description);
        exercisesList.add(exercise);
    }
    public void deleteExercise(Exercise exercise) {
        exercisesList.remove(exercise);
    }
}
