package nl.nusayba.oose.datasource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.nusayba.oose.domain.dto.LoginDTO;
import nl.nusayba.oose.domain.dto.UserDTO;
import nl.nusayba.oose.domain.entities.User;
import nl.nusayba.oose.domain.interfaces.ILoginDAO;

import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class LoginDAO implements ILoginDAO {

    private Logger logger = Logger.getLogger(getClass().getName());

    @PersistenceContext(unitName = "spotitubePU")
    private EntityManager entityManager;

    @Override
    public LoginDTO getUserAndToken(LoginDTO request) {
        LoginDTO l = new LoginDTO();
        try {
            User user = entityManager.createQuery(
                            "SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                    .setParameter("username", request.getUser())
                    .setParameter("password", request.getPassword())
                    .getSingleResult();

            if (user != null) {
                l.setUser(user.getUsername());
                l.setPassword(user.getPassword());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching user with username and password", e);
        }
        return l;
    }

    @Override
    public UserDTO getUserByToken(String token) {
        UserDTO userDTO = null;
        try {
            User user = entityManager.createQuery(
                            "SELECT u FROM User u WHERE u.token = :token", User.class)
                    .setParameter("token", token)
                    .getSingleResult();

            if (user != null) {
                userDTO = new UserDTO();
                userDTO.setUser(user.getUsername());
                userDTO.setToken(user.getToken());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching user by token", e);
        }
        return userDTO;
    }

    @Override
    public UserDTO getUserAndToken(String username) {
        UserDTO u = new UserDTO();
        try {
            User user = entityManager.createQuery(
                            "SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();

            if (user != null) {
                u.setUser(user.getFullname());
                u.setToken(user.getToken());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching user and token by username", e);
        }
        return u;
    }

    private String generateToken(String username) {
        return username + "_token";  // Je eigen token logica hier
    }
}
