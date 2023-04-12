package com.example.liftingstack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liftingstack.Entity.ExerciseInstructions;

public class ExerciseInstructionsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_instructions_page);

        ExerciseInstructions exerciseInstructions = getIntent().getParcelableExtra("Exercise");
        TextView exerciseName = findViewById(R.id.ExerciseName);
        exerciseName.setText(exerciseInstructions.getExerciseName());

        EditText exerciseDescription = findViewById(R.id.ExerciseDescription);
        exerciseDescription.setText(exerciseInstructions.getExerciseDescription());
    }

    public void onSaveClick(View v) {

        EditText exerciseName = findViewById(R.id.ExerciseName);
        EditText exerciseDescription = findViewById(R.id.ExerciseDescription);
        Toast.makeText(getApplicationContext(), exerciseName.getText(), Toast.LENGTH_SHORT).show();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("exerciseName", exerciseName.getText().toString());
        resultIntent.putExtra("exerciseDescription", exerciseDescription.getText().toString());

        setResult(78, resultIntent);
        finish();
    }
}