package com.example.liftingstack.Controller;

import com.example.liftingstack.Entity.ExerciseHistory;
import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.Entity.ExerciseData;
import com.example.liftingstack.Entity.WorkoutData;
import com.example.liftingstack.Entity.WorkoutHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Exercise data controller.
 * This class is used to control the exercise data.
 */
public class ExerciseDataController {
    private List<ExerciseData> exerciseDataList = new ArrayList<>();

    //Detta ska ändras när vi gör att saker sparas till fil.
    private WorkoutHistory workoutHistory = new WorkoutHistory();
    private ExerciseHistory exerciseHistory = new ExerciseHistory();


    /**
     * Create new exercise data.
     * This method is used to create a new exercise data object.
     *
     * @param exerciseInstruction the exercise instructions
     */
    public void createNewExerciseData(ExerciseInstruction exerciseInstruction) {
        exerciseDataList.add(new ExerciseData(exerciseInstruction));
    }

    /**
     * Add exercise data set.
     * This method is used to add a new set to an exercise data object.
     *
     * @param exerciseData the exercise data
     * @param set          the set
     * @param rep          the rep
     * @param weigh        the weigh
     */
    public void addExerciseDataSet(ExerciseData exerciseData, int set, double rep, double weigh) {
        exerciseDataList.get(exerciseDataList.indexOf(exerciseData)).addExcerciseData(set, rep, weigh);
    }

    public void finishWorkout() {
        WorkoutData workoutData = new WorkoutData("test");
        workoutData.setCompletedWorkouts(exerciseDataList);
        workoutHistory.addWorkoutDataToList(workoutData);


        for (ExerciseData exerciseData : exerciseDataList) {
            exerciseHistory.addExerciseDataToList(exerciseData);
        }
    }


}
