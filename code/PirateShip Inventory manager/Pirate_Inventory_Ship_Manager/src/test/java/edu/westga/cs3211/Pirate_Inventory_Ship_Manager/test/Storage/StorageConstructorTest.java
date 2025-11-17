package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.test.Storage;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Storage;

import static org.junit.jupiter.api.Assertions.*;

public class StorageConstructorTest {

    @Test
    public void testValidParams() {
        String identifier = "A1";
        int capacity = 100;
        
        Storage storage = new Storage(identifier, capacity);
        
        assertEquals(identifier, storage.getIdentifier());
        assertEquals(capacity, storage.getCapacity());
        assertEquals(capacity, storage.getAvailableSpace());
        assertTrue(storage.getCompatibleQualities().isEmpty());
        assertTrue(storage.getStoredStock().isEmpty());
        assertTrue(storage.getStockHistory().isEmpty());
    }

    @Test
    public void testNullIdentifier() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Storage(null, 100);
        });
    }

    @Test
    public void testEmptyIdentifier() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Storage("", 100);
        });
    }

    @Test
    public void testZeroCapacity() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Storage("A1", 0);
        });
    }

    @Test
    public void testNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Storage("A1", -10);
        });
    }
}