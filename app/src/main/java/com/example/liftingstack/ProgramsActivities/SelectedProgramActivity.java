package com.example.liftingstack.ProgramsActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liftingstack.Entity.Program;
import com.example.liftingstack.R;

public class SelectedProgramActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_program);

        Program program = getIntent().getParcelableExtra("Program");
        EditText programName = findViewById(R.id.selectedProgramName);
        programName.setText(program.getName());

        EditText programDescription = findViewById(R.id.selectedProgramDescription);
        programDescription.setText(program.getDescription());
    }

    public void saveOnClick(View v) {

        EditText programName = findViewById(R.id.selectedProgramName);
        EditText programDescription = findViewById(R.id.selectedProgramDescription);
        Toast.makeText(getApplicationContext(), programName.getText(), Toast.LENGTH_SHORT).show();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("programName", programName.getText().toString());
        resultIntent.putExtra("programDescription", programDescription.getText().toString());

        setResult(78, resultIntent);
        finish();
    }
}