package com.example.liftingstack.Entity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Controller.LoadFromDevice;
import com.example.liftingstack.Controller.SaveToDevice;

import java.util.ArrayList;

/**
 * This class is used to store all ExerciseInstructions.
 * It is used to save and load the programs from the device.
 */
public class AllExerciseInstructions {
    private ArrayList<ExerciseInstruction> exercisesInstructionsList;

    /**
     * Instantiates a new All exercise instructions.
     * Loads the programs from the device.
     * @param appCompatActivity the app compat activity
     */
    public AllExerciseInstructions(AppCompatActivity appCompatActivity) {
        exercisesInstructionsList = new ArrayList<>();
        try {
            exercisesInstructionsList = new LoadFromDevice().loadExerciseListFromDevice(appCompatActivity, "exerciseList");
        }catch (Exception e) {
            saveExercisesInstructionsList(appCompatActivity);
            System.out.println("Error loading from device, file may not exist - printed from AllExerciseInstructions");
            e.printStackTrace();
        }
    }

    /**
     * Save exercises instructions list.
     *
     * @param appCompatActivity the app compat activity
     */
    public void saveExercisesInstructionsList(AppCompatActivity appCompatActivity) {
        new SaveToDevice().saveListToDevice(exercisesInstructionsList, appCompatActivity, "exerciseList");
    }

    /**
     * Gets exercises instructions list.
     *
     * @return the exercises instructions list
     */
    public ArrayList<ExerciseInstruction> getExercisesInstructionsList() {
        return exercisesInstructionsList;
    }


    /**
     * Add exercise instructions.
     *
     * @param exerciseInstruction the exercise instructions
     */
    public void addExerciseInstructions(ExerciseInstruction exerciseInstruction) {
        exercisesInstructionsList.add(exerciseInstruction);
    }
}
