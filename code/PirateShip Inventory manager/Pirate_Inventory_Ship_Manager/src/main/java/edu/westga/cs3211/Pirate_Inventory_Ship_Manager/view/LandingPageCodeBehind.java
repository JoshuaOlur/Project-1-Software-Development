package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.view;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Code-behind for the landing page. Handles visibility of buttons and navigates
 * based on user role.
 * 
 * @author JO
 * @version Fall 2025
 */
public class LandingPageCodeBehind {

	@FXML
	private Button addStockButton;

	@FXML
	private Button viewStockChangesButton;

	@FXML
	private Button logoutButton;

	private Login.UserRole userRole;

	/**
	 * Creates a new LandingPageCodeBehind instance.
	 */
	public LandingPageCodeBehind() {
		
	}

	/**
	 * Sets the user role and updates button visibility.
	 * 
	 * @param role the role of the logged-in user
	 */
	public void setUserRole(Login.UserRole role) {
		this.userRole = role;
		this.updateButtonVisibility();
	}

	/**
	 * Initializes the landing page and controls visibility of buttons.
	 */
	@FXML
	private void initialize() {
		
	}

	/**
	 * Updates button visibility based on the user's role.
	 */
	private void updateButtonVisibility() {
		if (this.userRole == Login.UserRole.QUARTERMASTER) {
			this.viewStockChangesButton.setVisible(true);
		} else {
			this.viewStockChangesButton.setVisible(false);
		}

		this.addStockButton.setVisible(true);
	}

	/**
	 * Handles the action of clicking the "Add Stock" button. 
	 * Navigates to the Add Stock page.
	 */
	@FXML
	private void handleAddStock() {
		this.loadAddStockPage();
	}

	/**
	 * Handles the action of clicking the "View Stock Changes" button. 
	 * Navigates to the View Stock Changes page.
	 */
	@FXML
	private void handleViewStockChanges() {
		this.loadViewStockChangesPage();
	}

	/**
	 * Handles the action of clicking the "Logout" button. Logs the user out and
	 * navigates back to the login page.
	 */
	@FXML
	private void handleLogout() {
		this.loadLoginPage();
	}

	/**
	 * Loads the Add Stock page.
	 */
	private void loadAddStockPage() {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/edu/westga/cs3211/Pirate_Inventory_Ship_Manager/view/AddStockPage.fxml"));
			Scene addStockScene = new Scene(loader.load());

			Stage stage = (Stage) this.addStockButton.getScene().getWindow();
			stage.setScene(addStockScene);
			stage.show();
		} catch (Exception eE) {
			eE.printStackTrace();
		}
	}

	/**
	 * Loads the View Stock Changes page.
	 */
	private void loadViewStockChangesPage() {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/edu/westga/cs3211/Pirate_Inventory_Ship_Manager/view/ViewStockChangesPage.fxml"));
			Scene viewStockChangesScene = new Scene(loader.load());

			Stage stage = (Stage) this.viewStockChangesButton.getScene().getWindow();
			stage.setScene(viewStockChangesScene);
			stage.show();
		} catch (Exception eE) {
			eE.printStackTrace();
		}
	}

	/**
	 * Loads the login page scene after logout.
	 */
	private void loadLoginPage() {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/edu/westga/cs3211/Pirate_Inventory_Ship_Manager/view/initialPage.fxml"));
			Scene loginPageScene = new Scene(loader.load());

			Stage stage = (Stage) this.logoutButton.getScene().getWindow();
			stage.setScene(loginPageScene);
			stage.show();
		} catch (Exception eE) {
			eE.printStackTrace();
		}
	}
}