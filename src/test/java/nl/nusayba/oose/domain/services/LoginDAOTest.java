package nl.nusayba.oose.domain.services;

import jakarta.inject.Inject;
import nl.nusayba.oose.datasource.LoginDAO;
import nl.nusayba.oose.domain.dto.UserDTO;
import nl.nusayba.oose.domain.interfaces.ILoginDAO;
import nl.nusayba.oose.util.DatabaseProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;


class LoginDAOTest {

    private ILoginDAO loginDAO;

    @BeforeEach
    void setUp() {
        // Initialize the database for testing
        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://NUSELM\\SQLEXPRESS;databaseName=Spotitube", "your-username", "your-password");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
            statement.executeUpdate("CREATE TABLE Users (" +
                    "id INT IDENTITY NOT NULL PRIMARY KEY," +
                    "username VARCHAR(255) UNIQUE NOT NULL," +
                    "password VARCHAR(255) NOT NULL," +
                    "token VARCHAR(255) UNIQUE NOT NULL)");
            statement.executeUpdate("INSERT INTO Users (username, password, token) VALUES ('testuser', 'testpass', 'testtoken')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Manually set up the DAO
        loginDAO = new LoginDAO();
        loginDAO.setDbProperties(new DatabaseProperties(
                "jdbc:sqlserver://NUSELM\\SQLEXPRESS;databaseName=Spotitube",
                "your-username",
                "your-password"
        ));
    }

    @Test
    void testGetUserByUsername() {
        UserDTO user = loginDAO.getUserByUsername("testuser");
        assertNotNull(user);
        assertEquals("testuser", user.getUser());
        assertEquals("testpass", user.getPassword());
        assertEquals("testtoken", user.getToken());
    }

    @Test
    void testGetUserByToken() {
        UserDTO user = loginDAO.getUserByToken("testtoken");
        assertNotNull(user);
        assertEquals("testuser", user.getUser());
        assertEquals("testpass", user.getPassword());
        assertEquals("testtoken", user.getToken());
    }

    @Test
    void testInsertUser() {
        UserDTO newUser = new UserDTO();
        newUser.setUser("newuser");
        newUser.setPassword("newpass");
        loginDAO.insertUser(newUser);

        UserDTO user = loginDAO.getUserByUsername("newuser");
        assertNotNull(user);
        assertEquals("newuser", user.getUser());
        assertEquals("newpass", user.getPassword());
        assertNotNull(user.getToken());
    }

    @Test
    void testUpdateUser() {
        UserDTO user = loginDAO.getUserByUsername("testuser");
        user.setPassword("newpass");
        user.setToken("newtoken");
        loginDAO.updateUser(user);

        UserDTO updatedUser = loginDAO.getUserByUsername("testuser");
        assertEquals("newpass", updatedUser.getPassword());
        assertEquals("newtoken", updatedUser.getToken());
    }

//    @Test
//    void testDeleteUser() {
//        UserDTO user = loginDAO.getUserByUsername("testuser");
//        loginDAO.deleteUser(user.getId());
//
//        UserDTO deletedUser = loginDAO.getUserByUsername("testuser");
//        assertNull(deletedUser);
//    }
}