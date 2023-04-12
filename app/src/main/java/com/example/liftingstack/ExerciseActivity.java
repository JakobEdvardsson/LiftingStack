package com.example.liftingstack;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.Entity.Program;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity implements ExerciseRecyclerViewInterface {

    //private List<ExerciseInstructions> exerciseInstructions = new ArrayList<>();
    private AllExerciseInstructions allExerciseInstructions = new AllExerciseInstructions();
    private ExerciseInstructions currentExerciseInstructions = null;
    private RecyclerView recyclerView;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.d(TAG, "onActivityResult: ");
                    if (result.getResultCode() == 78) {
                        Intent data = result.getData();
                        if (data != null) {
                            String exerciseName = data.getStringExtra("exerciseName");
                            //Toast.makeText(getApplicationContext(), "Exercise saved" + exerciseName, Toast.LENGTH_SHORT).show();

                            String exerciseDescription = data.getStringExtra("exerciseDescription");
                            currentExerciseInstructions.setExerciseName(exerciseName);
                            currentExerciseInstructions.setExerciseDescription(exerciseDescription);

                            recyclerView.getAdapter().notifyDataSetChanged();

                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        recyclerView = findViewById(R.id.listExercise);
        setUpExerciseList();
        ExerciseRecyclerViewAdapter exerciseAdapter = new ExerciseRecyclerViewAdapter(this, allExerciseInstructions.getExercisesInstructionsList(), this);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        //System.out.println("XXXXXXXXXXXXXXXX " + exerciseInstructions);
        //Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
        currentExerciseInstructions = exerciseInstructions;
        Intent intent = new Intent(this, ExerciseInstructionsPage.class);
        intent.putExtra("Exercise", exerciseInstructions);

        activityResultLauncher.launch(intent);

    }


}