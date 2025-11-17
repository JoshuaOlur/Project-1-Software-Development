package edu.westga.cs3211.Pirate_Inventory_Ship_Manager.test.Login;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.Pirate_Inventory_Ship_Manager.model.Login;

import static org.junit.jupiter.api.Assertions.*;

public class LoginConstructorTest {

    @Test
    public void testValidParams() {
        String username = "testUser";
        String password = "testPass";
        Login.UserRole role = Login.UserRole.CREWMATE;
        
        Login login = new Login(username, password, role);
        
        assertEquals(username, login.getUsername());
        assertEquals(password, login.getPassword());
        assertEquals(role, login.getRole());
    }

    @Test
    public void testNullUsername() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Login(null, "password", Login.UserRole.CREWMATE);
        });
    }

    @Test
    public void testNullPassword() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Login("username", null, Login.UserRole.CREWMATE);
        });
    }

    @Test
    public void testNullRole() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Login("username", "password", null);
        });
    }

    @Test
    public void testQuartermasterRole() {
        String username = "qmUser";
        String password = "qmPass";
        Login.UserRole role = Login.UserRole.QUARTERMASTER;
        
        Login login = new Login(username, password, role);
        
        assertEquals(username, login.getUsername());
        assertEquals(password, login.getPassword());
        assertEquals(role, login.getRole());
    }
}