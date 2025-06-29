package nl.nusayba.oose.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String token;
    private String fullname;

    public Users() {}

    // Getters en setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
}
