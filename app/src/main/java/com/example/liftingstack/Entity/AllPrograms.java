package com.example.liftingstack.Entity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Controller.LoadFromDevice;
import com.example.liftingstack.Controller.SaveToDevice;

import java.util.ArrayList;

public class AllPrograms {
    private ArrayList<Program> programsList;

    public AllPrograms(AppCompatActivity appCompatActivity) {
        programsList = new ArrayList<>();
        //TODO Load the list from the device, here should be a check if the file exists if not load from the assets folder
        try {
            programsList = new LoadFromDevice().loadProgramListFromDevice(appCompatActivity, "programList");
        } catch (Exception e) {
            //TODO load from assets folder
            System.out.println("Error loading from device, file may not exist");
            e.printStackTrace();
        }
    }

    public void saveProgramList(AppCompatActivity appCompatActivity) {
        new SaveToDevice().saveListToDevice(programsList, appCompatActivity, "programList");
    }
    
    public ArrayList<Program> getProgramsList() {
        return programsList;
    }

    public void setProgramsList(ArrayList<Program> programsList) {
        this.programsList = programsList;
    }

    public void addProgram(Program program) {
        programsList.add(program);
    }

    public void removeProgram(Program program) {
        programsList.remove(program);
    }
}
