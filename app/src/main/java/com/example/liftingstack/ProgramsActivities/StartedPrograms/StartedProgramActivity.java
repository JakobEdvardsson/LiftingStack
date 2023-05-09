package com.example.liftingstack.ProgramsActivities.StartedPrograms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.ExerciseActivities.ExerciseRecyclerViewInterface;
import com.example.liftingstack.R;

import java.util.ArrayList;
import java.util.List;

public class StartedProgramActivity extends AppCompatActivity{

    private List<ExerciseInstructions> exercises = new ArrayList<>();
    private RecyclerView recyclerView;
    private StartedProgramRecyclerViewAdapter startedProgramRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_program);

        recyclerView = findViewById(R.id.startedProgramRecyclerView);
        setUpExerciseList();
        startedProgramRecyclerViewAdapter = new StartedProgramRecyclerViewAdapter(this, exercises);
        recyclerView.setAdapter(startedProgramRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpExerciseList() {
        for (int i = 1; i <= 20; i++) {
            exercises.add(new ExerciseInstructions("Exercise " + i , "Dips"));
        }
    }
}
