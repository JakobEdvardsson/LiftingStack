package com.example.liftingstack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Controller.FirstTimeAppOpens;
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
        // Creates all built in exercises from assets folder
        ArrayList<ExerciseInstruction> builtInExercisesList = new FirstTimeAppOpens().testFirstTimeOpened(this);
        new SaveToDevice().saveListToDevice(builtInExercisesList, this, "exerciseList");

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
