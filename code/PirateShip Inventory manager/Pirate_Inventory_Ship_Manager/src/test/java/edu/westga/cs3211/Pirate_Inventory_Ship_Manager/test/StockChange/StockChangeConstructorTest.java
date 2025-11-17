package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.test.StockChange;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Stock;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class StockChangeConstructorTest {

    @Test
    public void testValidParams() {
        String name = "Test Item";
        int quantity = 5;
        Stock.Condition condition = Stock.Condition.PERFECT;
        List<Stock.SpecialQuality> qualities = new ArrayList<>();
        LocalDate expirationDate = null;
        
        Stock stock = new Stock(name, quantity, condition, qualities, expirationDate);
        
        assertEquals(name, stock.getName());
        assertEquals(quantity, stock.getQuantity());
        assertEquals(condition, stock.getCondition());
        assertEquals(qualities, stock.getQualities());
        assertEquals(expirationDate, stock.getExpirationDate());
    }

    @Test
    public void testNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Stock(null, 5, Stock.Condition.PERFECT, new ArrayList<>(), null);
        });
    }

    @Test
    public void testZeroQuantity() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Stock("Test", 0, Stock.Condition.PERFECT, new ArrayList<>(), null);
        });
    }

    @Test
    public void testNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Stock("Test", -1, Stock.Condition.PERFECT, new ArrayList<>(), null);
        });
    }

    @Test
    public void testNullCondition() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Stock("Test", 5, null, new ArrayList<>(), null);
        });
    }

    @Test
    public void testNullQualities() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Stock("Test", 5, Stock.Condition.PERFECT, null, null);
        });
    }

    @Test
    public void testPerishableNoExpiration() {
        List<Stock.SpecialQuality> qualities = new ArrayList<>();
        qualities.add(Stock.SpecialQuality.PERISHABLE);
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Stock("Test", 5, Stock.Condition.PERFECT, qualities, null);
        });
    }

    @Test
    public void testPerishableWithExpiration() {
        String name = "Perishable Item";
        int quantity = 10;
        Stock.Condition condition = Stock.Condition.USABLE;
        List<Stock.SpecialQuality> qualities = new ArrayList<>();
        qualities.add(Stock.SpecialQuality.PERISHABLE);
        LocalDate expirationDate = LocalDate.now().plusDays(30);
        
        Stock stock = new Stock(name, quantity, condition, qualities, expirationDate);
        
        assertEquals(name, stock.getName());
        assertEquals(quantity, stock.getQuantity());
        assertEquals(condition, stock.getCondition());
        assertEquals(qualities, stock.getQualities());
        assertEquals(expirationDate, stock.getExpirationDate());
    }

    @Test
    public void testMultipleQualities() {
        List<Stock.SpecialQuality> qualities = new ArrayList<>();
        qualities.add(Stock.SpecialQuality.FLAMMABLE);
        qualities.add(Stock.SpecialQuality.LIQUID);
        
        Stock stock = new Stock("Test", 3, Stock.Condition.UNUSABLE, qualities, null);
        
        assertEquals(2, stock.getQualities().size());
        assertTrue(stock.getQualities().contains(Stock.SpecialQuality.FLAMMABLE));
        assertTrue(stock.getQualities().contains(Stock.SpecialQuality.LIQUID));
    }

    @Test
    public void testAllConditions() {
        Stock.Condition[] conditions = Stock.Condition.values();
        
        for (Stock.Condition condition : conditions) {
            Stock stock = new Stock("Test", 1, condition, new ArrayList<>(), null);
            assertEquals(condition, stock.getCondition());
        }
    }
}