package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.test.Storage;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Crewmate;
import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Stock;
import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Storage;

import static org.junit.jupiter.api.Assertions.*;

public class StorageToStringTest {

	@Test
	public void testToStringFormat() {
		Storage storage = new Storage("Storage A", 100);

		String result = storage.toString();

		assertEquals("Storage Storage A: 100/100 available", result);
	}

	@Test
	public void testToStringAfterAddingStock() {
		Storage storage = new Storage("Storage B", 50);
		Crewmate crewmate = new Crewmate("Test Crewmate");
		Stock stock = new Stock("Test Item", 10, Stock.Condition.PERFECT, new java.util.ArrayList<>(), null);

		storage.addStock(stock, crewmate);
		String result = storage.toString();

		assertEquals("Storage Storage B: 40/50 available", result);
	}
}