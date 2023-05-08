package com.example.liftingstack.ProgramsActivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liftingstack.Entity.AllPrograms;
import com.example.liftingstack.R;

public class ProgramRecyclerViewAdapter extends RecyclerView.Adapter<ProgramRecyclerViewAdapter.ViewHolder> {
    private Context context;
    //private List<Program> programs;
    private  AllPrograms allPrograms;
    private final ProgramRecyclerViewInterface programRecyclerViewInterface;

    public ProgramRecyclerViewAdapter(Context context, ProgramRecyclerViewInterface programRecyclerViewInterface, AllPrograms allPrograms){
        this.context = context;
        this.allPrograms = allPrograms;
        this.programRecyclerViewInterface = programRecyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_programs, parent, false);
        return new ViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramRecyclerViewAdapter.ViewHolder holder, int position) {


        holder.programNameTextView.setText(allPrograms.getProgramsList().get(position).getName());
        holder.programDescriptionTextView.setText(allPrograms.getProgramsList().get(position).getDescription());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                programRecyclerViewInterface.onItemClick(allPrograms.getProgramsList().get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return allPrograms.getProgramsList().size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private TextView programNameTextView;
        private TextView programDescriptionTextView;
        private CardView cardView;
        private ProgramRecyclerViewAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            programNameTextView = itemView.findViewById(R.id.exerciseNameSelectedProgram);
            programDescriptionTextView = itemView.findViewById(R.id.programDescription);
            cardView = itemView.findViewById(R.id.cardViewProgram);

            itemView.findViewById(R.id.cardViewDeleteIcon).setOnClickListener(view -> {
                try {
                    adapter.allPrograms.getProgramsList().remove(getAdapterPosition());
                    programRecyclerViewInterface.onProgramDelete(getAdapterPosition());
                    adapter.notifyItemRemoved(getAdapterPosition());
                }
                catch (Exception e) {
                    System.out.println("Tried deleting too fast" + e);
                }
            });
        }

        public ViewHolder linkAdapter(ProgramRecyclerViewAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }
}
