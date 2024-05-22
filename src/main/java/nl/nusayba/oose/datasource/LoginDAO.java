package nl.nusayba.oose.datasource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.nusayba.oose.domain.dto.LoginDTO;
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
    public LoginDTO getUserAndToken(LoginDTO request) {
        LoginDTO l = new LoginDTO();
        try {
            Connection connection = DriverManager.getConnection(dbProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?");
            statement.setString(1, request.getUser());
            statement.setString(2, request.getPassword());
                ResultSet resultset = statement.executeQuery();

                while (resultset.next()) {
                    l.setUser(resultset.getString("username"));
                    l.setPassword(resultset.getString("password"));
                }
                statement.close();
                connection.close();
            } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return l;
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
            System.out.println(e.getMessage());
        }
        return user;
    }

    private String generateToken(String username) {
        return username + "_token"; // Replace with your token generation logic
    }

    @Override
    public UserDTO getUserAndToken(String user) {
        UserDTO u = new UserDTO();
        try {
            Connection connection = DriverManager.getConnection(dbProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT fullname, token from Users where username = ?");
            statement.setString(1, user);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                u.setUser(resultSet.getString("fullname"));
                u.setToken(resultSet.getString("token"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println(u.getToken() +  u.getUser());
        return u;
    }
}