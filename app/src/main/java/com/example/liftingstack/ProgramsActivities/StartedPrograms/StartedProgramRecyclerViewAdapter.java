package com.example.liftingstack.ProgramsActivities.StartedPrograms;

import android.annotation.SuppressLint;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.R;

import java.util.ArrayList;
import java.util.List;

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
    public void onBindViewHolder(@NonNull StartedProgramRecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.exerciseNameTextView.setText(exercises.get(position).getExerciseName());

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView exerciseNameTextView;
        private CardView cardView;
        private ConstraintLayout constraintLayout;
        private TableLayout tableLayout;
        private TableRow tableRow;
        private EditText setsText, repsText, weightText;
        private StartedProgramRecyclerViewAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseNameTextView = itemView.findViewById(R.id.startedProgramExerciseName);
            cardView = itemView.findViewById(R.id.startedProgramCardView);
            tableLayout = itemView.findViewById(R.id.startedProgramTable);
            tableRow = itemView.findViewById(R.id.tableRowToExpand);
            constraintLayout = itemView.findViewById(R.id.constraintLayoutTable);
            setsText = itemView.findViewById(R.id.setsEditText);
            repsText = itemView.findViewById(R.id.repsEditText);
            weightText = itemView.findViewById(R.id.weightEditText);

            itemView.findViewById(R.id.addRowBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
                    layoutParams.height += 200;
                    cardView.setLayoutParams(layoutParams);

                    if(tableRow.getParent() != null) {
                        ((ViewGroup)tableRow.getParent()).removeView(tableRow); // <- fix
                    }






                    cardView.addView(tableRow);
                }
            });
        }


        public ViewHolder linkAdapter(StartedProgramRecyclerViewAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }
}
