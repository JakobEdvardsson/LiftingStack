package com.example.liftingstack.ProgramsActivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liftingstack.Entity.Program;
import com.example.liftingstack.MainActivity;
import com.example.liftingstack.R;

import java.util.ArrayList;
import java.util.List;

public class ProgramActivity extends AppCompatActivity implements ProgramRecyclerViewInterface
{
    private List<Program> programs = new ArrayList<>();

    private ProgramRecyclerViewAdapter programAdapter;

    private ImageView editView;
    private ImageView confirmView;
    private Button backButton;
    private TextView addDescription;
    private ImageView undoView;

    private Program selectedProgram = null;
    private Program programToAdd = new Program("Rygg", "Tungt");

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText popupProgramName;
    private EditText popupProgramDescription;
    private Button saveButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        RecyclerView recyclerView = findViewById(R.id.listPrograms);
        setUpProgramsList();
        programAdapter = new ProgramRecyclerViewAdapter(this, programs, this);
        recyclerView.setAdapter(programAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.addIcon).setOnClickListener(view ->
        {
            programs.add(programToAdd);
            programAdapter.notifyItemInserted(programs.size() - 1);
        });
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
                intent.putExtra("KEY_SENDER", selectedProgram);
                startActivity(intent);
            }
        });

        undoView = findViewById(R.id.cancelImageView);
        undoView.setVisibility(View.VISIBLE);

        undoView.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View view)
            {
                backButton.setVisibility(View.VISIBLE);
                addDescription.setVisibility(View.VISIBLE);

                editView.setVisibility(View.INVISIBLE);
                confirmView.setVisibility(View.INVISIBLE);
                undoView.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void makeInvisible(View view)
    {
        backButton = findViewById(R.id.backButton);
        backButton.setVisibility(View.INVISIBLE);

        addDescription = findViewById(R.id.addDescriptionText);
        addDescription.setVisibility(View.INVISIBLE);
    }

    public void editProgramPopup()
    {
        dialogBuilder = new AlertDialog.Builder(this);
        final View editPopup = getLayoutInflater().inflate(R.layout.program_edit_popup, null);
    }

    @Override
    public void onItemClick(Program program)
    {
        this.selectedProgram = program;
        System.out.println(program.getName());
    }
}