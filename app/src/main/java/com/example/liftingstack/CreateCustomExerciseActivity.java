package com.example.liftingstack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    }

    public void saveCustomExercise(View v) {
        String customExerciseName = customExerciseNameInput.getText().toString();
        String customExerciseDescription = customExerciseDescriptionInput.getText().toString();
        System.out.println(customExerciseName);
        ExerciseInstructions customExercise = new ExerciseInstructions(customExerciseName, customExerciseDescription);
    }
}
