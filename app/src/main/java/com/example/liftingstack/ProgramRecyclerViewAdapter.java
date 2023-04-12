package com.example.liftingstack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.Program;

import java.util.ArrayList;
import java.util.List;

public class ProgramRecyclerViewAdapter extends RecyclerView.Adapter<ProgramRecyclerViewAdapter.ViewHolder>
{
    private Context context;
    private List<Program> programs = new ArrayList<>();
    private final RecyclerViewInterface recyclerViewInterface;
    private ProgramRecyclerViewAdapter adapter;

    public ProgramRecyclerViewAdapter(Context context, List<Program> programs, RecyclerViewInterface recyclerViewInterface)
    {
        this.context = context;
        this.programs = programs;
        this.recyclerViewInterface = recyclerViewInterface;
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
    public void onBindViewHolder(@NonNull ProgramRecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        holder.programNameTextView.setText(programs.get(position).getName());
        holder.programDescriptionTextView.setText(programs.get(position).getDescription());

        holder.cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                recyclerViewInterface.onItemClick(programs.get(position));

                View cardView = (CardView) view.findViewById(R.id.cardViewProgram);


                View editView = (ImageView) view.findViewById(R.id.imageViewEdit);
                recyclerViewInterface.makeVisible(editView);

                View confirmView = (ImageView) view.findViewById(R.id.imageViewConfirm);
                recyclerViewInterface.makeVisible(confirmView);

                View deleteView = (ImageView) view.findViewById(R.id.imageViewDelete);
                recyclerViewInterface.makeVisible(deleteView);

                View undoView = (ImageView) view.findViewById(R.id.cancelImageView);
                recyclerViewInterface.makeVisible(undoView);

                View backButton = (Button) view.findViewById(R.id.backButton);
                recyclerViewInterface.makeInvisible(backButton);

                View imageViewAdd = (ImageView) view.findViewById(R.id.addIcon);
                recyclerViewInterface.makeInvisible(imageViewAdd);

                View addDescriptionText = (TextView) view.findViewById(R.id.addDescriptionText);
                recyclerViewInterface.makeInvisible(addDescriptionText);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return programs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView programNameTextView;
        private TextView programDescriptionTextView;
        private ImageView imageView;
        private CardView cardView;
        private ProgramRecyclerViewAdapter adapter;
        private ImageView deleteView;


        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            programNameTextView = itemView.findViewById(R.id.programName);
            programDescriptionTextView = itemView.findViewById(R.id.programDescription);
            imageView = itemView.findViewById(R.id.programImage);
            cardView = itemView.findViewById(R.id.cardViewProgram);
        }
    }
}
