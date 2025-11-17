package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.test.Crewmate;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Crewmate;

import static org.junit.jupiter.api.Assertions.*;

public class TestCrewmate {

    @Test
    public void testConstructorValidName() {
        String expectedName = "Jack Sparrow";
        Crewmate crewmate = new Crewmate(expectedName);
        
        assertEquals(expectedName, crewmate.getName());
    }

    @Test
    public void testConstructorNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Crewmate(null);
        });
    }

    @Test
    public void testConstructorEmptyName() {
        String expectedName = "";
        Crewmate crewmate = new Crewmate(expectedName);
        
        assertEquals(expectedName, crewmate.getName());
    }

    @Test
    public void testConstructorNameWithSpaces() {
        String expectedName = "  Will Turner  ";
        Crewmate crewmate = new Crewmate(expectedName);
        
        assertEquals(expectedName, crewmate.getName());
    }

    @Test
    public void testConstructorSpecialCharactersInName() {
        String expectedName = "Elizabeth-Swann";
        Crewmate crewmate = new Crewmate(expectedName);
        
        assertEquals(expectedName, crewmate.getName());
    }
}