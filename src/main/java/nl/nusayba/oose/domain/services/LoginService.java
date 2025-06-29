package nl.nusayba.oose.domain.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.nusayba.oose.domain.dto.LoginDTO;
import nl.nusayba.oose.domain.dto.UserDTO;
import nl.nusayba.oose.domain.exceptions.AuthenticationException;
import nl.nusayba.oose.domain.interfaces.ILoginDAO;


@ApplicationScoped
public class LoginService {

    private ILoginDAO loginDAO;
    @Inject
    void setLoginDAO(ILoginDAO loginDAO){
        this.loginDAO = loginDAO;
    }

    public UserDTO authenticate(LoginDTO request) {
            LoginDTO received = loginDAO.getUserAndToken(request);
        if (received.getUser() != null || received.getPassword() != null) {
            return loginDAO.getUserAndToken(request.getUser());
        } else {
            throw new AuthenticationException();
        }
    }

    public UserDTO getUserByToken(String token) {
        return loginDAO.getUserByToken(token);
    }
}
