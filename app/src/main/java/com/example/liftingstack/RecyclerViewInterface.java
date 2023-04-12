package com.example.liftingstack;


import android.view.View;


import com.example.liftingstack.Entity.Program;

public interface RecyclerViewInterface
{
    void onItemClick(Program program);

    void makeVisible(View view);

    void makeInvisible(View view);

}
