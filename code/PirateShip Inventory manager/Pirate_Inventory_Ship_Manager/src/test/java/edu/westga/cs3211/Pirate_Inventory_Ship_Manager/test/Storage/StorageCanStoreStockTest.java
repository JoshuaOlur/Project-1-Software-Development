package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.test.Storage;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Stock;
import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Storage;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class StorageCanStoreStockTest {

    @Test
    public void testCanStoreValid() {
        Storage storage = new Storage("A1", 100);
        List<Stock.SpecialQuality> qualities = new ArrayList<>();
        Stock stock = new Stock("Test Item", 50, Stock.Condition.PERFECT, qualities, null);
        
        boolean result = storage.canStoreStock(stock);
        
        assertTrue(result);
    }

    @Test
    public void testCanStoreNull() {
        Storage storage = new Storage("A1", 100);
        
        boolean result = storage.canStoreStock(null);
        
        assertFalse(result);
    }

    @Test
    public void testCanStoreInsufficientSpace() {
        Storage storage = new Storage("A1", 10);
        List<Stock.SpecialQuality> qualities = new ArrayList<>();
        Stock stock = new Stock("Test Item", 20, Stock.Condition.PERFECT, qualities, null);
        
        boolean result = storage.canStoreStock(stock);
        
        assertFalse(result);
    }

    @Test
    public void testCanStoreIncompatibleQuality() {
        Storage storage = new Storage("A1", 100);
        storage.addCompatibleQuality(Stock.SpecialQuality.FLAMMABLE);
        List<Stock.SpecialQuality> qualities = new ArrayList<>();
        qualities.add(Stock.SpecialQuality.LIQUID);
        Stock stock = new Stock("Test Item", 10, Stock.Condition.PERFECT, qualities, null);
        
        boolean result = storage.canStoreStock(stock);
        
        assertFalse(result);
    }

    @Test
    public void testCanStoreMultipleQualities() {
        Storage storage = new Storage("A1", 100);
        storage.addCompatibleQuality(Stock.SpecialQuality.FLAMMABLE);
        storage.addCompatibleQuality(Stock.SpecialQuality.LIQUID);
        List<Stock.SpecialQuality> qualities = new ArrayList<>();
        qualities.add(Stock.SpecialQuality.FLAMMABLE);
        qualities.add(Stock.SpecialQuality.LIQUID);
        Stock stock = new Stock("Test Item", 10, Stock.Condition.PERFECT, qualities, null);
        
        boolean result = storage.canStoreStock(stock);
        
        assertTrue(result);
    }
}