package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.view;

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

/**
 * Code-behind for the View Stock Changes page. Handles displaying and filtering
 * stock changes according to the View Stock Changes Use Case.
 * 
 * @author CS
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
		this.loadSampleData();
	}

	/**
	 * Sets up the CrewMate combo box with sample data.
	 */
	private void setupCrewMateComboBox() {
		this.crewMateComboBox.getItems().addAll("All CrewMates", "Jack Sparrow", "Will Turner", "Elizabeth Swann",
				"Joshamee Gibbs");
		this.crewMateComboBox.setValue("All CrewMates");
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
	 * Loads sample data for demonstration.
	 */
	private void loadSampleData() {
		this.stockChangesListView.getItems().addAll(
				"[2024-01-15 10:30] Jack Sparrow added 5 x Rum to Storage C (Condition: PERFECT, Qualities: [LIQUID])",
				"[2024-01-15 09:15] Will Turner added 10 x Cannonballs to Storage A (Condition: USABLE, Qualities: [])",
				"[2024-01-14 16:45] Elizabeth Swann added 3 x Medical Supplies to Storage D (Condition: PERFECT, Qualities: [PERISHABLE])",
				"[2024-01-14 14:20] Joshamee Gibbs added 8 x Gunpowder to Storage B (Condition: USABLE, Qualities: [FLAMMABLE])",
				"[2024-01-13 11:10] Jack Sparrow added 15 x Food Rations to Storage D (Condition: USABLE, Qualities: [PERISHABLE])");
	}

	/**
	 * Updates the details label with information about the selected stock change.
	 * 
	 * @param selectedItem the selected stock change string
	 */
	private void updateDetailsLabel(String selectedItem) {
		if (selectedItem != null) {
			this.detailsLabel.setText("Full details: " + selectedItem);
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
			if (this.endDatePicker.getValue().isBefore(this.startDatePicker.getValue())
					|| this.endDatePicker.getValue().equals(this.startDatePicker.getValue())) {
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
		this.crewMateComboBox.setValue("All CrewMates");
		this.startDatePicker.setValue(null);
		this.endDatePicker.setValue(null);
		this.errorLabel.setText("");

		this.stockChangesListView.getItems().clear();
		this.loadSampleData();
	}

	/**
	 * Handles refreshing the data.
	 */
	@FXML
	private void handleRefresh() {
		this.stockChangesListView.getItems().clear();
		this.loadSampleData();
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
	 * Applies all active filters to the data.
	 */
	private void applyFilters() {

		this.errorLabel.setText("Filters applied! (Sample implementation)");

		this.stockChangesListView.getItems().clear();
		this.loadSampleData();
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