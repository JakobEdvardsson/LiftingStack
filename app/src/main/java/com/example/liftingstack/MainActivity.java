package com.example.liftingstack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Controller.SaveToDevice;
import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.Entity.ExerciseGraph;
import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.ExerciseActivities.ExerciseActivity;
import com.example.liftingstack.ProgramsActivities.ProgramActivity;
import com.example.liftingstack.Entity.BarChartCount;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

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
        Log.i("TestFirstTime1" , "ok");


        if (sharedPreferences.getBoolean("firstTime", true)) {
            // code for first time opening,
            Log.i("TestFirstTime2" , "ok");
            firstTimeAppOpens();



            // set "firstTime" to false so that this code doesnt run again
            sharedEditor.putBoolean("firstTime", false);
            sharedEditor.commit();
            sharedEditor.apply();
        }
    }

    public void firstTimeAppOpens(){
        // Creates all built in exercises
        Log.i("TestFirstTime3" , "ok");

        AllExerciseInstructions allExerciseInstructions = new AllExerciseInstructions(this);
        allExerciseInstructions.addExerciseInstructions(new ExerciseInstruction("Bench Press", "Chest, Shoulders, Arms"));
        allExerciseInstructions.addExerciseInstructions(new ExerciseInstruction("Barbell Squat", "Legs, Butt, Core"));
        allExerciseInstructions.addExerciseInstructions(new ExerciseInstruction("Deadlift", "Hamstrings, Butt, Back, Core"));
        allExerciseInstructions.addExerciseInstructions(new ExerciseInstruction("Dumbbell Overhead Press", "Shoulders, Arms"));
        allExerciseInstructions.addExerciseInstructions(new ExerciseInstruction("Lats Pulldowns", "Back, Shoulders"));
        allExerciseInstructions.addExerciseInstructions(new ExerciseInstruction("Triceps Pulldowns", "Triceps"));
        allExerciseInstructions.addExerciseInstructions(new ExerciseInstruction("Biceps Dumbell Curls", "Biceps"));
        allExerciseInstructions.addExerciseInstructions(new ExerciseInstruction("Barbell Rows", "Back, Arms, Core"));



        allExerciseInstructions.saveExercisesInstructionsList(this);

        //Create all built in programs from above exercises
        Log.i("TestFirstTime4" , "ok");

        //Create empty save files for logged exercises and for logged programs
        new SaveToDevice().saveExerciseHashMapToDevice(null, this, "exerciseHistory");
        new SaveToDevice().saveProgramHashMapToDevice(null, this, "programHistory");
        Log.i("TestFirstTime5" , "ok");


        Toast.makeText(this, "Created savefiles", Toast.LENGTH_LONG);

    }

    public void launchExerciseHistoryMap(View v) {
        Intent intent = new Intent(this, ExerciseHistoryMap.class);
        startActivity(intent);
    }

    public void launchExerciseGraph(View v) {
        Intent intent = new Intent(this, ExerciseGraph.class);
        startActivity(intent);
    }


    public void launchSettings(View v) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void launchProfile(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
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
