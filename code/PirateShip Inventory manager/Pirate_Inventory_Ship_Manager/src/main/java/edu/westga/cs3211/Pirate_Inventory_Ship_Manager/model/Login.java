package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model;

/**
 * Represents login credentials for a system user. 
 * 
 * The Login class supports the Login Use Case by storing a username and password
 * and providing behavior for verifying whether provided credentials are valid.
 * System roles (Crewmate or Quartermaster) may be tied to the username.
 * 
 * 
 * @author JO 
 * @version Fall 2025
 */
public class Login {

    private static final String USERNAME_CANNOT_BE_NULL = "username cannot be null.";
    private static final String PASSWORD_CANNOT_BE_NULL = "password cannot be null.";

    private String username;
    private String password;
    private UserRole role;

    /**
     * Enumerates the roles that a system user may have.
     * A Crewmate can add stock.  
     * A Quartermaster can view stock changes.  
     */
    public enum UserRole {
        CREWMATE,
        QUARTERMASTER
    }

    /**
     * Creates login credentials for a user.
     * 
     * @precondition username != null && password != null && role != null
     * @postcondition getUsername() == username &&
     *                getPassword() == password &&
     *                getRole() == role
     * 
     * @param username the username used for login
     * @param password the password used for login
     * @param role     the role assigned to this login
     * 
     * @throws IllegalArgumentException if username or password is null
     */
    public Login(String username, String password, UserRole role) {
        if (username == null) {
            throw new IllegalArgumentException(USERNAME_CANNOT_BE_NULL);
        }
        if (password == null) {
            throw new IllegalArgumentException(PASSWORD_CANNOT_BE_NULL);
        }
        if (role == null) {
            throw new IllegalArgumentException("role cannot be null.");
        }

        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Returns the username for this login object.
     * 
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Returns the password for this login object.
     * Note: This is stored as plain text for assignment simplicity.
     * 
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns the system role for this login account.
     * 
     * @return the user's role (Crewmate or Quartermaster)
     */
    public UserRole getRole() {
        return this.role;
    }

    /**
     * Determines whether a provided username/password matches this Login object.
     * 
     * This supports the login verification step in the Login Use Case.
     * 
     * 
     * @param usernameAttempt the username entered by the user
     * @param passwordAttempt the password entered by the user
     * 
     * @return true if both usernameAttempt and passwordAttempt match the stored values,
     *         false otherwise
     */
    public boolean verifyCredentials(String usernameAttempt, String passwordAttempt) {
        if (usernameAttempt == null || passwordAttempt == null) {
            return false;
        }

        return this.username.equals(usernameAttempt) && this.password.equals(passwordAttempt);
    }
}

