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

import com.example.liftingstack.Controller.LoadFromDevice;
import com.example.liftingstack.Controller.SaveToDevice;
import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.MainActivity;
import com.example.liftingstack.R;

import java.util.Objects;

public class ExerciseActivity extends AppCompatActivity implements ExerciseRecyclerViewInterface {

    private AllExerciseInstructions allExerciseInstructions;
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
                            ExerciseInstructions exercise = data.getParcelableExtra("exercise");
                            String image = data.getStringExtra("image");
                            exercise.setImage(image);


                            int index = allExerciseInstructions.getExercisesInstructionsList().indexOf(currentExerciseInstructions);
                            allExerciseInstructions.getExercisesInstructionsList().set(index, exercise);
                            //Save the changes to the file
                            saveToFile();
                            setupRecyclerView();


                            Objects.requireNonNull(recyclerView.getAdapter()).notifyItemChanged(
                                    allExerciseInstructions.getExercisesInstructionsList().indexOf(currentExerciseInstructions));

                        }
                    }
                }
            }
    );

    private void saveToFile() {
        new SaveToDevice().saveListToDevice(allExerciseInstructions.getExercisesInstructionsList(), this, "exerciseList");
    }


    public void setupRecyclerView() {
        ExerciseRecyclerViewAdapter exerciseAdapter = new ExerciseRecyclerViewAdapter(
                this, allExerciseInstructions.getExercisesInstructionsList(), this, allExerciseInstructions);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        allExerciseInstructions = new AllExerciseInstructions();

        try {
            allExerciseInstructions.setExercisesInstructionsList(new LoadFromDevice().loadListFromDevice(this, "exerciseList"));
        }catch (Exception e){
            System.out.println("No exercise list found");
            e.printStackTrace();
        }

        // RecyclerView
        recyclerView = findViewById(R.id.listExercise);
        setupRecyclerView();


        //Create a new exercise
        findViewById(R.id.addIcon).setOnClickListener(view -> {
            // Add exercise
            ExerciseInstructions exerciseInstruction = new ExerciseInstructions("Exercise Name", "Exercise Description");
            currentExerciseInstructions = exerciseInstruction;
            // Add exercise to the ArrayList
            allExerciseInstructions.addExerciseInstructions(exerciseInstruction);

            Intent intent = new Intent(this, ExerciseInstructionsPage.class);
            intent.putExtra("Exercise", exerciseInstruction);

            activityResultLauncher.launch(intent);
        });
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onExerciseClick(ExerciseInstructions exerciseInstructions) {
        currentExerciseInstructions = exerciseInstructions;
        Intent intent = new Intent(this, ExerciseInstructionsPage.class);
        intent.putExtra("Exercise", currentExerciseInstructions);
        intent.putExtra("image", currentExerciseInstructions.getImage());

        activityResultLauncher.launch(intent);
    }

    @Override
    public void removeExerciseAndUpdateList(int index) {
        allExerciseInstructions.getExercisesInstructionsList().remove(index);
        saveToFile();
        setupRecyclerView();
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