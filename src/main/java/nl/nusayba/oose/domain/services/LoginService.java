package nl.nusayba.oose.domain.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.nusayba.oose.domain.dto.UserDTO;
import nl.nusayba.oose.domain.exceptions.AuthenticationException;
import nl.nusayba.oose.domain.interfaces.ILoginDAO;


@ApplicationScoped
public class LoginService {

    @Inject
    private ILoginDAO loginDAO;

    public UserDTO authenticate(UserDTO request) {
        UserDTO user = loginDAO.getUserByUsername(request.getUser());
        if (user != null && user.getPassword().equals(request.getPassword())) {
            return user;
        } else {
            throw new AuthenticationException("Invalid username or password");
        }
    }

    public UserDTO getUserByUsername(String username) {
        return loginDAO.getUserByUsername(username);
    }

    public UserDTO getUserByToken(String token) {
        return loginDAO.getUserByToken(token);
    }

    public void addUser(UserDTO user) {
        loginDAO.insertUser(user);
    }

    public void updateUser(UserDTO user) {
        loginDAO.updateUser(user);
    }

    public void deleteUser(int id) {
        loginDAO.deleteUser(id);
    }
}