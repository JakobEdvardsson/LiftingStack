package com.example.liftingstack.ExerciseActivities;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.R;

public class ExerciseRecyclerViewAdapter extends RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder> {
    private final ExerciseRecyclerViewInterface exerciseRecyclerViewInterface;
    private Context context;
    private AllExerciseInstructions allExerciseInstructions;


    public ExerciseRecyclerViewAdapter(
            Context context,
            ExerciseRecyclerViewInterface exerciseRecyclerViewInterface,
            AllExerciseInstructions allExerciseInstructions) {
        this.context = context;

        this.exerciseRecyclerViewInterface = exerciseRecyclerViewInterface;
        this.allExerciseInstructions = allExerciseInstructions;
    }

    @NonNull
    @Override
    public ExerciseRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_exercises, parent, false);
        return new ExerciseRecyclerViewAdapter.ViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.programNameTextView.setText(allExerciseInstructions.getExercisesInstructionsList().get(position).getExerciseName());
        holder.programDescriptionTextView.setText(allExerciseInstructions.getExercisesInstructionsList().get(position).getExerciseDescription());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseRecyclerViewInterface.onExerciseClick(allExerciseInstructions.getExercisesInstructionsList().get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return allExerciseInstructions.getExercisesInstructionsList().size();
    }
    //Denna klass var static innan, krånglar den så får vi göra den static igen.
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView programNameTextView;
        private TextView programDescriptionTextView;
        private ImageView imageView;
        private CardView cardView;
        private ExerciseRecyclerViewAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            programNameTextView = itemView.findViewById(R.id.exerciseNameSelectedProgram);
            programDescriptionTextView = itemView.findViewById(R.id.programDescription);
            cardView = itemView.findViewById(R.id.cardViewExercise);

            itemView.findViewById(R.id.cardViewDeleteIcon).setOnClickListener(view -> {
                try {
                    exerciseRecyclerViewInterface.removeExerciseAndUpdateList(getAdapterPosition());
                    adapter.notifyItemRemoved(getAdapterPosition());

                } catch (Exception e) {
                    System.out.println(e);
                }
            });
        }

        public ViewHolder linkAdapter(ExerciseRecyclerViewAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }
}
