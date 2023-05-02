package com.example.liftingstack.ExerciseActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.MainActivity;
import com.example.liftingstack.R;

import java.util.Objects;

public class ExerciseActivity extends AppCompatActivity implements ExerciseRecyclerViewInterface {

    private AllExerciseInstructions allExerciseInstructions = new AllExerciseInstructions();
    private ExerciseInstructions currentExerciseInstructions = null;
    private RecyclerView recyclerView;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 78) {
                        Intent data = result.getData();
                        if (data != null) {
                            String exerciseName = data.getStringExtra("exerciseName");
                            String exerciseDescription = data.getStringExtra("exerciseDescription");
                            allExerciseInstructions.addExerciseInstructions(new ExerciseInstructions(exerciseName, exerciseDescription));

                            Objects.requireNonNull(recyclerView.getAdapter()).notifyItemChanged(
                                    allExerciseInstructions.getExercisesInstructionsList().indexOf(currentExerciseInstructions));

                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        // RecyclerView
        recyclerView = findViewById(R.id.listExercise);
        setUpExerciseList();
        ExerciseRecyclerViewAdapter exerciseAdapter = new ExerciseRecyclerViewAdapter(
                this, allExerciseInstructions.getExercisesInstructionsList(), this);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add exercise button
        findViewById(R.id.addIcon).setOnClickListener(view -> {
            Intent intent = new Intent(this, ExerciseInstructionsPage.class);
            activityResultLauncher.launch(intent);
        });
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setUpExerciseList() {
        for (int i = 1; i <= 20; i++) {
            allExerciseInstructions.addExerciseInstructions(new ExerciseInstructions("Exercise " + i, "Hard exercise"));
        }
    }

    @Override
    public void onExerciseClick(ExerciseInstructions exerciseInstructions) {
        currentExerciseInstructions = exerciseInstructions;
        Intent intent = new Intent(this, ExerciseInstructionsPage.class);
        intent.putExtra("Exercise", exerciseInstructions);
        activityResultLauncher.launch(intent);
    }


    /**
     * Temporary button just to show temporary way of saving and loading an exercise - BNI
     * /
     * @param
     */

    public void launchCreateCustomExercise(View v){
        Intent intent = new Intent(this, CreateCustomExerciseActivity.class);
        startActivity(intent);
    }

}