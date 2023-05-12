package com.example.liftingstack.ExerciseActivities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.MainActivity;
import com.example.liftingstack.R;

public class ExerciseActivity extends AppCompatActivity implements ExerciseRecyclerViewInterface {

    private AllExerciseInstructions allExerciseInstructions;
    private RecyclerView recyclerView;
    private Button button;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> setupRecyclerView()
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        // RecyclerView
        recyclerView = findViewById(R.id.listExercise);
        // Setup RecyclerView and creates allExerciseInstructions object
        setupRecyclerView();

    }

    private void saveToFile() {
        allExerciseInstructions.saveExercisesInstructionsList(this);
    }


    public void setupRecyclerView() {
        // Create a new AllExerciseInstructions which will load all exercises from file
        allExerciseInstructions = new AllExerciseInstructions(this);

        // Create a new ExerciseRecyclerViewAdapter which will display all exercises
        ExerciseRecyclerViewAdapter exerciseAdapter = new ExerciseRecyclerViewAdapter(
                this, allExerciseInstructions.getExercisesInstructionsList(), this, allExerciseInstructions);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void newExercise(View v) {
        Intent intent = new Intent(this, ExerciseInstructionsPage.class);
        activityResultLauncher.launch(intent);
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onExerciseClick(ExerciseInstructions exerciseInstructions) {
        Intent intent = new Intent(this, ExerciseInstructionsPage.class);
        intent.putExtra("ExerciseID", exerciseInstructions.getId());

        activityResultLauncher.launch(intent);
    }

    @Override
    public void removeExerciseAndUpdateList(int index) {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_pop_up_text);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.pop_up_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.pop_up_animation;

        Button delete = dialog.findViewById(R.id.btn_delete);
        Button cancel = dialog.findViewById(R.id.btn_cancel);
        dialog.show();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(this, "Exercise deleted", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

                allExerciseInstructions.getExercisesInstructionsList().remove(index);
                saveToFile();
                setupRecyclerView();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    /**
     * Temporary button just to show temporary way of saving and loading an exercise - BNI
     * /
     *
     * @param
     */

    public void launchCreateCustomExercise(View v) {
        Intent intent = new Intent(this, CreateCustomExerciseActivity.class);
        startActivity(intent);
    }
}