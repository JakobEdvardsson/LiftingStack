package com.example.liftingstack.ProgramsActivities.StartedPrograms;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liftingstack.Controller.LoadFromDevice;
import com.example.liftingstack.Controller.SaveToDevice;
import com.example.liftingstack.Entity.ExerciseHistoryDataMap;
import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.Entity.AllPrograms;
import com.example.liftingstack.Entity.Program;
import com.example.liftingstack.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StartedProgramActivity extends AppCompatActivity {

    private List<ExerciseInstruction> exercises = new ArrayList<>();
    private RecyclerView recyclerView;
    private Program selectedProgram;
    private AllPrograms allPrograms;
    private String idForProgram;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> setupRecyclerView()
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_program);

        idForProgram = getIntent().getStringExtra("ProgramId");
        allPrograms = new AllPrograms(this);
        AllExerciseInstructions allExerciseInstructions = new AllExerciseInstructions(this);

        for (Program program : allPrograms.getProgramsList()) {
            if (program.getId().equals(idForProgram)) {
                selectedProgram = program;
            }
        }

        for (String id : selectedProgram.getExercises()) {
            for (ExerciseInstruction exerciseInstruction : allExerciseInstructions.getExercisesInstructionsList()) {
                if (exerciseInstruction.getId().equals(id)) {
                    exercises.add(exerciseInstruction);
                    System.out.println("Exercise number id = " + exerciseInstruction.getId());
                }
            }
        }

        recyclerView = findViewById(R.id.startedProgramRecyclerView);
        setupRecyclerView();

        TextView programName = findViewById(R.id.startedProgramName);
        programName.setText(selectedProgram.getName());
    }

    public void setupRecyclerView() {
        StartedProgramRecyclerViewAdapter startedProgramRecyclerViewAdapter = new StartedProgramRecyclerViewAdapter(this, exercises);
        recyclerView.setAdapter(startedProgramRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void finishProgramAndSave(View v) {
        StartedProgramRecyclerViewAdapter startedProgramRecyclerViewAdapter = (StartedProgramRecyclerViewAdapter) recyclerView.getAdapter();
        assert startedProgramRecyclerViewAdapter != null;


        for (int i = 0; i < ((StartedProgramRecyclerViewAdapter) recyclerView.getAdapter()).getExercises().size(); i++) {
            StartedProgramRecyclerViewAdapter.ViewHolder test = (StartedProgramRecyclerViewAdapter.ViewHolder) recyclerView.findViewHolderForAdapterPosition(i);


            assert test != null;
            Map<String, ArrayList<EditText>> editTextsMap = test.getEditTextsMap();
            Map<String, ArrayList<String>> setDataMap = test.getSetDataMap();


            String exerciseIdToSend = ((StartedProgramRecyclerViewAdapter) recyclerView.getAdapter()).getExercises().get(i).getId();

            //GÅ IGENOM EDITTEXTSMAP'S LISTA OCH HÄMTA UT SET, REPS, WEIGHT
            for (Map.Entry<String, ArrayList<EditText>> entry : editTextsMap.entrySet()) {
                String set = entry.getKey();
                ArrayList<EditText> value = entry.getValue();
                int index = 0;

                String reps = value.get(index).getText().toString();
                String weight = value.get(index + 1).getText().toString();

                //SKAPA NY ARRAYLIST FÖR REPS + WEIGHT
                ArrayList<String> repsAndWeight = new ArrayList<>();
                repsAndWeight.add(reps);
                repsAndWeight.add(weight);
                //LÄGG TILL SET + REPS + WEIGHT I HASHMAPPEN
                setDataMap.put(set, repsAndWeight);
            }

            //SKICKA HASHMAPPEN TILL SPARNINGS-FUNKTIONER
            new ExerciseHistoryDataMap(this).
                    saveExerciseHistoryMap(this, exerciseIdToSend, setDataMap);
            Toast.makeText(this, "Program saved", Toast.LENGTH_SHORT).show();



            finish();
        }
            // Logga programmet på dagens datum i filen datesLogged
            LocalDate dateObject = LocalDate.now();

            String dateString = dateObject.toString();

            ArrayList<Integer> datesLogged = new LoadFromDevice().loadDatesLoggedFromDevice(this, "datesLogged");
            Integer dateInteger = Integer.valueOf(dateString.replace("-", ""));
            datesLogged.add(dateInteger);

            new SaveToDevice().saveListToDevice(datesLogged, this, "datesLogged");
            Log.i( "dateslogged: ", datesLogged.toString());
    }
}
