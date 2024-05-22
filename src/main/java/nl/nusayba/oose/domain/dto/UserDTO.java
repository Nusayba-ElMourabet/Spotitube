package nl.nusayba.oose.domain.dto;

import java.awt.*;

public class UserDTO {
    private String user;
    private String token;
    private String password;

    // Getters and setters

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
