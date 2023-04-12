package com.example.liftingstack.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Program.
 */
public class Program implements Parcelable {
    private String name;
    private String description;
    private List<ExerciseInstructions> exerciseInstructions;

    /**
     * Instantiates a new Program.
     *
     * @param name        the name
     * @param description the description
     */
    public Program(String name, String description) {
        this.name = name;
        this.description = description;
        exerciseInstructions = new ArrayList<>();
    }

    protected Program(Parcel in) {
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<Program> CREATOR = new Creator<Program>() {
        @Override
        public Program createFromParcel(Parcel in) {
            return new Program(in);
        }

        @Override
        public Program[] newArray(int size) {
            return new Program[size];
        }
    };

    /**
     * Gets exercises.
     *
     * @return the exercises
     */
    public List<ExerciseInstructions> getExercises() {
        return exerciseInstructions;
    }

    /**
     * Sets exercises.
     *
     * @param exerciseInstructions the exercises
     */
    public void setExercises(List<ExerciseInstructions> exerciseInstructions) {
        this.exerciseInstructions = exerciseInstructions;
    }

    /**
     * Add exercise. Adds an exercise to the program list.
     *
     * @param exerciseInstructions the exercise
     */
    public void addExercise(ExerciseInstructions exerciseInstructions) {
        this.exerciseInstructions.add(exerciseInstructions);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
    }
}
