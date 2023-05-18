package com.example.liftingstack.Entity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Controller.LoadFromDevice;
import com.example.liftingstack.Controller.SaveToDevice;

import java.util.ArrayList;

/**
 * This class is used to store all programs.
 * It is used to save and load the programs from the device.
 */
public class AllPrograms {
    private ArrayList<Program> programsList;

    /**
     * Instantiates a new All programs.
     * Loads the programs from the device.
     *
     * @param appCompatActivity the app compat activity
     */
    public AllPrograms(AppCompatActivity appCompatActivity) {
        programsList = new ArrayList<>();
        try {
            programsList = new LoadFromDevice().loadProgramListFromDevice(appCompatActivity, "programList");
        } catch (Exception e) {
            saveProgramList(appCompatActivity);
            System.out.println("Error loading from device, file may not exist - Printed from AllPrograms");
            e.printStackTrace();
        }
    }

    /**
     * Saves the programs to the device.
     *
     * @param appCompatActivity the app compat activity
     */
    public void saveProgramList(AppCompatActivity appCompatActivity) {
        new SaveToDevice().saveListToDevice(programsList, appCompatActivity, "programList");
    }

    /**
     * Gets programs list.
     *
     * @return the programs list
     */
    public ArrayList<Program> getProgramsList() {
        return programsList;
    }

    /**
     * Sets programs list.
     *
     * @param programsList the programs list
     */
    public void setProgramsList(ArrayList<Program> programsList) {
        this.programsList = programsList;
    }

    /**
     * Add program.
     *
     * @param program the program
     */
    public void addProgram(Program program) {
        programsList.add(program);
    }

}
