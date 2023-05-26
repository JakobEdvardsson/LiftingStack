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
import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.Entity.Program;
import com.example.liftingstack.ExerciseActivities.ExerciseInstructionsPage;
import com.example.liftingstack.ExerciseActivities.ExerciseRecyclerViewInterface;
import com.example.liftingstack.ProgramsActivities.StartedPrograms.StartedProgramActivity;
import com.example.liftingstack.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to display the selected program.
 * It also allows the user to edit the program and start the program.
 */
public class SelectedProgramActivity extends AppCompatActivity implements ExerciseRecyclerViewInterface {
    private RecyclerView recyclerView;
    private AllPrograms allPrograms;
    private String idForProgram;
    private Program selectedProgram;
    private List<ExerciseInstruction> exercises; //All exercises in the selected program


    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> setupRecyclerView()
    );

    /**
     * On create method.
     * The purpose of this method is to create the activity and set the content view.
     * It also gets the selected program and displays its content.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_program);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        recyclerView = findViewById(R.id.selectedProgramRecyclerView);

        //Get all programs from file
        allPrograms = new AllPrograms(this);
        //Gets the id for the program that was selected
        idForProgram = getIntent().getStringExtra("ProgramID");

        //Gets the selected program from allPrograms
        getSelectedProgram();



        //Sets up the recycler view
        //And gets all exercises from the selected program
        setupRecyclerView();

        EditText programName = findViewById(R.id.selectedProgramName);
        programName.setText(selectedProgram.getName());

        EditText programDescription = findViewById(R.id.selectedProgramDescription);
        programDescription.setText(selectedProgram.getDescription());
    }

    /**
     * Get all exercises for program.
     * This method gets all exercises for the selected program.
     * It also removes exercises that are no longer in the list.
     */
    public void getAllExercisesForProgram() {
        exercises = new ArrayList<>();
        AllExerciseInstructions allExerciseInstructions = new AllExerciseInstructions(this);
        for (String id : selectedProgram.getExercises()) {
            //Used to remove exercises that are no longer in the list
            boolean exerciseFound = false;
            for (ExerciseInstruction exerciseInstruction : allExerciseInstructions.getExercisesInstructionsList()) {
                if (exerciseInstruction.getId().equals(id)) {
                    exercises.add(exerciseInstruction);
                    exerciseFound = true;
                }
            }
            if (!exerciseFound) {
                //Remove exercise from program if the exercise has been removed.
                selectedProgram.getExercises().remove(id);
            }
        }
    }

    /**
     * Get selected program.
     * This method gets the selected program from allPrograms.
     */
    public void getSelectedProgram() {
        boolean programFound = false;

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

    }

    /**
     * Sets up recycler view.
     */
    public void setupRecyclerView() {
        //Gets all exercises from the selected program
        getAllExercisesForProgram();
        
        SelectedProgramRecyclerViewAdapter exerciseAdapter = new SelectedProgramRecyclerViewAdapter(this, exercises, this);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Saves the program with all of it content to file.
     * @param v the v
     */
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

    //TODO: Remove this method
    public void startBtnClicked(View v) {
        Intent intent = new Intent(this, StartedProgramActivity.class);
        intent.putExtra("ProgramId", selectedProgram.getId());
        activityResultLauncher.launch(intent);
    }


    @Override
    public void onExerciseClick(ExerciseInstruction exerciseInstruction) {

        Intent intent = new Intent(this, ExerciseInstructionsPage.class);
        intent.putExtra("ExerciseID", exerciseInstruction.getId());
        activityResultLauncher.launch(intent);
    }

    /**
     * Creates a popup menu with all exercises that are not already in the program.
     * When an exercise is selected it is added to the program.
     * @param v the v
     */
    public void addExerciseOnClick(View v) {
        // Get the menu view from the layout
        PopupMenu popupMenu = new PopupMenu(this, v);
        // Add a menu item for each option
        AllExerciseInstructions allExerciseInstructions = new AllExerciseInstructions(this);

        // Show only exercises that are not already in the program
        for (ExerciseInstruction option : allExerciseInstructions.getExercisesInstructionsList()) {
            if (!selectedProgram.getExercises().contains(option.getId())) {
                popupMenu.getMenu().add(option.getExerciseName());
            }

        }
        // Listener for the menu item clicks, will add the exercise to the exercises and selectedProgram on click
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle the menu item click
                for (ExerciseInstruction exerciseInstruction : allExerciseInstructions.getExercisesInstructionsList()) {
                    if (exerciseInstruction.getExerciseName().equals(item.getTitle().toString())) {
                        selectedProgram.addExercise(exerciseInstruction.getId());
                        exercises.add(exerciseInstruction);
                        setupRecyclerView();
                        break;
                    }
                }
                return true;
            }
        });
        // Show the menu
        popupMenu.show();
    }


    /**
     * Removes the exercise from the program and updates the recycler view.
     * @param index the index
     */
    @Override
    public void removeExerciseAndUpdateList(int index) {
        selectedProgram.getExercises().remove(index);
        exercises.remove(index);
    }
}