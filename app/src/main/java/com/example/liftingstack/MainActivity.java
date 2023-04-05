package com.example.liftingstack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.Exercise;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    private List<Exercise> exercises = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //This code is for the recycler view
        RecyclerView recyclerView = findViewById(R.id.listProgramExercise);
        setUpMainList();
        MainRecyclerViewAdapter adapter = new MainRecyclerViewAdapter(this, exercises);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void launchSettings(View v)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    public void launchProfile(View v)
    {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
    private void setUpMainList()
    {
        for (int i = 1; i <= 20; i++)
        {
            exercises.add(new Exercise("Workout " + i , "Hard exercise"));
        }
    }

    public void launchPrograms(View v)
    {
        Intent intent = new Intent(this, ProgramActivity.class);
        startActivity(intent);
    }


    public void toast(View v)
    {
        Toast.makeText(this, "Toast", Toast.LENGTH_SHORT).show();
    }
}