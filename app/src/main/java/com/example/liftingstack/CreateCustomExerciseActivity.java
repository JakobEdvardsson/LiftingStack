package com.example.liftingstack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.liftingstack.Entity.ExerciseInstructions;

public class CreateCustomExerciseActivity extends AppCompatActivity {
    EditText customExerciseNameInput;
    EditText customExerciseDescriptionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_custom_exercise);

        customExerciseNameInput = (EditText) findViewById(R.id.customExerciseNameInput);
        customExerciseDescriptionInput = (EditText) findViewById(R.id.customExerciseDescriptionInput);
        isStorageReadable();
        isStorageWritable();
    }

    public void saveCustomExercise(View v) {
        String customExerciseName = customExerciseNameInput.getText().toString();
        String customExerciseDescription = customExerciseDescriptionInput.getText().toString();
        System.out.println(customExerciseName);
        ExerciseInstructions customExercise = new ExerciseInstructions(customExerciseName, customExerciseDescription);
    }

    public boolean isStorageWritable() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            System.out.println("Storage is writable");
            Log.i("Storage", "Storage is writable");
            return true;
        } else {
            Log.i("Storage", "Storage is not writable");
            return false;
        }
    }
    public boolean isStorageReadable() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ||
                Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            System.out.println("Storage is readable");
            Log.i("Storage", "Storage is readable");
            return true;
        } else {
            Log.i("Storage", "Storage is not readable");
            return false;
        }
    }
}
