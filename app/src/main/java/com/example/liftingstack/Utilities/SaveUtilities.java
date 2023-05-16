package com.example.liftingstack.Utilities;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.Entity.Program;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveUtilities extends AppCompatActivity {

    public void saveExerciseInstruction(ExerciseInstruction exerciseInstruction) {

        String json = convertObjectToJson(exerciseInstruction);
        try {
            File file = new File(this.getFilesDir(), "Exercises");

            // if we want to append the saved file we can use ** new FileWriter(file, true); **
            //https://stackoverflow.com/questions/69582517/how-can-i-save-every-data-in-json-file-android-studio

            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(json);
            bufferedWriter.close();
            System.out.println("File saved");
            Log.i("Saved file", "Exercise saved to "+this.getFilesDir()+ "/Exercises");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveProgram(Program program) {

    }
    public String convertObjectToJson(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        System.out.println(json);
        return json;
    }


}
