package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Login;

/**
 * ViewModel for handling user login.
 * 
 * Supports the Login Use Case by storing username and password input, verifying
 * credentials, and exposing appropriate UI messages.
 * 
 * 
 * @author JO
 * @version Fall 2025
 */
public class LoginViewModel {

	private StringProperty usernameProperty;
	private StringProperty passwordProperty;
	private StringProperty errorMessageProperty;
	private Login.UserRole currentUserRole;
	private String currentUsername;

	/**
	 * Creates a new LoginViewModel. Initializes properties and sample user
	 * accounts.
	 */
	public LoginViewModel() {
		this.usernameProperty = new SimpleStringProperty("");
		this.passwordProperty = new SimpleStringProperty("");
		this.errorMessageProperty = new SimpleStringProperty("");
		this.currentUserRole = null;
		this.currentUsername = null;
	}

	/**
	 * Attempts to authenticate the user using provided credentials.
	 * 
	 * @precondition none
	 * @postcondition - If login successful: errorMessageProperty is empty - If
	 *                login fails: errorMessageProperty contains an explanation
	 * 
	 * @return the user's role if login succeeded; null otherwise
	 */
	public Login.UserRole attemptLogin() {

		String username = this.usernameProperty.get();
		String password = this.passwordProperty.get();

		if (username.isEmpty() || password.isEmpty()) {
			this.errorMessageProperty.set("Please enter both username and password.");
			return null;
		}

		if (username.equals("crew1") && password.equals("password")) {
			this.currentUserRole = Login.UserRole.CREWMATE;
			this.currentUsername = username;
			this.errorMessageProperty.set("");
			return this.currentUserRole;
		}

		if (username.equals("qm1") && password.equals("password")) {
			this.currentUserRole = Login.UserRole.QUARTERMASTER;
			this.currentUsername = username;
			this.errorMessageProperty.set("");
			return this.currentUserRole;
		}

		this.errorMessageProperty.set("Invalid username or password.");
		return null;
	}

	/**
	 * Clears the login state (for logout).
	 */
	public void clearLogin() {
		this.currentUserRole = null;
		this.currentUsername = null;
		this.usernameProperty.set("");
		this.passwordProperty.set("");
		this.errorMessageProperty.set("");
	}

	/**
	 * Returns the username property.
	 * 
	 * @return username property
	 */
	public StringProperty usernameProperty() {
		return this.usernameProperty;
	}

	/**
	 * Returns the password property.
	 * 
	 * @return password property
	 */
	public StringProperty passwordProperty() {
		return this.passwordProperty;
	}

	/**
	 * Returns the error message property.
	 * 
	 * @return error message property
	 */
	public StringProperty errorMessageProperty() {
		return this.errorMessageProperty;
	}

	/**
	 * Returns the current logged-in username.
	 * 
	 * @return the current username
	 */
	public String getCurrentUsername() {
		return this.currentUsername;
	}
}