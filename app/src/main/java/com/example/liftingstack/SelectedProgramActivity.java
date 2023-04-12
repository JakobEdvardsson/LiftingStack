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

public class SelectedProgramActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_program);
    }
}