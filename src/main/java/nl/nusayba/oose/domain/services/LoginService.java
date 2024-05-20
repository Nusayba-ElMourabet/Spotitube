package nl.nusayba.oose.domain.services;

import nl.nusayba.oose.domain.dto.LoginRequestDTO;
import nl.nusayba.oose.domain.dto.LoginResponseDTO;
import nl.nusayba.oose.domain.exceptions.AuthenticationException;

public class LoginService {

    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";
    private static final String TOKEN = "dummy-token"; // Ensure this matches the token used in subsequent requests

    public LoginResponseDTO authenticate(LoginRequestDTO request) {
        if (USERNAME.equals(request.getUser()) && PASSWORD.equals(request.getPassword())) {
            LoginResponseDTO response = new LoginResponseDTO();
            response.setUser(request.getUser());
            response.setToken(TOKEN);
            return response;
        } else {
            throw new AuthenticationException("Invalid username or password");
        }
    }
}
