package com.example.liftingstack.Utilities;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Entity.ExerciseInstructions;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoadUtilities extends AppCompatActivity {


    public void loadExercise(View v) {

        File file = new File(this.getFilesDir(), "Test");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            String response = stringBuilder.toString();
            convertJsonToObject(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public void convertJsonToObject(String json) {
        Gson gson = new Gson();
        ExerciseInstructions exerciseInstructions = gson.fromJson(json, ExerciseInstructions.class);
        System.out.println(json);
        //visa texten i gui -- ta bort senare
        //displayObjectOnScreen(exerciseInstructions);
    }
}
