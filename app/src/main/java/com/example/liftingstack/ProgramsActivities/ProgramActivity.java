package com.example.liftingstack.ProgramsActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.AllPrograms;
import com.example.liftingstack.Entity.Program;
import com.example.liftingstack.ExerciseActivities.ExerciseActivity;
import com.example.liftingstack.ProgramsActivities.SelectedProgram.SelectedProgramActivity;
import com.example.liftingstack.R;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Program activity.
 * This activity is used to display all programs.
 */
public class ProgramActivity extends AppCompatActivity implements ProgramRecyclerViewInterface {
    private List<Program> programs = new ArrayList<>();
    private RecyclerView recyclerView;
    private AllPrograms allPrograms;

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> setupRecyclerView()
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        recyclerView = findViewById(R.id.listPrograms);
        setupRecyclerView();
    }

    /**
     * Sets recycler view.
     */
    public void setupRecyclerView() {
        // Create a new AllExerciseInstructions which will load all exercises from file
        allPrograms = new AllPrograms(this);

        // Create a new ExerciseRecyclerViewAdapter which will display all exercises
        ProgramRecyclerViewAdapter programRecyclerViewAdapter = new ProgramRecyclerViewAdapter(
                this, this, allPrograms);
        recyclerView.setAdapter(programRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * This method is used to add a new program, it will launch the SelectedProgramActivity
     *
     * @param view the view
     */
    public void addProgram(View view) {
        Intent intent = new Intent(this, SelectedProgramActivity.class);
        activityResultLauncher.launch(intent);
    }


    /**
     * This method is used to edit a program, it will launch the SelectedProgramActivity
     *
     * @param program the program
     */
    @Override
    public void onItemClick(Program program) {

        Intent intent = new Intent(this, SelectedProgramActivity.class);
        intent.putExtra("ProgramID", program.getId());

        activityResultLauncher.launch(intent);
    }

    /**
     * This method is used to delete a program
     *
     * @param program the program
     */
    @Override
    public void onProgramDelete(int program) {
        allPrograms.getProgramsList().remove(program);
        allPrograms.saveProgramList(this);
    }
}