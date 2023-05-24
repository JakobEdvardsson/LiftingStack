package com.example.liftingstack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Controller.FirstTimeAppOpens;
import com.example.liftingstack.Controller.ImageHandler;
import com.example.liftingstack.Controller.LoadFromDevice;
import com.example.liftingstack.Controller.SaveToDevice;
import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.Entity.AllPrograms;
import com.example.liftingstack.Entity.ExerciseGraph;
import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.Entity.Program;
import com.example.liftingstack.Entity.TreeIcons;
import com.example.liftingstack.ExerciseActivities.ExerciseActivity;
import com.example.liftingstack.ProgramsActivities.ProgramActivity;
import com.example.liftingstack.Entity.BarChartCount;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

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
    public void onStart() {
        super.onStart();
        // To get todays date plus todays date minus 1 month

        LocalDate dateToday = LocalDate.now();
        Integer dateIntegerToday = Integer.valueOf(dateToday.toString().replace("-", ""));
        LocalDate dateOneMonthBack = dateToday.minusMonths(1);
        Integer dateIntegerOneMonthBack = Integer.valueOf(dateOneMonthBack.toString().replace("-", ""));

        // get number of logged programs last 30 days from dateLogged save file
        Integer datesLoggedPast30Days = 0;
        ArrayList<Integer> datesLogged = new LoadFromDevice().loadDatesLoggedFromDevice(this, "datesLogged");
        for (int i = 0; i < datesLogged.size(); i++) {
            if(datesLogged.get(i) > dateIntegerOneMonthBack && datesLogged.get(i) <= dateIntegerToday) {
                datesLoggedPast30Days ++;
            }
        }

        // To determine which tree-icon is shown
        ArrayList<TreeIcons> treeIcons = new LoadFromDevice().loadTreeIconsFromAssets(this, "tree_icons");

        int currentTreeIcon;
        if(datesLoggedPast30Days == 0) {
            currentTreeIcon = 0;
        } else if (datesLoggedPast30Days < 2) {
            currentTreeIcon = 1;
        }else if (datesLoggedPast30Days < 5) {
            currentTreeIcon = 2;
        }else if (datesLoggedPast30Days < 8) {
            currentTreeIcon = 3;
        }else {
            currentTreeIcon = 4;
        }

        Log.i("onCreateTest Dates", datesLoggedPast30Days.toString());
        Log.i("onCreateTest Tree", currentTreeIcon + "");

        // display tree-icon
        imageView = findViewById(R.id.tree);
        imageView.setImageBitmap(new ImageHandler().convertBase64ToBitmap(treeIcons.get(currentTreeIcon).getImage()));
    }

    public void showProgressIcon() {




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

        // Creates new save file for dates logged
        new SaveToDevice().saveListToDevice(new ArrayList<>(), this, "datesLogged");
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
