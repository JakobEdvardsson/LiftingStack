package com.example.liftingstack.Controller;

import android.content.Context;
import android.util.Log;

import com.example.liftingstack.Entity.ExerciseInstruction;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FirstTimeAppOpens {

    // Check to see if it is first time app was opened (put the code in this comment in mainActivity)
    //imports:
    //import android.content.Context;
    //import android.content.SharedPreferences;
    //Add below in onCreate() method (still in mainActivity):
    /*

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEditor;
    sharedPreferences = getPreferences(Context.MODE_PRIVATE);
    sharedEditor = sharedPreferences.edit();

            if (sharedPreferences.getBoolean("firstTime", true)) {
        // ***** put code here for first time opening,
        // reading from assets etc, creating empty savefile for exercises,
        // programs and history ****
            // call below method "testFirstTimeAppOpened" for example

        // set "firstTime to false so that this code doesnt run again
        sharedEditor.putBoolean("firstTime", false);
        sharedEditor.commit();
        sharedEditor.apply();
            }
    */





    public <T> ArrayList<T> testFirstTimeOpened(Context context) {
        ArrayList<T> list;
        String json = null;

        try {
            // reads data in assets file

            // needs a context to work, pass from mainActivity
            InputStream inputStream = context.getAssets().open("built_in_exercises"); // needs a context to work
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            // converts to string
            json = new String(buffer, "UTF-8");

            // converts to StringBuilder
            StringBuilder jsonString = new StringBuilder();
            jsonString.append(json);

            // Logs data for testing
            Log.i("TestAssets", json);
            Log.i("TestAssets2", String.valueOf(jsonString));

            // Converts to ArrayList of ExerciseInstructions
            Gson g = new Gson();
            Type listType = new TypeToken<ArrayList<ExerciseInstruction>>(){}.getType();
            list = g.fromJson(String.valueOf(jsonString), listType);

            //TODO create "exerciseList" file and save the list in "exerciseList" file, do the same for programs


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
}
