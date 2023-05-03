package com.example.liftingstack.Controller;


import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Entity.ExerciseInstructions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

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
    public <T> ArrayList<T> loadListFromDevice(AppCompatActivity activity, String fileName){

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
            Type listType = new TypeToken<ArrayList<ExerciseInstructions>>(){}.getType();
            list = g.fromJson(String.valueOf(jsonString), listType);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public HashMap<String, HashMap> loadHashMapFromDevice(AppCompatActivity activity, String fileName){

        HashMap<String, HashMap> hashMap;
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
            Type listType = new TypeToken<HashMap<String, Object>>(){}.getType();
            hashMap = g.fromJson(String.valueOf(jsonString), listType);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Log.i("TestHistory loadhashmap", hashMap.toString());
        return hashMap;
    }



}
