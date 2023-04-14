package com.example.liftingstack.ProgramsActivities;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.liftingstack.R;

import java.util.ArrayList;
import java.util.List;

public class ProgramRecyclerViewAdapter extends RecyclerView.Adapter<ProgramRecyclerViewAdapter.ViewHolder>
{
    private Context context;
    private List<Program> programs = new ArrayList<>();
    private final ProgramRecyclerViewInterface programRecyclerViewInterface;

    public ProgramRecyclerViewAdapter(Context context, List<Program> programs, ProgramRecyclerViewInterface programRecyclerViewInterface)
    {
        this.context = context;
        this.programs = programs;
        this.programRecyclerViewInterface = programRecyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_programs, parent, false);
        return new ViewHolder(view).linkAdapter(this);
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
                programRecyclerViewInterface.onItemClick(programs.get(holder.getAdapterPosition()));
                System.out.println(position + "PRVA");

                View editView = (ImageView) view.findViewById(R.id.imageViewEdit);
                programRecyclerViewInterface.makeVisible(editView);

                View confirmView = (ImageView) view.findViewById(R.id.imageViewConfirm);
                programRecyclerViewInterface.makeVisible(confirmView);

                View undoView = (ImageView) view.findViewById(R.id.cancelImageView);
                programRecyclerViewInterface.makeVisible(undoView);

                View backButton = (Button) view.findViewById(R.id.backButton);
                programRecyclerViewInterface.makeInvisible(backButton);

                //View addDescriptionText = (TextView) view.findViewById(R.id.addDescriptionText);
                //recyclerViewInterface.makeInvisible(addDescriptionText);
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
        private CardView cardView;
        private ProgramRecyclerViewAdapter adapter;



        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            programNameTextView = itemView.findViewById(R.id.programName);
            programDescriptionTextView = itemView.findViewById(R.id.programDescription);
            cardView = itemView.findViewById(R.id.cardViewProgram);

            itemView.findViewById(R.id.cardViewDeleteIcon).setOnClickListener(view ->
            {
                adapter.programs.remove(getAdapterPosition());
                adapter.notifyItemRemoved(getAdapterPosition());
            });
        }

        public ViewHolder linkAdapter(ProgramRecyclerViewAdapter adapter)
        {
            this.adapter = adapter;
            return this;
        }
    }
}