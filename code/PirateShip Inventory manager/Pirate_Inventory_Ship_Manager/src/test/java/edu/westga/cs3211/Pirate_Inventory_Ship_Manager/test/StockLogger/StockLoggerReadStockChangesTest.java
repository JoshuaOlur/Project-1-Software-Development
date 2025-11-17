package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.test.StockLogger;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.StockLogger;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class StockLoggerReadStockChangesTest {

	@Test
	public void testReadEmptyFile() {
		StockLogger.clearLog();

		List<String> entries = StockLogger.readStockChanges();

		assertTrue(entries.isEmpty());
	}

	@Test
	public void testReadMultipleEntries() {
		StockLogger.clearLog();

		StockLogger.logStockChange("user1", "Item1", 1, "PERFECT", "NONE", "A");
		StockLogger.logStockChange("user2", "Item2", 2, "USABLE", "FLAMMABLE", "B");
		StockLogger.logStockChange("user3", "Item3", 3, "UNUSABLE", "LIQUID", "C");

		List<String> entries = StockLogger.readStockChanges();

		assertEquals(3, entries.size());
		assertTrue(entries.get(0).contains("user3"));
		assertTrue(entries.get(1).contains("user2"));
		assertTrue(entries.get(2).contains("user1"));
	}

	@Test
	public void testReverseChronologicalOrder() {
		StockLogger.clearLog();

		StockLogger.logStockChange("first", "First", 1, "PERFECT", "NONE", "A");
		StockLogger.logStockChange("second", "Second", 2, "USABLE", "FLAMMABLE", "B");

		List<String> entries = StockLogger.readStockChanges();

		assertEquals(2, entries.size());
		assertTrue(entries.get(0).contains("second"));
		assertTrue(entries.get(1).contains("first"));
	}
}