package com.example.liftingstack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.Entity.Program;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity {

    private List<ExerciseInstructions> exerciseInstructions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        RecyclerView recyclerView = findViewById(R.id.listExercise);
        setUpExerciseList();
        ExerciseRecyclerViewAdapter exerciseAdapter = new ExerciseRecyclerViewAdapter(this, exerciseInstructions);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setUpExerciseList()
    {
        for (int i = 1; i <= 20; i++)
        {
            exerciseInstructions.add(new ExerciseInstructions("Exercise " + i , "Hard exercise"));
        }
    }
    public void launchCreateCustomExercise(View v){
        Intent intent = new Intent(this, TestReadWrite.class);
        startActivity(intent);
    }

    public void launchTestReadWrite(View v){
        Intent intent = new Intent(this, TestReadWrite.class);
        startActivity(intent);
    }
}