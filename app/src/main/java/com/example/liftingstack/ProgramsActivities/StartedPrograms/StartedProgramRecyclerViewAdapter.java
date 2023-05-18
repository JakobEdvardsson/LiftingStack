package com.example.liftingstack.ProgramsActivities.StartedPrograms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.ExerciseHistoryDataMap;
import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.ExerciseHistoryMap;
import com.example.liftingstack.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StartedProgramRecyclerViewAdapter extends RecyclerView.Adapter<StartedProgramRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<ExerciseInstruction> exercises = new ArrayList<>();
    //private final ExerciseRecyclerViewInterface exerciseRecyclerViewInterface;

    public StartedProgramRecyclerViewAdapter(Context context, List<ExerciseInstruction> exercises) {
        this.context = context;
        this.exercises = exercises;
        //this.exerciseRecyclerViewInterface = exerciseRecyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_started_program, parent, false);

        return new ViewHolder(view).linkAdapter(this);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TableLayout tableLayout;
        //private Button saveButton;
        private StartedProgramRecyclerViewAdapter adapter;
        private TextView exerciseNameTextView;
        private EditText setsEditText, repsEditText, weightEditText;
        private int setCounter;
        private String exerciseIdToSend;

        private ExerciseHistoryMap exerciseHistoryMap = new ExerciseHistoryMap();
        private Map<String, ArrayList<String>> setDataMap = new HashMap<>();
        private HashMap<String, ArrayList<EditText>> editTextsMap = new HashMap<>();
        private ArrayList<EditText> repsAndWeight;


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
                    weightEditText = newRow.findViewById(R.id.weightEditText);

                    //LÄGG TILL REPS + WEIGHT I ARRAYLIST, SEDAN SETS + ARRAYLISTAN I HASHMAPPEN
                    repsAndWeight.add(repsEditText);
                    repsAndWeight.add(weightEditText);
                    editTextsMap.put(setsEditText.getText().toString(), repsAndWeight);
                    
                    //LÄGG TILL NYA TABLEROW I TABLELAYOUTEN
                    tableLayout.addView(newRow);

                    int tableHeight = 300;

                    //RÄKNA UT TABELLENS HÖJD BASERAT PÅ ANTAL RADER
                    for (int i = 0; i < tableLayout.getChildCount(); i++) {
                        View row = tableLayout.getChildAt(i);
                        tableHeight += row.getHeight();
                    }

                    //SÄTT DEN NYA TABELLHÖJDEN
                    tableLayout.getLayoutParams().height = tableHeight;

                    //RÄKNA UT HUR HÖG CARDVIEWEN SKA VARA
                    int cardViewHeight = tableHeight;

                    //SÄTT HÖJDEN PÅ CARDVIEWEN
                    try {
                        cardView.getLayoutParams().height = cardViewHeight;
                        cardView.requestLayout();
                    } catch (Exception e) {
                        System.out.println("Tried adding too fast " + e);
                    }
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

                        int rowHeight = 140;
                        //JUSTERA CARDVIEW EFTER ATT HA TAGIT BORT TABLEROW
                        try {
                            cardView.getLayoutParams().height = cardView.getHeight() - rowHeight;
                            cardView.requestLayout();
                        } catch (Exception e) {
                            System.out.println("Tried deleting too fast " + e);
                        }
                    }
                }
            });

            itemView.findViewById(R.id.saveExerciserRepsWeighBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // HÄMTA UT ID FRÅN ÖVNINGEN SOM SPARA-KNAPPEN TRYCKTES PÅ
                    ExerciseInstruction exerciseInstruction = adapter.exercises.get(getAdapterPosition());
                    exerciseIdToSend = exerciseInstruction.getId();

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
                    new ExerciseHistoryDataMap((AppCompatActivity) context).
                            saveExerciseHistoryMap((AppCompatActivity) context, exerciseIdToSend, setDataMap);
                    Toast.makeText(context, "Exercise saved", Toast.LENGTH_SHORT).show();

                }
            });
        }

        public ViewHolder linkAdapter(StartedProgramRecyclerViewAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }
}
