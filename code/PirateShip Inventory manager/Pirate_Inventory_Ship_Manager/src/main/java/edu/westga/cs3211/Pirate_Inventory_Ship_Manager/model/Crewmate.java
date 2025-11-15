package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model;

/**
 * Represents a Crewmate who is part of the ship's crew.
 * 
 * 
 * @author CS 3211
 * @version Fall 2025
 */
public class Crewmate {

    private static final String NAME_CANNOT_BE_NULL = "name cannot be null.";
    private String name;

    /**
     * Creates a new Crewmate with the provided name.
     * 
     * @precondition name != null
     * @postcondition getName().equals(name)
     * 
     * @param name the name of the Crewmate
     * @throws IllegalArgumentException if name is null
     */
    public Crewmate(String name) {
        if (name == null) {
            throw new IllegalArgumentException(NAME_CANNOT_BE_NULL);
        }

        this.name = name;
    }

    /**
     * Returns the Crewmate's name.
     * 
     * @precondition none
     * @postcondition none
     *
     * @return the name of the Crewmate
     */
    public String getName() {
        return this.name;
    }
}
