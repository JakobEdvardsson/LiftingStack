package com.example.liftingstack.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.UUID;

/**
 * The type Exercise.
 * This class is a specific exercise such as Bench Press or Squats.
 */
public class ExerciseInstructions implements Parcelable {
    private String name;
    private String description;
    private String id;
    private String image;
    //Text / Video

    /**
     * Instantiates a new Exercise.
     *
     * @param name        the name
     * @param description the description
     */
    public ExerciseInstructions(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }
    public ExerciseInstructions(String name, String description, String image) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
