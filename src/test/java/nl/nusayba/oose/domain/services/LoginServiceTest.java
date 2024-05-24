package nl.nusayba.oose.domain.services;

import nl.nusayba.oose.datasource.LoginDAO;
import nl.nusayba.oose.domain.dto.LoginDTO;
import nl.nusayba.oose.domain.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginServiceTest {
    private LoginService sut;
    private LoginDAO fixture;

    @BeforeEach
    public void setup() {
        sut = new LoginService();
        fixture = Mockito.mock(LoginDAO.class);
        sut.setLoginDAO(fixture);
    }

    @Test
    public void loginUserTest(){
        // Arrange
        UserDTO returnValue = new UserDTO();
        returnValue.setToken("1234-1234-1234");
        returnValue.setUser("meron");

        LoginDTO login = new LoginDTO();
        login.setUser("meron");
        login.setPassword("password");

        // Coverage
        login.getPassword();

        Mockito.when(fixture.getUserAndToken(Mockito.anyString())).thenReturn(returnValue);
        Mockito.when(fixture.getUserAndToken(Mockito.any(LoginDTO.class))).thenReturn(login);

        // Act
        UserDTO result = sut.authenticate(login);

        // Assert
        assertEquals(returnValue.getToken(), result.getToken());
    }
}