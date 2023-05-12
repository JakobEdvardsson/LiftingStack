package com.example.liftingstack.ExerciseActivities;

import com.example.liftingstack.Entity.ExerciseInstruction;

public interface ExerciseRecyclerViewInterface {
    void onExerciseClick(ExerciseInstruction exerciseInstruction);
    void removeExerciseAndUpdateList(int index);

}
