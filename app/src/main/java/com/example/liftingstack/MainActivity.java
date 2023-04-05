package com.example.liftingstack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.ExerciseInstructions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    public void launchPrograms(View v){
        Intent intent = new Intent(this, ProgramActivity.class);
        startActivity(intent);
    }

    public void launchExercises(View v){
        Intent intent = new Intent(this, ExerciseActivity.class);
        startActivity(intent);
    }
}