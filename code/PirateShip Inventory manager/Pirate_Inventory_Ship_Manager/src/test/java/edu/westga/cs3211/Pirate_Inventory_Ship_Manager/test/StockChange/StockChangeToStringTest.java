package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.test.StockChange;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Crewmate;
import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Stock;
import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.StockChange;
import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Storage;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StockChangeToStringTest {

    @Test
    public void testToStringFormat() {
        Crewmate crewmate = new Crewmate("Jack Sparrow");
        List<Stock.SpecialQuality> qualities = new ArrayList<>();
        qualities.add(Stock.SpecialQuality.LIQUID);
        Stock stock = new Stock("Rum", 10, Stock.Condition.PERFECT, qualities, null);
        Storage storage = new Storage("Storage C", 75);
        LocalDateTime timestamp = LocalDateTime.of(2024, 1, 15, 10, 30);
        
        StockChange stockChange = new StockChange(crewmate, stock, storage, timestamp);
        String result = stockChange.toString();
        
        assertTrue(result.contains("Jack Sparrow"));
        assertTrue(result.contains("10 x Rum"));
        assertTrue(result.contains("Storage C"));
        assertTrue(result.contains("PERFECT"));
        assertTrue(result.contains("LIQUID"));
        assertTrue(result.contains("2024-01-15T10:30"));
    }

    @Test
    public void testToStringMultipleQualities() {
        Crewmate crewmate = new Crewmate("Test Crewmate");
        List<Stock.SpecialQuality> qualities = new ArrayList<>();
        qualities.add(Stock.SpecialQuality.FLAMMABLE);
        qualities.add(Stock.SpecialQuality.LIQUID);
        Stock stock = new Stock("Gunpowder", 5, Stock.Condition.USABLE, qualities, null);
        Storage storage = new Storage("Storage B", 50);
        LocalDateTime timestamp = LocalDateTime.now();
        
        StockChange stockChange = new StockChange(crewmate, stock, storage, timestamp);
        String result = stockChange.toString();
        
        assertTrue(result.contains("FLAMMABLE"));
        assertTrue(result.contains("LIQUID"));
    }

    @Test
    public void testToStringNoQualities() {
        Crewmate crewmate = new Crewmate("Test Crewmate");
        List<Stock.SpecialQuality> qualities = new ArrayList<>();
        Stock stock = new Stock("Cannonballs", 20, Stock.Condition.UNUSABLE, qualities, null);
        Storage storage = new Storage("Storage A", 100);
        LocalDateTime timestamp = LocalDateTime.now();
        
        StockChange stockChange = new StockChange(crewmate, stock, storage, timestamp);
        String result = stockChange.toString();
        
        assertTrue(result.contains("UNUSABLE"));
        assertTrue(result.contains("[]"));
    }
}