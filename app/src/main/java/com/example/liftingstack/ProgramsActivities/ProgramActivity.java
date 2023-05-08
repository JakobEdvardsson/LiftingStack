package com.example.liftingstack.ProgramsActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.AllPrograms;
import com.example.liftingstack.Entity.Program;
import com.example.liftingstack.ProgramsActivities.SelectedProgram.SelectedProgramActivity;
import com.example.liftingstack.R;

import java.util.ArrayList;
import java.util.List;

public class ProgramActivity extends AppCompatActivity implements ProgramRecyclerViewInterface {
    private List<Program> programs = new ArrayList<>();
    private RecyclerView recyclerView;
    private AllPrograms allPrograms;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
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

    public void setupRecyclerView() {
        // Create a new AllExerciseInstructions which will load all exercises from file
        allPrograms = new AllPrograms(this);

        // Create a new ExerciseRecyclerViewAdapter which will display all exercises
        ProgramRecyclerViewAdapter programRecyclerViewAdapter = new ProgramRecyclerViewAdapter(
                this, this, allPrograms);
        recyclerView.setAdapter(programRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void addProgram(View view) {
        Intent intent = new Intent(this, SelectedProgramActivity.class);
        activityResultLauncher.launch(intent);
    }


    @Override
    public void onItemClick(Program program) {

        Intent intent = new Intent(this, SelectedProgramActivity.class);
        intent.putExtra("ProgramID", program.getId());

        activityResultLauncher.launch(intent);
    }

    @Override
    public void onProgramDelete(int program) {
        allPrograms.getProgramsList().remove(program);
        allPrograms.saveProgramList(this);
    }
}