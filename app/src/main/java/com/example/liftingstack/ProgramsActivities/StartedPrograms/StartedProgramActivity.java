package com.example.liftingstack.ProgramsActivities.StartedPrograms;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.liftingstack.ExerciseActivities.ExerciseActivity;
import com.example.liftingstack.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartedProgramActivity extends AppCompatActivity {

    private List<ExerciseInstruction> exercises = new ArrayList<>();
    private RecyclerView recyclerView;
    private Program selectedProgram;
    private AllPrograms allPrograms;
    private String idForProgram;

    private Map<Integer, Integer> loggedEffort;

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
    public static boolean isNumeric(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void finishProgramAndSave(View v) {
        StartedProgramRecyclerViewAdapter startedProgramRecyclerViewAdapter = (StartedProgramRecyclerViewAdapter) recyclerView.getAdapter();
        assert startedProgramRecyclerViewAdapter != null;
        boolean saved = false;
    //todo Kolla så att det är en siffra som användaren har skrivit in.
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

                if (!(reps.equals("") || reps.equals("0")  || reps.equals(" ") || !isNumeric(reps))) {
                    if (weight.equals("") || weight.equals(" ") || weight.equals("0") || weight.equals("0.0") || !isNumeric(weight)) {
                        weight = "0";
                    }
                    ArrayList<String> repsAndWeight = new ArrayList<>();
                    repsAndWeight.add(reps);
                    repsAndWeight.add(weight);
                    //LÄGG TILL SET + REPS + WEIGHT I HASHMAPPEN
                    setDataMap.put(set, repsAndWeight);
                }
                //SKAPA NY ARRAYLIST FÖR REPS + WEIGHT
            }

            //SKICKA HASHMAPPEN TILL SPARNINGS-FUNKTIONER
            if (!setDataMap.isEmpty()) {
                new ExerciseHistoryDataMap(this).saveExerciseHistoryMap(this, exerciseIdToSend, setDataMap);
                saved = true;
            }
        }

        if (saved) {
            showFeelingDialogAndSave();

        } else {
            Toast.makeText(this, "No program saved", Toast.LENGTH_SHORT).show();
        }



    }

    public void showFeelingDialogAndSave() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_pop_up_feeling);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.pop_up_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.pop_up_animation;

        Button delete = dialog.findViewById(R.id.btn_delete);
        Button cancel = dialog.findViewById(R.id.btn_cancel);
        dialog.show();

        // If delete button is clicked, delete the exercise and close the custom dialog
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.dismiss();


                // Logga programmet på dagens datum i filen datesLogged
                LocalDate dateObject = LocalDate.now();

                String dateString = dateObject.toString();

                ArrayList<Integer> datesLogged = new LoadFromDevice().loadDatesLoggedFromDevice(StartedProgramActivity.this, "datesLogged");
                Integer dateInteger = Integer.valueOf(dateString.replace("-", ""));
                datesLogged.add(dateInteger);

                new SaveToDevice().saveListToDevice(datesLogged, StartedProgramActivity.this, "datesLogged");
                Integer effort = 1;
                loggedEffort = new LoadFromDevice().loadEffortFromDevice(StartedProgramActivity.this, "effortLogged");
                loggedEffort.put(dateInteger, effort);
                new SaveToDevice().saveFeelingHashMapToDevice(loggedEffort, StartedProgramActivity.this, "effortLogged");
                Log.i("testFeeling dialog", datesLogged.toString());

                finish();


            }
        });

        // If cancel button is clicked, close the custom dialog
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();


                // Logga programmet på dagens datum i filen datesLogged
                LocalDate dateObject = LocalDate.now();

                String dateString = dateObject.toString();

                ArrayList<Integer> datesLogged = new LoadFromDevice().loadDatesLoggedFromDevice(StartedProgramActivity.this, "datesLogged");
                Integer dateInteger = Integer.valueOf(dateString.replace("-", ""));
                datesLogged.add(dateInteger);

                new SaveToDevice().saveListToDevice(datesLogged, StartedProgramActivity.this, "datesLogged");
                Log.i("test Feeling", datesLogged.toString());
                finish();
            }
        });

    }
}
