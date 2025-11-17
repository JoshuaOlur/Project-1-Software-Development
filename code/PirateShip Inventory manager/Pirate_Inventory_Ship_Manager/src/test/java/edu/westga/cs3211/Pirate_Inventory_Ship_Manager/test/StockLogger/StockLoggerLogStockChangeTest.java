package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.test.StockLogger;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.StockLogger;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class StockLoggerLogStockChangeTest {

    @Test
    public void testLogEntry() {
        StockLogger.clearLog();
        
        String username = "testUser";
        String stockName = "Test Item";
        int quantity = 5;
        String condition = "PERFECT";
        String qualities = "FLAMMABLE";
        String storage = "Storage A";
        
        StockLogger.logStockChange(username, stockName, quantity, condition, qualities, storage);
        
        List<String> entries = StockLogger.readStockChanges();
        assertEquals(1, entries.size());
        
        String entry = entries.get(0);
        assertTrue(entry.contains("User: " + username));
        assertTrue(entry.contains("Item: " + stockName));
        assertTrue(entry.contains("Qty: " + quantity));
        assertTrue(entry.contains("Condition: " + condition));
        assertTrue(entry.contains("Qualities: " + qualities));
        assertTrue(entry.contains("Storage: " + storage));
    }

    @Test
    public void testMultipleLogs() {
        StockLogger.clearLog();
        
        StockLogger.logStockChange("user1", "Item1", 1, "PERFECT", "NONE", "Storage A");
        StockLogger.logStockChange("user2", "Item2", 2, "USABLE", "LIQUID", "Storage B");
        
        List<String> entries = StockLogger.readStockChanges();
        assertEquals(2, entries.size());
        
        String secondEntry = entries.get(0);
        assertTrue(secondEntry.contains("user2"));
        
        String firstEntry = entries.get(1);
        assertTrue(firstEntry.contains("user1"));
    }

    @Test
    public void testNoQualities() {
        StockLogger.clearLog();
        
        StockLogger.logStockChange("testUser", "Test Item", 3, "UNUSABLE", "NONE", "Storage C");
        
        List<String> entries = StockLogger.readStockChanges();
        String entry = entries.get(0);
        assertTrue(entry.contains("Qualities: NONE"));
    }

    @Test
    public void testMultipleQualities() {
        StockLogger.clearLog();
        
        StockLogger.logStockChange("testUser", "Test Item", 4, "USABLE", "FLAMMABLE LIQUID", "Storage D");
        
        List<String> entries = StockLogger.readStockChanges();
        String entry = entries.get(0);
        assertTrue(entry.contains("FLAMMABLE LIQUID"));
    }
}
