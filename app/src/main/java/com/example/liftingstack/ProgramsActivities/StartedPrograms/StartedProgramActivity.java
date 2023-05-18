package com.example.liftingstack.ProgramsActivities.StartedPrograms;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.Entity.AllPrograms;
import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.Entity.Program;
import com.example.liftingstack.MainActivity;
import com.example.liftingstack.R;

import java.util.ArrayList;
import java.util.List;

public class StartedProgramActivity extends AppCompatActivity{

    private List<ExerciseInstruction> exercises = new ArrayList<>();
    private RecyclerView recyclerView;
    private Program selectedProgram;
    private AllPrograms allPrograms;
    private String idForProgram;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> setupRecyclerView()
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_program);

        idForProgram = getIntent().getStringExtra("ProgramId");
        allPrograms = new AllPrograms(this);
        AllExerciseInstructions allExerciseInstructions = new AllExerciseInstructions(this);

        for (Program program : allPrograms.getProgramsList()) {
            if (program.getId().equals(idForProgram)) {
                selectedProgram = program;
            }
        }

        for (String id : selectedProgram.getExercises()) {
            for (ExerciseInstruction exerciseInstruction : allExerciseInstructions.getExercisesInstructionsList()) {
                if (exerciseInstruction.getId().equals(id)) {
                    exercises.add(exerciseInstruction);
                    System.out.println("Exercise number id = " + exerciseInstruction.getId());
                }
            }
        }

        recyclerView = findViewById(R.id.startedProgramRecyclerView);
        setupRecyclerView();

        TextView programName = findViewById(R.id.startedProgramName);
        programName.setText(selectedProgram.getName());
    }

    public void setupRecyclerView() {
        StartedProgramRecyclerViewAdapter startedProgramRecyclerViewAdapter = new StartedProgramRecyclerViewAdapter(this, exercises);
        recyclerView.setAdapter(startedProgramRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void finishProgram(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
