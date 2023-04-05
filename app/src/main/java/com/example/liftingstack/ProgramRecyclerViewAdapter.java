package com.example.liftingstack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.liftingstack.Entity.Program;
import java.util.ArrayList;
import java.util.List;

public class ProgramRecyclerViewAdapter extends RecyclerView.Adapter<ProgramRecyclerViewAdapter.ViewHolder>
{
    private Context context;
    private List<Program> programs = new ArrayList<>();

    public ProgramRecyclerViewAdapter(Context context, List<Program> programs)
    {
        this.context = context;
        this.programs = programs;
    }

    @NonNull
    @Override
    public ProgramRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_programs, parent, false);
        return new ProgramRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramRecyclerViewAdapter.ViewHolder holder, int position)
    {
        holder.programNameTextView.setText(programs.get(position).getName());
        holder.programDescriptionTextView.setText(programs.get(position).getDescription());
    }

    @Override
    public int getItemCount()
    {
        return programs.size();
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
