package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents an item of Stock that can be stored within the ship's inventory.
 * 
 * This class supports the Add Stock Use Case by storing all information the
 * system must log when stock is added to a compartment.
 * 
 * 
 * @author CS
 * @version Fall 2025
 */
public class Stock {

    /**
     * Represents the condition of stock as provided by the Crewmate.
     */
    public enum Condition {
        PERFECT,
        USABLE,
        UNUSABLE
    }

    /**
     * Represents any special qualities the stock may have that affect where
     * it can be stored.
     */
    public enum SpecialQuality {
        FLAMMABLE,
        LIQUID,
        PERISHABLE
    }

    private String name;
    private int quantity;
    private Condition condition;
    private List<SpecialQuality> qualities;
    private LocalDate expirationDate;

    /**
     * Creates a new Stock item with the provided information.
     * 
     * @precondition
     *      name != null &&
     *      quantity > 0 &&
     *      condition != null &&
     *      qualities != null &&
     *      (if qualities contains PERISHABLE, expirationDate != null)
     * 
     * @postcondition
     *      getName() == name &&
     *      getQuantity() == quantity &&
     *      getCondition() == condition &&
     *      getQualities() == qualities &&
     *      getExpirationDate() == expirationDate
     *
     * @param name            the name of the stock item
     * @param quantity        the quantity being added
     * @param condition       the condition of the stock (perfect, usable, unusable)
     * @param qualities       list of special qualities such as flammable or liquid
     * @param expirationDate  expiration date if PERISHABLE stock; otherwise may be null
     * 
     * @throws IllegalArgumentException if preconditions are violated
     */
    public Stock(String name, int quantity, Condition condition, 
                 List<SpecialQuality> qualities, LocalDate expirationDate) {

        if (name == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive.");
        }
        if (condition == null) {
            throw new IllegalArgumentException("condition cannot be null.");
        }
        if (qualities == null) {
            throw new IllegalArgumentException("qualities cannot be null.");
        }
        if (qualities.contains(SpecialQuality.PERISHABLE) && expirationDate == null) {
            throw new IllegalArgumentException("Perishable stock must include an expiration date.");
        }

        this.name = name;
        this.quantity = quantity;
        this.condition = condition;
        this.qualities = qualities;
        this.expirationDate = expirationDate;
    }

    /**
     * Returns the name of the stock item.
     * 
     * @return the name of the stock
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the quantity of the stock item.
     * 
     * @return the quantity of stock
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Returns the condition of the stock item.
     * 
     * @return the condition of the stock
     */
    public Condition getCondition() {
        return this.condition;
    }

    /**
     * Returns the list of special qualities associated with the stock.
     * 
     * @return list of special qualities
     */
    public List<SpecialQuality> getQualities() {
        return this.qualities;
    }

    /**
     * Returns the expiration date of the stock, if applicable.
     * 
     * @return expiration date for perishable items, otherwise null
     */
    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }
}
