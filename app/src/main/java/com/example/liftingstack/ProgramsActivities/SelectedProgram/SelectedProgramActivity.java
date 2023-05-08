package com.example.liftingstack.ProgramsActivities.SelectedProgram;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.Entity.AllPrograms;
import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.Entity.Program;
import com.example.liftingstack.ExerciseActivities.ExerciseRecyclerViewInterface;
import com.example.liftingstack.R;

import java.util.ArrayList;
import java.util.List;

public class SelectedProgramActivity extends AppCompatActivity implements ExerciseRecyclerViewInterface {
    //private SelectedProgramRecyclerViewAdapter selectedProgramAdapter;
    private RecyclerView recyclerView;
    private AllPrograms allPrograms;
    private String idForProgram;
    private Program selectedProgram;
    List<ExerciseInstructions> exercises;

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> setupRecyclerView()
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_program);

        recyclerView = findViewById(R.id.selectedProgramRecyclerView);

        allPrograms = new AllPrograms(this);
        idForProgram = getIntent().getStringExtra("ProgramID");


        boolean programFound = false;
        //Get the exercise from the list
        for (Program program : allPrograms.getProgramsList()) {
            if (program.getId().equals(idForProgram)) {
                selectedProgram = program;
                programFound = true;
                break;
            }
        }
        if (!programFound) {
            selectedProgram = new Program("New Program", "Description");
        }


        exercises = new ArrayList<>();

        AllExerciseInstructions allExerciseInstructions = new AllExerciseInstructions(this);


        for (String id : selectedProgram.getExercises()) {
            //Used to remove exercises that are no longer in the list
            boolean exerciseFound = false;
            for (ExerciseInstructions exerciseInstructions : allExerciseInstructions.getExercisesInstructionsList()) {
                if (exerciseInstructions.getId().equals(id)) {
                    exercises.add(exerciseInstructions);
                    exerciseFound = true;
                }
            }
            if (!exerciseFound) {
                //Remove exercise from program if the exercise has been removed.
                selectedProgram.getExercises().remove(id);
            }
        }

        setupRecyclerView();


        EditText programName = findViewById(R.id.selectedProgramName);
        programName.setText(selectedProgram.getName());

        EditText programDescription = findViewById(R.id.selectedProgramDescription);
        programDescription.setText(selectedProgram.getDescription());
    }

    public void setupRecyclerView() {
        // Create a new AllExerciseInstructions which will load all exercises from file

        // Create a new ExerciseRecyclerViewAdapter which will display all exercises
        SelectedProgramRecyclerViewAdapter exerciseAdapter = new SelectedProgramRecyclerViewAdapter(this, exercises, this);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void saveOnClick(View v) {

        selectedProgram.setName(((TextView) findViewById(R.id.selectedProgramName)).getText().toString());
        selectedProgram.setDescription(((EditText) findViewById(R.id.selectedProgramDescription)).getText().toString());

        if (!allPrograms.getProgramsList().contains(selectedProgram)) {
            allPrograms.addProgram(selectedProgram);
        } else {
            for (Program program : allPrograms.getProgramsList()) {
                if (program.getId().equals(idForProgram)) {
                    program.setName(selectedProgram.getName());
                    program.setDescription(selectedProgram.getDescription());
                    break;
                }
            }
        }
        allPrograms.saveProgramList(this);

        finish();
    }


    @Override
    public void onExerciseClick(ExerciseInstructions exerciseInstructions) {
        //Do nothing
    }

    public void addExerciseOnClick(View v) {
        // Get the menu view from the layout
        //View menuButton = findViewById(R.id.menu_button);
        PopupMenu popupMenu = new PopupMenu(this, v);
        // Add a menu item for each option
        AllExerciseInstructions allExerciseInstructions = new AllExerciseInstructions(this);

        for (ExerciseInstructions option : allExerciseInstructions.getExercisesInstructionsList()) {
            popupMenu.getMenu().add(option.getExerciseName());
        }
        // Set a listener to handle menu item clicks
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle the menu item click
                for (ExerciseInstructions exerciseInstructions : allExerciseInstructions.getExercisesInstructionsList()) {
                    if (exerciseInstructions.getExerciseName().equals(item.getTitle().toString())) {
                        selectedProgram.addExercise(exerciseInstructions.getId());
                        exercises.add(exerciseInstructions);
                        setupRecyclerView();
                        break;
                    }
                }
                // Do something with the selected option
                return true;
            }
        });
        // Show the menu
        popupMenu.show();


    }

    @Override
    public void removeExerciseAndUpdateList(int index) {
        selectedProgram.getExercises().remove(index);
        exercises.remove(index);
    }
}