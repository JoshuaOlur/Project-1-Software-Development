package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.view;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.StockLogger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * Code-behind for the View Stock Changes page. Handles displaying and filtering
 * stock changes from the log file.
 * 
 * @author JO
 * @version Fall 2025
 */
public class ViewStockChangesCodeBehind {

	@FXML
	private CheckBox filterFlammableCheckBox;

	@FXML
	private CheckBox filterLiquidCheckBox;

	@FXML
	private CheckBox filterPerishableCheckBox;

	@FXML
	private ComboBox<String> crewMateComboBox;

	@FXML
	private DatePicker startDatePicker;

	@FXML
	private DatePicker endDatePicker;

	@FXML
	private Button applyTimeFilterButton;

	@FXML
	private Button applyFiltersButton;

	@FXML
	private Button clearFiltersButton;

	@FXML
	private Label errorLabel;

	@FXML
	private ListView<String> stockChangesListView;

	@FXML
	private Label detailsLabel;

	@FXML
	private Button backButton;

	@FXML
	private Button refreshButton;

	/**
	 * Initializes the View Stock Changes page.
	 */
	@FXML
	private void initialize() {
		this.setupCrewMateComboBox();
		this.setupEventHandlers();
		this.loadStockChanges();
	}

	/**
	 * Sets up the CrewMate combo box with usernames.
	 */
	private void setupCrewMateComboBox() {
		this.crewMateComboBox.getItems().addAll("All Users", "crew1", "qm1");
		this.crewMateComboBox.setValue("All Users");
	}

	/**
	 * Sets up event handlers for interactive elements.
	 */
	private void setupEventHandlers() {
		this.stockChangesListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					this.updateDetailsLabel(newValue);
				});
	}

	/**
	 * Loads stock changes from the log file.
	 */
	private void loadStockChanges() {
		List<String> stockChanges = StockLogger.readStockChanges();
		this.stockChangesListView.getItems().setAll(stockChanges);
	}

	/**
	 * Updates the details label with the selected stock change.
	 * 
	 * @param selectedItem item currently selected
	 */
	private void updateDetailsLabel(String selectedItem) {
		if (selectedItem != null) {
			this.detailsLabel.setText(selectedItem);
		} else {
			this.detailsLabel.setText("Select a stock change to view details");
		}
	}

	/**
	 * Handles applying the time filter.
	 */
	@FXML
	private void handleApplyTimeFilter() {
		if (this.startDatePicker.getValue() != null && this.endDatePicker.getValue() != null) {
			if (this.endDatePicker.getValue().isBefore(this.startDatePicker.getValue())) {
				this.errorLabel.setText("Error: End date must be after start date.");
				return;
			}
		}
		this.errorLabel.setText("");
		this.applyFilters();
	}

	/**
	 * Handles applying all active filters.
	 */
	@FXML
	private void handleApplyFilters() {
		this.applyFilters();
	}

	/**
	 * Handles clearing all filters.
	 */
	@FXML
	private void handleClearFilters() {
		this.filterFlammableCheckBox.setSelected(false);
		this.filterLiquidCheckBox.setSelected(false);
		this.filterPerishableCheckBox.setSelected(false);
		this.crewMateComboBox.setValue("All Users");
		this.startDatePicker.setValue(null);
		this.endDatePicker.setValue(null);
		this.errorLabel.setText("");
		this.loadStockChanges();
	}

	/**
	 * Handles refreshing the data.
	 */
	@FXML
	private void handleRefresh() {
		this.loadStockChanges();
		this.errorLabel.setText("Data refreshed successfully!");
	}

	/**
	 * Handles going back to the landing page.
	 */
	@FXML
	private void handleBack() {
		this.loadLandingPage();
	}

	/**
	 * Applies filters to the stock changes.
	 */
	private void applyFilters() {
		List<String> allChanges = StockLogger.readStockChanges();
		List<String> filteredChanges = allChanges;

		String selectedUser = this.crewMateComboBox.getValue();
		if (!"All Users".equals(selectedUser)) {
			filteredChanges = filteredChanges.stream().filter(change -> change.contains("User: " + selectedUser))
					.toList();
		}

		if (this.filterFlammableCheckBox.isSelected()) {
			filteredChanges = filteredChanges.stream().filter(change -> change.contains("FLAMMABLE")).toList();
		}
		if (this.filterLiquidCheckBox.isSelected()) {
			filteredChanges = filteredChanges.stream().filter(change -> change.contains("LIQUID")).toList();
		}
		if (this.filterPerishableCheckBox.isSelected()) {
			filteredChanges = filteredChanges.stream().filter(change -> change.contains("PERISHABLE")).toList();
		}

		this.stockChangesListView.getItems().setAll(filteredChanges);
		this.errorLabel.setText("Filters applied! Showing " + filteredChanges.size() + " entries.");
	}

	/**
	 * Loads the landing page scene.
	 */
	private void loadLandingPage() {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/edu/westga/cs3211/Pirate_Inventory_Ship_Manager/view/landingPage.fxml"));
			Scene landingPageScene = new Scene(loader.load());

			Stage stage = (Stage) this.backButton.getScene().getWindow();
			stage.setScene(landingPageScene);
			stage.show();
		} catch (Exception eE) {
			eE.printStackTrace();
		}
	}
}