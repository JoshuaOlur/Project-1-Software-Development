package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles logging stock changes to a text file and reading them back.
 * 
 * @author JO
 * @version Fall 2025
 */
public class StockLogger {
    private static final String LOG_FILE = "stock_changes.log";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Logs a stock change to the text file.
     * 
     * @param username the username who added the stock
     * @param stockName the name of the stock
     * @param quantity the quantity added
     * @param condition the condition of the stock
     * @param qualities the special qualities
     * @param storageCompartment the storage compartment used
     */
    public static void logStockChange(String username, String stockName, int quantity, 
                                    String condition, String qualities, String storageCompartment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = LocalDateTime.now().format(FORMATTER);
            String logEntry = String.format("[%s] User: %s | Item: %s | Qty: %d | Condition: %s | Qualities: %s | Storage: %s",
                    timestamp, username, stockName, quantity, condition, qualities, storageCompartment);
            
            writer.write(logEntry);
            writer.newLine();
            writer.flush();
        } catch (IOException eE) {
            System.err.println("Error writing to log file: " + eE.getMessage());
        }
    }

    /**
     * Reads all stock changes from the log file.
     * 
     * @return list of stock change entries in reverse chronological order
     */
    public static List<String> readStockChanges() {
        List<String> stockChanges = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stockChanges.add(0, line); 
            }
        } catch (IOException eE) {
            System.err.println("Error reading log file: " + eE.getMessage());
        }
        
        return stockChanges;
    }

    /**
     * Clears the log file (for testing/reset purposes).
     */
    public static void clearLog() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, false))) {
            writer.write("");
        } catch (IOException eE) {
            System.err.println("Error clearing log file: " + eE.getMessage());
        }
    }
}