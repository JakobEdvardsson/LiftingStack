package com.example.liftingstack.ProgramsActivities.StartedPrograms;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.R;

import java.util.ArrayList;
import java.util.List;

public class StartedProgramRecyclerViewAdapter extends RecyclerView.Adapter<StartedProgramRecyclerViewAdapter.ViewHolder> {
    private static Context context;
    private List<ExerciseInstructions> exercises = new ArrayList<>();
    //private final ExerciseRecyclerViewInterface exerciseRecyclerViewInterface;

    public StartedProgramRecyclerViewAdapter(Context context, List<ExerciseInstructions> exercises) {
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
    public void onBindViewHolder(@NonNull StartedProgramRecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.exerciseNameTextView.setText(exercises.get(position).getExerciseName());
        System.out.println(exercises.get(position).getId());

        /*holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseRecyclerViewInterface.onExerciseClick(exercises.get(holder.getAdapterPosition()));
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TableLayout tableLayout;
        private StartedProgramRecyclerViewAdapter adapter;
        private TextView exerciseNameTextView;
        private int setCounter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.startedProgramCardView);
            tableLayout = itemView.findViewById(R.id.startedProgramTable);
            exerciseNameTextView = itemView.findViewById(R.id.startedProgramExerciseName);

            itemView.findViewById(R.id.addRowBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    TableRow newRow = (TableRow) layoutInflater.inflate(R.layout.table_row_to_expand, null);

                    setCounter = tableLayout.getChildCount();
                    EditText setsText = newRow.findViewById(R.id.setsEditText);
                    setsText.setText(String.valueOf(setCounter));

                    tableLayout.addView(newRow);

                    int tableHeight = 300;

                    for (int i = 0; i < tableLayout.getChildCount(); i++) {
                        View row = tableLayout.getChildAt(i);
                        tableHeight += row.getHeight();
                    }

                    // Set the new height for the TableLayout
                    tableLayout.getLayoutParams().height = tableHeight;

                    // Calculate the new height of the CardView
                    int cardViewHeight = tableHeight;

                    // Set the new height for the CardView
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
                    if (tableLayout.getChildCount() > 2) {
                        tableLayout.removeViewAt(tableLayout.getChildCount() - 1);


                        int rowHeight = 140;
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
                    for (int i = 0; i < tableLayout.getChildCount(); i++) {

                    }
                }
            });
        }

        public ViewHolder linkAdapter(StartedProgramRecyclerViewAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }
}
