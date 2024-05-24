package nl.nusayba.oose.domain.resources;

import nl.nusayba.oose.domain.dto.LoginDTO;
import nl.nusayba.oose.domain.services.LoginService;
import nl.nusayba.oose.resources.LoginResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LoginResourceTest {

    @InjectMocks
    private LoginResource loginResource;

    @Mock
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginSuccess() {
        LoginDTO loginDTO = new LoginDTO("user1", "password1");
        when(loginService.authenticate(loginDTO)).thenReturn("dummy-token-1");

        Response response = loginResource.login(loginDTO);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("dummy-token-1", response.getEntity());
    }

    @Test
    void testLoginUnauthorized() {
        LoginDTO loginDTO = new LoginDTO("user1", "wrongpassword");
        when(loginService.authenticate(loginDTO)).thenReturn(null);

        Response response = loginResource.login(loginDTO);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    void testLoginInternalServerError() {
        LoginDTO loginDTO = new LoginDTO("user1", "password1");
        when(loginService.authenticate(loginDTO)).thenThrow(new RuntimeException());

        Response response = loginResource.login(loginDTO);

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }
}
