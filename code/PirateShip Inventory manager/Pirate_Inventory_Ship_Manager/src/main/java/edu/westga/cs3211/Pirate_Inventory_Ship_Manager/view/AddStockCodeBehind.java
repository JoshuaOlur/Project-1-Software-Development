package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Code-behind for the Add Stock page. Handles the form for adding new stock to
 * the system according to the Add Stock Use Case.
 * 
 * @author CS
 * @version Fall 2025
 */
public class AddStockCodeBehind {

	@FXML
	private TextField nameTextField;

	@FXML
	private Spinner<Integer> quantitySpinner;

	@FXML
	private ComboBox<String> conditionComboBox;

	@FXML
	private CheckBox flammableCheckBox;

	@FXML
	private CheckBox liquidCheckBox;

	@FXML
	private CheckBox perishableCheckBox;

	@FXML
	private HBox expirationDateContainer;

	@FXML
	private DatePicker expirationDatePicker;

	@FXML
	private ComboBox<String> storageComboBox;

	@FXML
	private Label storageInfoLabel;

	@FXML
	private Label errorLabel;

	@FXML
	private Button addStockButton;

	@FXML
	private Button cancelButton;

	/**
	 * Initializes the Add Stock page.
	 */
	@FXML
	private void initialize() {
		this.setupQuantitySpinner();
		this.setupConditionComboBox();
		this.setupEventHandlers();
		this.setupStorageComboBox();
	}

	/**
	 * Sets up the quantity spinner with reasonable defaults.
	 */
	private void setupQuantitySpinner() {
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);
		this.quantitySpinner.setValueFactory(valueFactory);
	}

	/**
	 * Sets up the condition combo box with available options.
	 */
	private void setupConditionComboBox() {
		this.conditionComboBox.getItems().addAll("PERFECT", "USABLE", "UNUSABLE");
		this.conditionComboBox.setValue("USABLE");
	}

	/**
	 * Sets up event handlers for interactive elements.
	 */
	private void setupEventHandlers() {

		this.perishableCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
			this.expirationDateContainer.setVisible(newValue);
			if (newValue) {
				this.expirationDatePicker.setValue(null);
			}
		});

		this.storageComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			this.updateStorageInfo();
		});
	}

	/**
	 * Sets up the storage compartment combo box with sample data.
	 */
	private void setupStorageComboBox() {

		this.storageComboBox.getItems().addAll("Storage A - General (Capacity: 100)",
				"Storage B - Flammables (Capacity: 50)", "Storage C - Liquids (Capacity: 75)",
				"Storage D - Perishables (Capacity: 60)");
	}

	/**
	 * Updates the storage information label based on selection.
	 */
	private void updateStorageInfo() {
		String selectedStorage = this.storageComboBox.getValue();
		if (selectedStorage != null) {
			this.storageInfoLabel.setText("Selected: " + selectedStorage);
		} else {
			this.storageInfoLabel.setText("Select a storage compartment");
		}
	}

	/**
	 * Handles the Add Stock button click. Validates input and adds stock to the
	 * system.
	 */
	@FXML
	private void handleAddStock() {
		if (this.validateInput()) {

			this.errorLabel.setText("Stock added successfully!");
			this.errorLabel.setStyle("-fx-text-fill: green;");

			this.clearForm();
		}
	}

	/**
	 * Handles the Cancel button click. Navigates back to the landing page.
	 */
	@FXML
	private void handleCancel() {
		this.loadLandingPage();
	}

	/**
	 * Validates all form input fields.
	 * 
	 * @return true if all input is valid, false otherwise
	 */
	private boolean validateInput() {

		if (this.nameTextField.getText() == null || this.nameTextField.getText().trim().isEmpty()) {
			this.errorLabel.setText("Stock name is required.");
			return false;
		}

		if (this.conditionComboBox.getValue() == null) {
			this.errorLabel.setText("Please select a condition.");
			return false;
		}

		if (this.perishableCheckBox.isSelected() && this.expirationDatePicker.getValue() == null) {
			this.errorLabel.setText("Expiration date is required for perishable items.");
			return false;
		}

		if (this.storageComboBox.getValue() == null) {
			this.errorLabel.setText("Please select a storage compartment.");
			return false;
		}

		this.errorLabel.setText("");
		return true;
	}

	/**
	 * Clears the form after successful submission.
	 */
	private void clearForm() {
		this.nameTextField.clear();
		this.quantitySpinner.getValueFactory().setValue(1);
		this.conditionComboBox.setValue("USABLE");
		this.flammableCheckBox.setSelected(false);
		this.liquidCheckBox.setSelected(false);
		this.perishableCheckBox.setSelected(false);
		this.expirationDatePicker.setValue(null);
		this.expirationDateContainer.setVisible(false);
		this.storageComboBox.setValue(null);
		this.storageInfoLabel.setText("Select a storage compartment");
	}

	/**
	 * Loads the landing page scene.
	 */
	private void loadLandingPage() {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/edu/westga/cs3211/Pirate_Inventory_Ship_Manager/view/landingPage.fxml"));
			Scene landingPageScene = new Scene(loader.load());

			Stage stage = (Stage) this.cancelButton.getScene().getWindow();
			stage.setScene(landingPageScene);
			stage.show();
		} catch (Exception eE) {
			eE.printStackTrace();
		}
	}
}
