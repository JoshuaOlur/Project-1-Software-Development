package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a storage compartment on the ship that can hold stock items. Each
 * storage compartment has a capacity and can only store stock with compatible
 * special qualities.
 * 
 * @author JO
 * @version Fall 2025
 */
public class Storage {

	private String identifier;
	private int capacity;
	private int availableSpace;
	private List<Stock.SpecialQuality> compatibleQualities;
	private List<Stock> storedStock;
	private List<StockChange> stockHistory;

	/**
	 * Creates a new storage compartment with the specified identifier and capacity.
	 * 
	 * @precondition identifier != null && !identifier.isEmpty() && capacity > 0
	 * @postcondition getIdentifier().equals(identifier) && getCapacity() ==
	 *                capacity && getAvailableSpace() == capacity &&
	 *                getCompatibleQualities().isEmpty() &&
	 *                getStoredStock().isEmpty()
	 * 
	 * @param identifier the unique identifier for this storage compartment
	 * @param capacity   the maximum capacity of this storage compartment
	 */
	public Storage(String identifier, int capacity) {
		if (identifier == null || identifier.isEmpty()) {
			throw new IllegalArgumentException("identifier cannot be null or empty");
		}
		if (capacity <= 0) {
			throw new IllegalArgumentException("capacity must be positive");
		}

		this.identifier = identifier;
		this.capacity = capacity;
		this.availableSpace = capacity;
		this.compatibleQualities = new ArrayList<>();
		this.storedStock = new ArrayList<>();
		this.stockHistory = new ArrayList<>();
	}

	/**
	 * Adds a stock item to this storage compartment.
	 * 
	 * @precondition stock != null && canStoreStock(stock)
	 * @postcondition getAvailableSpace() == old.getAvailableSpace() -
	 *                stock.getQuantity() && getStoredStock().contains(stock)
	 * 
	 * @param stock    the stock item to add
	 * @param crewmate the crewmate adding the stock
	 * @return true if stock was successfully added, false otherwise
	 */
	public boolean addStock(Stock stock, Crewmate crewmate) {
		if (stock == null) {
			throw new IllegalArgumentException("stock cannot be null");
		}
		if (crewmate == null) {
			throw new IllegalArgumentException("crewmate cannot be null");
		}
		if (!this.canStoreStock(stock)) {
			return false;
		}
		if (stock.getQuantity() > this.availableSpace) {
			return false;
		}

		this.storedStock.add(stock);
		this.availableSpace -= stock.getQuantity();

		StockChange change = new StockChange(crewmate, stock, this, LocalDateTime.now());
		this.stockHistory.add(change);

		return true;
	}

	/**
	 * Checks if this storage compartment can store the given stock item.
	 * 
	 * @precondition stock != null
	 * @postcondition none
	 * 
	 * @param stock the stock item to check
	 * @return true if this compartment has sufficient space and can handle all
	 *         special qualities
	 */
	public boolean canStoreStock(Stock stock) {
		if (stock == null) {
			return false;
		}

		if (stock.getQuantity() > this.availableSpace) {
			return false;
		}

		for (Stock.SpecialQuality quality : stock.getQualities()) {
			if (!this.compatibleQualities.contains(quality)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Adds a compatible quality to this storage compartment.
	 * 
	 * @precondition quality != null
	 * @postcondition getCompatibleQualities().contains(quality)
	 * 
	 * @param quality the special quality this compartment can handle
	 */
	public void addCompatibleQuality(Stock.SpecialQuality quality) {
		if (quality == null) {
			throw new IllegalArgumentException("quality cannot be null");
		}
		if (!this.compatibleQualities.contains(quality)) {
			this.compatibleQualities.add(quality);
		}
	}

	/**
	 * Returns the unique identifier of this storage compartment.
	 * 
	 * @return the storage compartment identifier
	 */
	public String getIdentifier() {
		return this.identifier;
	}

	/**
	 * Returns the total capacity of this storage compartment.
	 * 
	 * @return the total capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * Returns the available space in this storage compartment.
	 * 
	 * @return the available space
	 */
	public int getAvailableSpace() {
		return this.availableSpace;
	}

	/**
	 * Returns the list of compatible special qualities for this storage
	 * compartment.
	 * 
	 * @return list of compatible qualities
	 */
	public List<Stock.SpecialQuality> getCompatibleQualities() {
		return new ArrayList<>(this.compatibleQualities);
	}

	/**
	 * Returns the list of stock items currently stored in this compartment.
	 * 
	 * @return list of stored stock items
	 */
	public List<Stock> getStoredStock() {
		return new ArrayList<>(this.storedStock);
	}

	/**
	 * Returns the history of stock changes for this storage compartment.
	 * 
	 * @return list of stock changes
	 */
	public List<StockChange> getStockHistory() {
		return new ArrayList<>(this.stockHistory);
	}

	/**
	 * Returns a string representation of this storage compartment.
	 * 
	 * @return string representation including identifier and available space
	 */
	@Override
	public String toString() {
		return String.format("Storage %s: %d/%d available", this.identifier, this.availableSpace, this.capacity);
	}
}