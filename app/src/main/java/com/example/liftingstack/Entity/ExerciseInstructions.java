package com.example.liftingstack.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * The type Exercise.
 * This class is a specific exercise such as Bench Press or Squats.
 */
public class ExerciseInstructions implements Parcelable {
    private String name;
    private String description;
    //Text / Video

    /**
     * Instantiates a new Exercise.
     *
     * @param name        the name
     * @param description the description
     */
    public ExerciseInstructions(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected ExerciseInstructions(Parcel in) {
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<ExerciseInstructions> CREATOR = new Creator<ExerciseInstructions>() {
        @Override
        public ExerciseInstructions createFromParcel(Parcel in) {
            return new ExerciseInstructions(in);
        }

        @Override
        public ExerciseInstructions[] newArray(int size) {
            return new ExerciseInstructions[size];
        }
    };

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getExerciseName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setExerciseName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getExerciseDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setExerciseDescription(String description) {
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
