package com.example.liftingstack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.Entity.Program;

import java.util.ArrayList;
import java.util.List;

public class ExerciseRecyclerViewAdapter extends RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder>
{
    private Context context;
    private List<ExerciseInstructions> exerciseInstructions = new ArrayList<>();

    public ExerciseRecyclerViewAdapter(Context context, List<ExerciseInstructions> exerciseInstructions)
    {
        this.context = context;
        this.exerciseInstructions = exerciseInstructions;
    }


    @NonNull
    @Override
    public ExerciseRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_programs, parent, false);
        return new ExerciseRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseRecyclerViewAdapter.ViewHolder holder, int position)
    {
        holder.programNameTextView.setText(exerciseInstructions.get(position).getName());
        holder.programDescriptionTextView.setText(exerciseInstructions.get(position).getDescription());
    }

    @Override
    public int getItemCount()
    {
        return exerciseInstructions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView programNameTextView;
        TextView programDescriptionTextView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            programNameTextView = itemView.findViewById(R.id.programName);
            programDescriptionTextView = itemView.findViewById(R.id.programDescription);
            imageView = itemView.findViewById(R.id.programImage);
        }
    }
}
