package nl.nusayba.oose.domain.resources;

import nl.nusayba.oose.domain.dto.LoginDTO;
import nl.nusayba.oose.domain.dto.UserDTO;
import nl.nusayba.oose.domain.services.LoginService;
import nl.nusayba.oose.resources.LoginResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginResourceTest {
    private LoginResource sut;
    private LoginService fixture;

    @BeforeEach
    public void setup() {
        sut = new LoginResource();
        fixture = Mockito.mock(LoginService.class);
        sut.setLoginService(fixture);
    }

    @Test
    public void loginServiceTest(){
        // Arrange
        UserDTO returnValue = new UserDTO();
        returnValue.setToken("1234-1234-1234");
        returnValue.setUser("meron");
        Mockito.when(fixture.authenticate(Mockito.any(LoginDTO.class))).thenReturn(returnValue);

        // Act
        UserDTO result = sut.login(new LoginDTO());

        // Assert
        assertEquals(returnValue, result);
    }
}
