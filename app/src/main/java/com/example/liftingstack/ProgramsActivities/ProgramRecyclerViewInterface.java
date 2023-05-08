package com.example.liftingstack.ProgramsActivities;


import com.example.liftingstack.Entity.Program;

public interface ProgramRecyclerViewInterface {
    void onItemClick(Program program);
    void onProgramDelete(String program);
}
