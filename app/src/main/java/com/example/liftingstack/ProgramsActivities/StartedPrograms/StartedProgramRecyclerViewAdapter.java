package com.example.liftingstack.ProgramsActivities.StartedPrograms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StartedProgramRecyclerViewAdapter extends RecyclerView.Adapter<StartedProgramRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<ExerciseInstruction> exercises;
    private ViewHolder viewHolder;

    public StartedProgramRecyclerViewAdapter(Context context, List<ExerciseInstruction> exercises) {
        this.context = context;
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_started_program, parent, false);
        viewHolder = new ViewHolder(view).linkAdapter(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StartedProgramRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.exerciseNameTextView.setText(exercises.get(position).getExerciseName());
        System.out.println(position);
        System.out.println(exercises.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public List<ExerciseInstruction> getExercises() {
        return exercises;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TableLayout tableLayout;
        private StartedProgramRecyclerViewAdapter adapter;
        public TextView exerciseNameTextView;
        private EditText setsEditText, repsEditText, weightEditText;
        private int setCounter;

        private Map<String, ArrayList<String>> setDataMap = new HashMap<>();
        private HashMap<String, ArrayList<EditText>> editTextsMap = new HashMap<>();
        private ArrayList<EditText> repsAndWeight;

        public HashMap<String, ArrayList<EditText>> getEditTextsMap() {
            return editTextsMap;
        }

        public Map<String, ArrayList<String>> getSetDataMap() {
            return setDataMap;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.startedProgramCardView);
            tableLayout = itemView.findViewById(R.id.startedProgramTable);
            exerciseNameTextView = itemView.findViewById(R.id.startedProgramExerciseName);


            itemView.findViewById(R.id.addRowBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    //NY TABLEROW
                    TableRow newRow = (TableRow) layoutInflater.inflate(R.layout.table_row_to_expand, null);
                    
                    //SKAPA NY ARRAYLIST VARJE GÅNG FÖR ATT FYLLA I REPS + WEIGHT FÖR JUST DENNA TABLEROW
                    repsAndWeight = new ArrayList<>();

                    setCounter = tableLayout.getChildCount();
                    setsEditText = newRow.findViewById(R.id.setsEditText);
                    setsEditText.setText(String.valueOf(setCounter));

                    repsEditText = newRow.findViewById(R.id.repsEditText);
                    repsEditText.requestFocus();
                    weightEditText = newRow.findViewById(R.id.weightEditText);

                    //LÄGG TILL REPS + WEIGHT I ARRAYLIST, SEDAN SETS + ARRAYLISTAN I HASHMAPPEN
                    repsAndWeight.add(repsEditText);
                    repsAndWeight.add(weightEditText);
                    editTextsMap.put(setsEditText.getText().toString(), repsAndWeight);

                    //LÄGG TILL NYA TABLEROW I TABLELAYOUTEN
                    tableLayout.addView(newRow);

                }
            });

            itemView.findViewById(R.id.removeRowBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TA ENDAST BORT SENASTE RADEN OM TABLELAYOUT HAR FLER ÄN 1 TABLEROW
                    if (tableLayout.getChildCount() > 1) {
                        tableLayout.removeViewAt(tableLayout.getChildCount() - 1);

                        //KOD FÖR ATT HITTA SISTA TILLAGDA ENTRY I HASHMAPPEN
                        Iterator<Map.Entry<String, ArrayList<EditText>>> iterator = editTextsMap.entrySet().iterator();
                        Map.Entry<String, ArrayList<EditText>> lastEntry = null;

                        //GÅ IGENOM ALLA ENTRYS TILLS MAN KOMMER TILL SISTA ENTRYN
                        while (iterator.hasNext()) {
                            lastEntry = iterator.next();
                        }

                        //TA BORT SISTA ENTRYN
                        if (lastEntry != null) {
                            iterator.remove();
                        }

                    }
                }
            });
            // Adds a row when the view is created
            itemView.findViewById(R.id.addRowBtn).performClick();
        }

        public ViewHolder linkAdapter(StartedProgramRecyclerViewAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }
}
