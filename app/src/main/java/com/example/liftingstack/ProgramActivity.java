package com.example.liftingstack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liftingstack.Entity.Program;

import java.util.ArrayList;
import java.util.List;

public class ProgramActivity extends AppCompatActivity implements RecyclerViewInterface
{
    private List<Program> programs = new ArrayList<>();
    ImageView editView;
    ImageView confirmView;
    ImageView deleteView;
    Button backButton;
    ImageView addIcon;
    TextView addDescription;
    ImageView undoView;
    CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        RecyclerView recyclerView = findViewById(R.id.listPrograms);
        setUpProgramsList();
        ProgramRecyclerViewAdapter programAdapter = new ProgramRecyclerViewAdapter(this, programs, this);
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

    public void makeVisible(View view)
    {
        editView = findViewById(R.id.imageViewEdit);
        editView.setVisibility(View.VISIBLE);

        confirmView = findViewById(R.id.imageViewConfirm);
        confirmView.setVisibility(View.VISIBLE);

        confirmView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ProgramActivity.this, SelectedProgramActivity.class);
                startActivity(intent);
            }
        });

        deleteView = findViewById(R.id.imageViewDelete);
        deleteView.setVisibility(View.VISIBLE);

        undoView = findViewById(R.id.cancelImageView);
        undoView.setVisibility(View.VISIBLE);


        undoView.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View view)
            {
                backButton.setVisibility(View.VISIBLE);
                addIcon.setVisibility(View.VISIBLE);
                addDescription.setVisibility(View.VISIBLE);

                editView.setVisibility(View.INVISIBLE);
                confirmView.setVisibility(View.INVISIBLE);
                deleteView.setVisibility(View.INVISIBLE);
                undoView.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void makeInvisible(View view)
    {
        backButton = findViewById(R.id.backButton);
        backButton.setVisibility(View.INVISIBLE);

        addIcon = findViewById(R.id.addIcon);
        addIcon.setVisibility(View.INVISIBLE);

        addDescription = findViewById(R.id.addDescriptionText);
        addDescription.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClick(Program program)
    {
        //Intent intent = new Intent(this, SelectedProgramActivity.class);
        //startActivity(intent);
        //Toast.makeText(getApplicationContext(),"HejEHjehe",Toast.LENGTH_SHORT).show();

    }
}