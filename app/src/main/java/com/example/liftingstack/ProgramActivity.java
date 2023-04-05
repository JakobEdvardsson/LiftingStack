package com.example.liftingstack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;


import android.os.Bundle;

import com.example.liftingstack.Entity.Program;

import java.util.ArrayList;
import java.util.List;

public class ProgramActivity extends AppCompatActivity
{
    private List<Program> programs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        RecyclerView recyclerView = findViewById(R.id.listPrograms);
        setUpProgramsList();
        ProgramRecyclerViewAdapter programAdapter = new ProgramRecyclerViewAdapter(this, programs);
        recyclerView.setAdapter(programAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void goBack(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setUpProgramsList()
    {
        for (int i = 1; i <= 20; i++)
        {
            programs.add(new Program("Program " + i , "Back and biceps"));
        }
    }
}