package com.example.liftingstack.Utilities;

import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.Entity.Program;
import com.google.gson.Gson;

public class SaveUtilities {

    public void saveExerciseInstruction(ExerciseInstructions exerciseInstructions) {

    }

    public void saveProgram(Program program) {

    }

    public String convertObjectToJson(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        System.out.println(json);
        return json;
    }
}
