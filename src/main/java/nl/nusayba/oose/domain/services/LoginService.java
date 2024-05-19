package nl.nusayba.oose.domain.services;


import nl.nusayba.oose.domain.dto.LoginRequestDTO;
import nl.nusayba.oose.domain.dto.LoginResponseDTO;

public class LoginService {

    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    public LoginResponseDTO authenticate(LoginRequestDTO request) {
        if (USERNAME.equals(request.getUser()) && PASSWORD.equals(request.getPassword())) {
            LoginResponseDTO response = new LoginResponseDTO();
            response.setUser(request.getUser());
            response.setToken("dummy-token");
            return response;
        } else {
            return null;
        }
    }
}
