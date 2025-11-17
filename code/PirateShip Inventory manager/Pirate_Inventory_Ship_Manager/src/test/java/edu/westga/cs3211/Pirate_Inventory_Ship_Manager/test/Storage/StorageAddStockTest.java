package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.test.Storage;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Crewmate;
import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Stock;
import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Storage;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class StorageAddStockTest {

	@Test
	public void testAddValidStock() {
		Storage storage = new Storage("A1", 100);
		Crewmate crewmate = new Crewmate("Test Crewmate");
		List<Stock.SpecialQuality> qualities = new ArrayList<>();
		Stock stock = new Stock("Test Item", 10, Stock.Condition.PERFECT, qualities, null);

		boolean result = storage.addStock(stock, crewmate);

		assertTrue(result);
		assertEquals(90, storage.getAvailableSpace());
		assertEquals(1, storage.getStoredStock().size());
		assertEquals(1, storage.getStockHistory().size());
	}

	@Test
	public void testAddNullStock() {
		Storage storage = new Storage("A1", 100);
		Crewmate crewmate = new Crewmate("Test Crewmate");

		assertThrows(IllegalArgumentException.class, () -> {
			storage.addStock(null, crewmate);
		});
	}

	@Test
	public void testAddNullCrewmate() {
		Storage storage = new Storage("A1", 100);
		List<Stock.SpecialQuality> qualities = new ArrayList<>();
		Stock stock = new Stock("Test Item", 10, Stock.Condition.PERFECT, qualities, null);

		assertThrows(IllegalArgumentException.class, () -> {
			storage.addStock(stock, null);
		});
	}

	@Test
	public void testAddStockInsufficientSpace() {
		Storage storage = new Storage("A1", 5);
		Crewmate crewmate = new Crewmate("Test Crewmate");
		List<Stock.SpecialQuality> qualities = new ArrayList<>();
		Stock stock = new Stock("Test Item", 10, Stock.Condition.PERFECT, qualities, null);

		boolean result = storage.addStock(stock, crewmate);

		assertFalse(result);
		assertEquals(5, storage.getAvailableSpace());
		assertTrue(storage.getStoredStock().isEmpty());
	}

	@Test
	public void testAddStockIncompatibleQuality() {
		Storage storage = new Storage("A1", 100);
		storage.addCompatibleQuality(Stock.SpecialQuality.FLAMMABLE);
		Crewmate crewmate = new Crewmate("Test Crewmate");
		List<Stock.SpecialQuality> qualities = new ArrayList<>();
		qualities.add(Stock.SpecialQuality.LIQUID);
		Stock stock = new Stock("Test Item", 10, Stock.Condition.PERFECT, qualities, null);

		boolean result = storage.addStock(stock, crewmate);

		assertFalse(result);
		assertEquals(100, storage.getAvailableSpace());
		assertTrue(storage.getStoredStock().isEmpty());
	}
}