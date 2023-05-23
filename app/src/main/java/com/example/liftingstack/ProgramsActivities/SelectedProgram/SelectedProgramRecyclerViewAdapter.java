package com.example.liftingstack.ProgramsActivities.SelectedProgram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Controller.ImageHandler;
import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.ExerciseActivities.ExerciseRecyclerViewInterface;
import com.example.liftingstack.R;

import java.util.List;

public class SelectedProgramRecyclerViewAdapter extends RecyclerView.Adapter<SelectedProgramRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<ExerciseInstruction> exercises;
    private final ExerciseRecyclerViewInterface exerciseRecyclerViewInterface;

    public SelectedProgramRecyclerViewAdapter(Context context, List<ExerciseInstruction> exercises, ExerciseRecyclerViewInterface exerciseRecyclerViewInterface) {
        this.context = context;
        this.exercises = exercises;
        this.exerciseRecyclerViewInterface = exerciseRecyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_selected_program, parent, false);
        return new ViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedProgramRecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.exerciseNameTextView.setText(exercises.get(position).getExerciseName());
        //holder.exerciseDescriptionTextView.setText(exercises.get(position).getExerciseDescription());
        ImageHandler imageHandler = new ImageHandler();
        holder.exerciseImage.setImageBitmap(imageHandler.convertBase64ToBitmap(exercises.get(position).getImage()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseRecyclerViewInterface.onExerciseClick(exercises.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView exerciseNameTextView;
        private TextView exerciseDescriptionTextView;
        private ImageView exerciseImage;
        private CardView cardView;
        private SelectedProgramRecyclerViewAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseNameTextView = itemView.findViewById(R.id.exerciseNameSelectedProgram);
            //exerciseDescriptionTextView = itemView.findViewById(R.id.exerciseDescriptionSelectedProgram);
            cardView = itemView.findViewById(R.id.selectedProgramCardView);
            exerciseImage = itemView.findViewById(R.id.workoutImage);

            itemView.findViewById(R.id.deleteIconSelectedProgram).setOnClickListener(view -> {
                try {

                    exerciseRecyclerViewInterface.removeExerciseAndUpdateList(getAdapterPosition());
                    adapter.notifyItemRemoved(getAdapterPosition());
                } catch (Exception e) {
                    System.out.println("Tried deleting too fast" + e);
                }
            });
        }

        public ViewHolder linkAdapter(SelectedProgramRecyclerViewAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }
}
