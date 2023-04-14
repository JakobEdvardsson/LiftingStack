package com.example.liftingstack.ProgramsActivities;


import android.view.View;


import com.example.liftingstack.Entity.Program;

public interface ProgramRecyclerViewInterface
{
    void onItemClick(Program program);

    void makeVisible(View view);

    void makeInvisible(View view);

}