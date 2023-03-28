package com.example.liftingstack.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Program.
 */
public class Program {
    private String name;
    private String description;
    private List<Exercise> exercises;

    /**
     * Instantiates a new Program.
     *
     * @param name        the name
     * @param description the description
     */
    public Program(String name, String description) {
        this.name = name;
        this.description = description;
        exercises = new ArrayList<>();
    }

    /**
     * Gets exercises.
     *
     * @return the exercises
     */
    public List<Exercise> getExercises() {
        return exercises;
    }

    /**
     * Sets exercises.
     *
     * @param exercises the exercises
     */
    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    /**
     * Add exercise. Adds an exercise to the program list.
     *
     * @param exercise the exercise
     */
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
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
