package nl.nusayba.oose.domain.services;

import nl.nusayba.oose.domain.dto.LoginRequestDTO;
import nl.nusayba.oose.domain.dto.LoginResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    private LoginService loginService = new LoginService();

    @Test
    public void testValidLogin() {
        LoginRequestDTO request = new LoginRequestDTO();
        request.setUser("user");
        request.setPassword("password");

        LoginResponseDTO response = loginService.authenticate(request);
        assertNotNull(response);
        assertEquals("user", response.getUser());
        assertEquals("dummy-token", response.getToken());
    }

    @Test
    public void testInvalidLogin() {
        LoginRequestDTO request = new LoginRequestDTO();
        request.setUser("invalidUser");
        request.setPassword("invalidPassword");

        LoginResponseDTO response = loginService.authenticate(request);
        assertNull(response);
    }
}
