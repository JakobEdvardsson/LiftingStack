package com.example.liftingstack.ExerciseActivities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Controller.SaveToDevice;
import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.Entity.AllPrograms;
import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.Entity.Program;
import com.example.liftingstack.MainActivity;
import com.example.liftingstack.R;

import java.util.ArrayList;

/**
 * This class is used to display all exercises.
 * It also allows the user to create, edit and delete exercises.
 */
public class ExerciseActivity extends AppCompatActivity implements ExerciseRecyclerViewInterface {

    private AllExerciseInstructions allExerciseInstructions;
    private RecyclerView recyclerView;
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> setupRecyclerView()
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        // Setup RecyclerView and creates allExerciseInstructions object
        setupRecyclerView();

    }

    /**
     * Saves AllExerciseInstructions to file.
     */
    private void saveToFile() {
        allExerciseInstructions.saveExercisesInstructionsList(this);
    }

    /**
     * Sets recycler view.
     */
    public void setupRecyclerView() {
        recyclerView = findViewById(R.id.listExercise);
        // Create a new AllExerciseInstructions which will load all exercises from file
        allExerciseInstructions = new AllExerciseInstructions(this);

        // Create a new ExerciseRecyclerViewAdapter which will display all exercises
        ExerciseRecyclerViewAdapter exerciseAdapter = new ExerciseRecyclerViewAdapter(
                this, this, allExerciseInstructions);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    /**
     * New exercise.
     *
     * @param v the v
     */
    public void newExercise(View v) {
        Intent intent = new Intent(this, ExerciseInstructionsPage.class);
        activityResultLauncher.launch(intent);

    }

    /**
     * Go back.
     *
     * @param v the v
     */
    public void goBack(View v) { //TODO remove this method
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * On exercise click.
     * Passes the exercise id to the ExerciseInstructionsPage activity.
     *
     * @param exerciseInstruction the exercise instructions
     */
    @Override
    public void onExerciseClick(ExerciseInstruction exerciseInstruction) {
        Intent intent = new Intent(this, ExerciseInstructionsPage.class);
        intent.putExtra("ExerciseID", exerciseInstruction.getId());

        activityResultLauncher.launch(intent);
    }

    /**
     * Remove exercise and update list.
     * Creates a custom dialog to confirm if the user wants to delete the exercise.
     *
     * @param index the index
     */
    @Override
    public void removeExerciseAndUpdateList(int index) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_pop_up_text);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.pop_up_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.pop_up_animation;

        Button delete = dialog.findViewById(R.id.btn_delete);
        Button cancel = dialog.findViewById(R.id.btn_cancel);
        dialog.show();

        // If delete button is clicked, delete the exercise and close the custom dialog
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.dismiss();


                // Tar bort övningen från alla program den är med i och sparar programmen utan övningen
                String exerciseIDToRemove = allExerciseInstructions.getExercisesInstructionsList().get(index).getId();
                AllPrograms allPrograms = new AllPrograms(ExerciseActivity.this);
                ArrayList<Program> programsList = allPrograms.getProgramsList();

                for (int i = 0; i < programsList.size(); i++) {
                    programsList.get(i).removeExercise(exerciseIDToRemove);
                }

                new SaveToDevice().saveListToDevice(programsList, ExerciseActivity.this, "programList");

                // Removes the exercise
                allExerciseInstructions.getExercisesInstructionsList().remove(index);

                Toast.makeText(ExerciseActivity.this, "Exercise and data deleted", Toast.LENGTH_SHORT).show();

                saveToFile();
                setupRecyclerView();
            }
        });

        // If cancel button is clicked, close the custom dialog
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ExerciseActivity.this, "Exercise saved", Toast.LENGTH_SHORT).show();
                setupRecyclerView();
                dialog.dismiss();
            }
        });

    }

}