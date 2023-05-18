package com.example.liftingstack.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The type Program.
 */
public class Program implements Parcelable {
    private String id;

    private String name;
    private String description;
    private List<String> exerciseInstructions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Instantiates a new Program.
     * Gives the program a unique id.
     * @param name        the name
     * @param description the description
     */
    public Program(String name, String description) {
        this.id = UUID.randomUUID().toString();
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
    public List<String> getExercises() {
        return exerciseInstructions;
    }

    /**
     * Sets exercises.
     *
     * @param exerciseInstructions the exercises
     */
    public void setExercises(List<String> exerciseInstructions) {
        this.exerciseInstructions = exerciseInstructions;
    }

    /**
     * Add exercise. Adds an exercise to the program list.
     *
     * @param exerciseInstructions the exercise
     */
    public void addExercise(String exerciseInstructions) {
        this.exerciseInstructions.add(exerciseInstructions);
    }

    public void removeExercise(String exerciseID) {
        this.exerciseInstructions.remove(exerciseID);
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
