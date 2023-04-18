package com.example.liftingstack.ExerciseActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.R;

public class ExerciseInstructionsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_instructions_page);

        ExerciseInstructions exerciseInstructions = getIntent().getParcelableExtra("Exercise");
        TextView exerciseName = findViewById(R.id.selectedProgramName);
        exerciseName.setText(exerciseInstructions.getExerciseName());

        EditText exerciseDescription = findViewById(R.id.ExerciseDescription);
        exerciseDescription.setText(exerciseInstructions.getExerciseDescription());
    }

    public void onSaveClick(View v) {

        EditText exerciseName = findViewById(R.id.selectedProgramName);
        EditText exerciseDescription = findViewById(R.id.ExerciseDescription);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("exerciseName", exerciseName.getText().toString());
        resultIntent.putExtra("exerciseDescription", exerciseDescription.getText().toString());

        setResult(78, resultIntent);
        finish();
    }
}