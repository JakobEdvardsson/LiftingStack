package com.example.liftingstack.Controller;


import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.Entity.Program;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * The type Load from device.
 * This class is used to load data from the device.
 */
public class LoadFromDevice{


    /**
     * Load list from device.
     *
     * @param <T>      the type parameter
     * @param fileName the file name
     * @return the array list
     */
    public <T> ArrayList<T> loadExerciseListFromDevice(AppCompatActivity activity, String fileName){

        ArrayList<T> list;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(new File(activity.getFilesDir(), fileName)));

            // read the contents of the file as a string
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            // close the reader
            reader.close();

            Gson g = new Gson();
            Type listType = new TypeToken<ArrayList<ExerciseInstruction>>(){}.getType();
            list = g.fromJson(String.valueOf(jsonString), listType);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public <T> ArrayList<T> loadExerciseListFromAssets(Context context, String fileName){

        ArrayList<T> list;
        String json = null;

        try {
            // reads data in assets file

            // needs a context to work, pass from mainActivity
            InputStream inputStream = context.getAssets().open(fileName); // needs a context to work
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            // converts to string
            json = new String(buffer, "UTF-8");

            // converts to StringBuilder
            StringBuilder jsonString = new StringBuilder();
            jsonString.append(json);

            // Converts to ArrayList of ExerciseInstructions
            Gson g = new Gson();
            Type listType = new TypeToken<ArrayList<ExerciseInstruction>>(){}.getType();
            list = g.fromJson(String.valueOf(jsonString), listType);


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }


    public <T> ArrayList<T> loadProgramListFromDevice(AppCompatActivity activity, String fileName){

        ArrayList<T> list;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(new File(activity.getFilesDir(), fileName)));

            // read the contents of the file as a string
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            // close the reader
            reader.close();

            Gson g = new Gson();
            Type listType = new TypeToken<ArrayList<Program>>(){}.getType();
            list = g.fromJson(String.valueOf(jsonString), listType);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public Map<String, Map<String, Map<String, ArrayList<String>>>> loadExerciseHashMapFromDevice(AppCompatActivity activity, String fileName){

        Map<String, Map<String, Map<String, ArrayList<String>>>> hashMap;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(new File(activity.getFilesDir(), fileName)));

            // read the contents of the file as a string
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            // close the reader
            reader.close();

            Gson g = new Gson();
            Type listType = new TypeToken<Map<String, Object>>(){}.getType();
            hashMap = g.fromJson(String.valueOf(jsonString), listType);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        //Log.i("TestHistory TestLoad111", hashMap.toString());

        return hashMap;
    }

    public Map<String, String> loadProgramHashMapFromDevice(AppCompatActivity activity, String fileName){

        Map<String, String> hashMap;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(new File(activity.getFilesDir(), fileName)));

            // read the contents of the file as a string
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            // close the reader
            reader.close();

            Gson g = new Gson();
            Type listType = new TypeToken<Map<String, String>>(){}.getType();
            hashMap = g.fromJson(String.valueOf(jsonString), listType);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return hashMap;
    }


}
