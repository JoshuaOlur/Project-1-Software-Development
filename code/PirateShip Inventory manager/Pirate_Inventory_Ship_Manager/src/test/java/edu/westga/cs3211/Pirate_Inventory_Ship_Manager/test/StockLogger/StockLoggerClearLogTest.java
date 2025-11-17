package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.test.StockLogger;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.StockLogger;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class StockLoggerClearLogTest {

	@Test
	public void testClearWithEntries() {
		StockLogger.logStockChange("testUser", "Test Item", 5, "PERFECT", "NONE", "Storage A");

		StockLogger.clearLog();

		List<String> entries = StockLogger.readStockChanges();
		assertTrue(entries.isEmpty());
	}

	@Test
	public void testClearEmptyFile() {
		StockLogger.clearLog();

		StockLogger.clearLog();

		List<String> entries = StockLogger.readStockChanges();
		assertTrue(entries.isEmpty());
	}

	@Test
	public void testLogAfterClear() {
		StockLogger.clearLog();

		StockLogger.logStockChange("newUser", "New Item", 10, "USABLE", "LIQUID", "Storage B");

		List<String> entries = StockLogger.readStockChanges();
		assertEquals(1, entries.size());
		assertTrue(entries.get(0).contains("newUser"));
	}
}