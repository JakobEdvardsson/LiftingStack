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

import java.util.List;

/*public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>{
    private Context context;
    private List<ExerciseInstructions> exerciseInstructions;

    public MainRecyclerViewAdapter(Context context, List<ExerciseInstructions> exerciseInstructions) {
        this.context = context;
        this.exerciseInstructions = exerciseInstructions;
    }

    @NonNull
    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_program_exercise, parent, false);
        return new MainRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerViewAdapter.ViewHolder holder, int position) {
        //holder.imageView.setImageResource(R.drawable.ic_launcher_foreground);
        holder.textView.setText(exerciseInstructions.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return exerciseInstructions.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.programName);
        }
    }
}*/
