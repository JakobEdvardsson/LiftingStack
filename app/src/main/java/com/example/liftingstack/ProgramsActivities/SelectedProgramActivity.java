package com.example.liftingstack.ProgramsActivities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.Entity.Program;
import com.example.liftingstack.ExerciseActivities.ExerciseInstructionsPage;
import com.example.liftingstack.ExerciseActivities.ExerciseRecyclerViewInterface;
import com.example.liftingstack.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelectedProgramActivity extends AppCompatActivity implements ExerciseRecyclerViewInterface {
    private List<ExerciseInstructions> exercises = new ArrayList<>();
    private SelectedProgramRecyclerViewAdapter selectedProgramAdapter;
    private RecyclerView recyclerView;
    private ExerciseInstructions currentExerciseInstructions;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.d(TAG, "onActivityResult: ");
                    if (result.getResultCode() == 78) {
                        Intent data = result.getData();
                        if (data != null) {
                            String exerciseName = data.getStringExtra("exerciseName");
                            String exerciseDescription = data.getStringExtra("exerciseDescription");
                            currentExerciseInstructions.setExerciseName(exerciseName);
                            currentExerciseInstructions.setExerciseDescription(exerciseDescription);

                            Objects.requireNonNull(recyclerView.getAdapter()).notifyItemChanged(exercises.indexOf(currentExerciseInstructions));
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_program);

        recyclerView = findViewById(R.id.selectedProgramRecyclerView);
        setUpExerciseList();
        selectedProgramAdapter = new SelectedProgramRecyclerViewAdapter(this, exercises, this);
        recyclerView.setAdapter(selectedProgramAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Program program = getIntent().getParcelableExtra("Program");
        EditText programName = findViewById(R.id.selectedProgramName);
        programName.setText(program.getName());

        EditText programDescription = findViewById(R.id.selectedProgramDescription);
        programDescription.setText(program.getDescription());
    }

    public void saveOnClick(View v) {

        EditText programName = findViewById(R.id.selectedProgramName);
        EditText programDescription = findViewById(R.id.selectedProgramDescription);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("programName", programName.getText().toString());
        resultIntent.putExtra("programDescription", programDescription.getText().toString());

        setResult(78, resultIntent);
        finish();
    }

    private void setUpExerciseList() {
        for (int i = 1; i <= 20; i++) {
            exercises.add(new ExerciseInstructions("Exercise " + i , "Dips"));
        }
    }

    @Override
    public void onExerciseClick(ExerciseInstructions exerciseInstructions) {
        currentExerciseInstructions = exerciseInstructions;
        Intent intent = new Intent(this, ExerciseInstructionsPage.class);
        intent.putExtra("Exercise", exerciseInstructions);

        activityResultLauncher.launch(intent);
    }

    @Override
    public void saveAndUpdateList(int index) {

    }
}