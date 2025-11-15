package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Crewmate;

class TestConstructor {

	@Test
	void testNullName() {
		assertThrows(IllegalArgumentException.class, ()->{new Crewmate(null);});
	}

	@Test
	void testValidName() {
		Crewmate result = new Crewmate("Bob");
		
		assertEquals("Bob", result.getName(), "checking the name of the person");
	}

}
