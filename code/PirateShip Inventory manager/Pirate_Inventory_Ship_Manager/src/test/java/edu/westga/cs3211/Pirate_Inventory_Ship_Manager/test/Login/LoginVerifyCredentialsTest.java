package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.test.Login;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Login;

import static org.junit.jupiter.api.Assertions.*;

public class LoginVerifyCredentialsTest {

    @Test
    public void testValidMatch() {
        Login login = new Login("testUser", "testPass", Login.UserRole.CREWMATE);
        
        boolean result = login.verifyCredentials("testUser", "testPass");
        
        assertTrue(result);
    }

    @Test
    public void testWrongUsername() {
        Login login = new Login("testUser", "testPass", Login.UserRole.CREWMATE);
        
        boolean result = login.verifyCredentials("wrongUser", "testPass");
        
        assertFalse(result);
    }

    @Test
    public void testWrongPassword() {
        Login login = new Login("testUser", "testPass", Login.UserRole.CREWMATE);
        
        boolean result = login.verifyCredentials("testUser", "wrongPass");
        
        assertFalse(result);
    }

    @Test
    public void testNullUsername() {
        Login login = new Login("testUser", "testPass", Login.UserRole.CREWMATE);
        
        boolean result = login.verifyCredentials(null, "testPass");
        
        assertFalse(result);
    }

    @Test
    public void testNullPassword() {
        Login login = new Login("testUser", "testPass", Login.UserRole.CREWMATE);
        
        boolean result = login.verifyCredentials("testUser", null);
        
        assertFalse(result);
    }

    @Test
    public void testBothNull() {
        Login login = new Login("testUser", "testPass", Login.UserRole.CREWMATE);
        
        boolean result = login.verifyCredentials(null, null);
        
        assertFalse(result);
    }

    @Test
    public void testCaseSensitive() {
        Login login = new Login("TestUser", "TestPass", Login.UserRole.CREWMATE);
        
        boolean result = login.verifyCredentials("testuser", "testpass");
        
        assertFalse(result);
    }
}