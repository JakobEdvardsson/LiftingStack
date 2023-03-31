package com.example.liftingstack.Entity;

/**
 * The type Exercise.
 * This class is a specific exercise such as Bench Press or Squats.
 */
public class ExerciseInstructions {
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
}
