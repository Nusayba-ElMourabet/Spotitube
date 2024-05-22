package nl.nusayba.oose.datasource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.nusayba.oose.domain.dto.UserDTO;
import nl.nusayba.oose.domain.interfaces.ILoginDAO;
import nl.nusayba.oose.util.DatabaseProperties;

import java.sql.*;

@ApplicationScoped
public class LoginDAO implements ILoginDAO {


    @Inject
    private DatabaseProperties dbProperties;

    private String url;
    private String username;
    private String password;

    @Inject
    public void init() {
        this.url = dbProperties.getUrl();
        this.username = dbProperties.getUsername();
        this.password = dbProperties.getPassword();
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        UserDTO user = null;
        try (Connection connection = DriverManager.getConnection(url, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ?")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserDTO();
                    user.setUser(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setToken(resultSet.getString("token"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public UserDTO getUserByToken(String token) {
        UserDTO user = null;
        try (Connection connection = DriverManager.getConnection(url, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE token = ?")) {
            statement.setString(1, token);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserDTO();
                    user.setUser(resultSet.getString("username"));
                    user.setToken(resultSet.getString("token"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void insertUser(UserDTO user) {
        String token = generateToken(user.getUser());
        try (Connection connection = DriverManager.getConnection(url, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (username, password, token) VALUES (?, ?, ?)")) {
            statement.setString(1, user.getUser());
            statement.setString(2, user.getPassword());
            statement.setString(3, token);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateToken(String username) {
        return username + "_token"; // Replace with your token generation logic
    }

    @Override
    public void updateUser(UserDTO user) {
        try (Connection connection = DriverManager.getConnection(url, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement("UPDATE Users SET username = ?, password = ?, token = ? WHERE username = ?")) {
            statement.setString(1, user.getUser());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getToken());
            statement.setString(4, user.getUser());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        try (Connection connection = DriverManager.getConnection(url, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Users WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}