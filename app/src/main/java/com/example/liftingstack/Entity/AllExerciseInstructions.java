package com.example.liftingstack.Entity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Controller.LoadFromDevice;
import com.example.liftingstack.Controller.SaveToDevice;

import java.util.ArrayList;

public class AllExerciseInstructions {
    private ArrayList<ExerciseInstructions> exercisesInstructionsList;

    public AllExerciseInstructions(AppCompatActivity appCompatActivity) {
        exercisesInstructionsList = new ArrayList<>();

        //TODO Load the list from the device, here should be a check if the file exists if not load from the assets folder
        try {
            exercisesInstructionsList = new LoadFromDevice().loadExerciseListFromDevice(appCompatActivity, "exerciseList");
        }catch (Exception e) {
            //TODO load from assets folder
            System.out.println("Error loading from device, file may not exist");
            e.printStackTrace();
        }
    }

    public void saveExercisesInstructionsList(AppCompatActivity appCompatActivity) {
        new SaveToDevice().saveListToDevice(exercisesInstructionsList, appCompatActivity, "exerciseList");
    }

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
