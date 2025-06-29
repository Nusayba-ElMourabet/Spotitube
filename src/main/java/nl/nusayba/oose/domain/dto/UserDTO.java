package nl.nusayba.oose.domain.dto;

import java.awt.*;

public class UserDTO {
    private String user;
    private String fullname;
    private String password;

    private int id;
    private String token;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
