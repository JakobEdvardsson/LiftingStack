package com.example.liftingstack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Controller.FirstTimeAppOpens;
import com.example.liftingstack.Controller.LoadFromDevice;
import com.example.liftingstack.Controller.SaveToDevice;
import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.Entity.AllPrograms;
import com.example.liftingstack.Entity.ExerciseGraph;
import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.Entity.Program;
import com.example.liftingstack.ExerciseActivities.ExerciseActivity;
import com.example.liftingstack.ProgramsActivities.ProgramActivity;
import com.example.liftingstack.Entity.BarChartCount;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Check if its the first time app opens
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor sharedEditor;
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        sharedEditor = sharedPreferences.edit();

        if (sharedPreferences.getBoolean("firstTime", true)) {
            // code for first time opening,

            firstTimeAppOpens();

            // set "firstTime" to false so that this code doesnt run again

            sharedEditor.putBoolean("firstTime", false);
            sharedEditor.commit();
            sharedEditor.apply();
        }
    }

    public void firstTimeAppOpens() {
        // Creates all built in exercises from assets folder
        ArrayList<ExerciseInstruction> builtInExercisesList = new LoadFromDevice().loadExerciseListFromAssets(this, "built_in_exercises");
        new SaveToDevice().saveListToDevice(builtInExercisesList, this, "exerciseList");


        // Creates a new list with all programs
        AllPrograms builtInProgramList = new AllPrograms(this);

        //Creates and adds program BIG 3
        Program big_3 = new Program("Big 3", "Squat, Deadlift, Bench Press");
        big_3.addExercise(builtInExercisesList.get(1).getId());
        big_3.addExercise(builtInExercisesList.get(2).getId());
        big_3.addExercise(builtInExercisesList.get(0).getId());

        builtInProgramList.addProgram(big_3);

        //Creates and adds program ARMS
        Program arms = new Program("Arms", "Biceps, Triceps");
        arms.addExercise(builtInExercisesList.get(6).getId());
        arms.addExercise(builtInExercisesList.get(5).getId());

        builtInProgramList.addProgram(arms);

        //Creates and adds program PRESS AND PULL
        Program pressAndPull  = new Program("Press and pull", "Shoulders, Arms");
        pressAndPull.addExercise(builtInExercisesList.get(3).getId());
        pressAndPull.addExercise(builtInExercisesList.get(4).getId());
        pressAndPull.addExercise(builtInExercisesList.get(7).getId());

        builtInProgramList.addProgram(pressAndPull);

        //Saves all the programs to file
        new SaveToDevice().saveListToDevice(
                builtInProgramList.
                        getProgramsList(), this, "programList");
    }



    public void launchExerciseGraph(View v) {
        Intent intent = new Intent(this, ExerciseGraph.class);
        startActivity(intent);
    }


    public void launchSettings(View v) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }



    public void launchPrograms(View v) {
        Intent intent = new Intent(this, ProgramActivity.class);
        startActivity(intent);
    }

    public void launchExercises(View v) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        startActivity(intent);
    }

    public void launchStats(View v) {
        Intent intent = new Intent(this, BarChartCount.class);
        startActivity(intent);
    }
}
