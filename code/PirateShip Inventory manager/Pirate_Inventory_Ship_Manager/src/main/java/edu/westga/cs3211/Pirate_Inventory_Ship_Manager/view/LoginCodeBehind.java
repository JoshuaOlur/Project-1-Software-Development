package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.view;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.viewmodel.LoginViewModel;
import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Code-behind for the login screen. Handles login logic and scene switching.
 * 
 * @author CS
 * @version Fall 2025
 */
public class LoginCodeBehind {

	@FXML
	private TextField usernameTextField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button loginButton;

	@FXML
	private Label errorLabel;

	private LoginViewModel viewModel;

	/**
	 * Creates a new LoginCodeBehind instance and sets up the ViewModel.
	 */
	public LoginCodeBehind() {
		this.viewModel = new LoginViewModel();
	}

	/**
	 * Initializes the login screen and binds the components to the ViewModel.
	 */
	@FXML
	private void initialize() {

		this.usernameTextField.textProperty().bindBidirectional(this.viewModel.usernameProperty());
		this.passwordField.textProperty().bindBidirectional(this.viewModel.passwordProperty());
		this.errorLabel.textProperty().bind(this.viewModel.errorMessageProperty());
	}

	/**
	 * Handles the login button click. If login is successful, navigates to the
	 * landing page.
	 */
	@FXML
	private void handleLogin() {
		Login.UserRole role = this.viewModel.attemptLogin();

		if (role == null) {
			return;
		}

		this.loadLandingPage(role);
	}

	/**
	 * Loads the LandingPage scene after successful login.
	 * 
	 * @param role the role of the logged-in user
	 */
	private void loadLandingPage(Login.UserRole role) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/edu/westga/cs3211/Pirate_Inventory_Ship_Manager/view/landingPage.fxml"));
			Scene landingPageScene = new Scene(loader.load());

			LandingPageCodeBehind controller = loader.getController();
			controller.setUserRole(role);
			controller.setLoginViewModel(this.viewModel);

			Stage stage = (Stage) this.loginButton.getScene().getWindow();
			stage.setScene(landingPageScene);
			stage.show();
		} catch (Exception eE) {
			eE.printStackTrace();
		}
	}
}