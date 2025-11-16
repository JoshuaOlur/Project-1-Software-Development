package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model;

import java.time.LocalDateTime;

/**
 * Represents a change in stock inventory, logging all details required
 * by the Add Stock Use Case. This includes information about the crewmate
 * who made the change, the stock added, the storage compartment used,
 * and the timestamp of the change.
 * 
 * @author JO
 * @version Fall 2025
 */
public class StockChange {

    private Crewmate crewmate;
    private Stock stock;
    private Storage storage;
    private LocalDateTime timestamp;

    /**
     * Creates a new stock change record.
     * 
     * @precondition crewmate != null && stock != null && storage != null && timestamp != null
     * @postcondition getCrewmate() == crewmate && getStock() == stock && 
     *                getStorage() == storage && getTimestamp() == timestamp
     * 
     * @param crewmate the crewmate who made the stock change
     * @param stock the stock that was added or modified
     * @param storage the storage compartment where stock was stored
     * @param timestamp the date and time when the change occurred
     */
    public StockChange(Crewmate crewmate, Stock stock, Storage storage, LocalDateTime timestamp) {
        if (crewmate == null) {
            throw new IllegalArgumentException("crewmate cannot be null");
        }
        if (stock == null) {
            throw new IllegalArgumentException("stock cannot be null");
        }
        if (storage == null) {
            throw new IllegalArgumentException("storage cannot be null");
        }
        if (timestamp == null) {
            throw new IllegalArgumentException("timestamp cannot be null");
        }

        this.crewmate = crewmate;
        this.stock = stock;
        this.storage = storage;
        this.timestamp = timestamp;
    }

    /**
     * Returns the crewmate who made the stock change.
     * 
     * @return the crewmate responsible for the change
     */
    public Crewmate getCrewmate() {
        return this.crewmate;
    }

    /**
     * Returns the stock that was added or modified.
     * 
     * @return the stock involved in the change
     */
    public Stock getStock() {
        return this.stock;
    }

    /**
     * Returns the storage compartment where the stock was stored.
     * 
     * @return the storage compartment used
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Returns the timestamp when the stock change occurred.
     * 
     * @return the date and time of the change
     */
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    /**
     * Returns a string representation of the stock change.
     * 
     * @return string containing crewmate name, stock details, storage identifier, and timestamp
     */
    @Override
    public String toString() {
        return String.format("[%s] %s added %d x %s to %s (Condition: %s, Qualities: %s)",
                this.timestamp.toString(),
                this.crewmate.getName(),
                this.stock.getQuantity(),
                this.stock.getName(),
                this.storage.getIdentifier(),
                this.stock.getCondition().toString(),
                this.stock.getQualities().toString());
    }
}