package com.example.liftingstack.Controller;


import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Save to device.
 * This class is used to save data to the device.
 */
public class SaveToDevice {


    /**
     * Save list to device.
     *
     * @param <T>        the type parameter
     * @param listToSave the list to save
     * @param activity   the activity
     * @param fileName   the file name
     */
    public <T> void saveListToDevice(ArrayList<T> listToSave, AppCompatActivity activity, String fileName){
        String json = new Gson().toJson(listToSave);
        File file = new File(activity.getFilesDir(), fileName);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(json);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveHashMapToDevice(HashMap<String, HashMap> hashMap, AppCompatActivity activity, String fileName){
        String json = new Gson().toJson(hashMap);
        File file = new File(activity.getFilesDir(), fileName);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(json);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Log.i("TestHistory savehashmap", hashMap.toString());
    }
}
