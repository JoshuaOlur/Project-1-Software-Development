package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.test.Storage;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Stock;
import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Storage;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class StorageAddCompatibleQualityTest {

	@Test
	public void testAddValidQuality() {
		Storage storage = new Storage("A1", 100);

		storage.addCompatibleQuality(Stock.SpecialQuality.FLAMMABLE);

		List<Stock.SpecialQuality> qualities = storage.getCompatibleQualities();
		assertEquals(1, qualities.size());
		assertTrue(qualities.contains(Stock.SpecialQuality.FLAMMABLE));
	}

	@Test
	public void testAddNullQuality() {
		Storage storage = new Storage("A1", 100);

		assertThrows(IllegalArgumentException.class, () -> {
			storage.addCompatibleQuality(null);
		});
	}

	@Test
	public void testAddDuplicateQuality() {
		Storage storage = new Storage("A1", 100);

		storage.addCompatibleQuality(Stock.SpecialQuality.LIQUID);
		storage.addCompatibleQuality(Stock.SpecialQuality.LIQUID);

		List<Stock.SpecialQuality> qualities = storage.getCompatibleQualities();
		assertEquals(1, qualities.size());
	}

	@Test
	public void testAddMultipleQualities() {
		Storage storage = new Storage("A1", 100);

		storage.addCompatibleQuality(Stock.SpecialQuality.FLAMMABLE);
		storage.addCompatibleQuality(Stock.SpecialQuality.LIQUID);
		storage.addCompatibleQuality(Stock.SpecialQuality.PERISHABLE);

		List<Stock.SpecialQuality> qualities = storage.getCompatibleQualities();
		assertEquals(3, qualities.size());
	}
}