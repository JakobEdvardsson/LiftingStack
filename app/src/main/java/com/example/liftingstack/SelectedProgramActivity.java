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
    private TextView programDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_program);

        Program program = getIntent().getParcelableExtra("KEY_SENDER");
        programName = (TextView) findViewById(R.id.selectedProgramText);
        programDescription = (TextView) findViewById(R.id.selectedProgramDescription);


        programName.setText(program.getName());
        programDescription.setText(program.getDescription());
    }
}