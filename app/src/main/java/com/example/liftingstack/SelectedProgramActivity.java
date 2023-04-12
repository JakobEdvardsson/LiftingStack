package com.example.liftingstack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.TextView;

import com.example.liftingstack.Entity.Program;

import java.util.ArrayList;
import java.util.List;

public class SelectedProgramActivity extends AppCompatActivity
{
    private TextView programName;
    private List<Program> programs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_program);

    }

    public void displayProgramName(View view)
    {
        programName = findViewById(R.id.selectedProgramText);
        //programName.setText(programs.get());
    }
}