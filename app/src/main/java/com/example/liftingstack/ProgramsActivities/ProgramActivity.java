package com.example.liftingstack.ProgramsActivities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;

import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.Entity.Program;
import com.example.liftingstack.ExerciseActivities.ExerciseInstructionsPage;
import com.example.liftingstack.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProgramActivity extends AppCompatActivity implements ProgramRecyclerViewInterface
{
    private List<Program> programs = new ArrayList<>();
    private ProgramRecyclerViewAdapter programAdapter;
    private Program selectedProgram = null;
    private Program programToAdd;
    private RecyclerView recyclerView;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult result)
                {
                    Log.d(TAG, "onActivityResult: ");
                    if (result.getResultCode() == 78)
                    {
                        Intent data = result.getData();
                        if (data != null)
                        {
                            selectedProgram = new Program("", "");
                            String programName = data.getStringExtra("programName");
                            String programDescription = data.getStringExtra("programDescription");
                            selectedProgram.setName(programName);
                            selectedProgram.setDescription(programDescription);

                            Objects.requireNonNull(recyclerView.getAdapter()).notifyItemChanged(programs.indexOf(selectedProgram));

                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        recyclerView = findViewById(R.id.listPrograms);
        setUpProgramsList();
        programAdapter = new ProgramRecyclerViewAdapter(this, programs, this);
        recyclerView.setAdapter(programAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.addIcon).setOnClickListener(view ->
        {
            programToAdd = new Program("", "");
            programs.add(programToAdd);

            Intent intent = new Intent(this, SelectedProgramActivity.class);
            intent.putExtra("Program", programToAdd);
            activityResultLauncher.launch(intent);
            programAdapter.notifyItemInserted(programs.size() - 1);
            recyclerView.scrollToPosition(programs.size() - 1);
        });

        /*findViewById(R.id.addIcon).setOnClickListener(view ->
        {

            currentExerciseInstructions = new ExerciseInstructions("New Exercise", "New Description");
            allExerciseInstructions.addExerciseInstructions(currentExerciseInstructions);

            Intent intent = new Intent(this, ExerciseInstructionsPage.class);
            intent.putExtra("Exercise", currentExerciseInstructions);
            activityResultLauncher.launch(intent);

            exerciseAdapter.notifyItemInserted(allExerciseInstructions.getExercisesInstructionsList().size() - 1);
            recyclerView.scrollToPosition(allExerciseInstructions.getExercisesInstructionsList().size() - 1);
        });*/
    }

    private void setUpProgramsList()
    {
        for (int i = 1; i <= 20; i++)
        {
            programs.add(new Program("Program " + i , "Back and biceps"));
        }
    }

    @Override
    public void onItemClick(Program program)
    {
        selectedProgram = program;
        Intent intent = new Intent(this, SelectedProgramActivity.class);
        intent.putExtra("Program", program);

        activityResultLauncher.launch(intent);
    }
}