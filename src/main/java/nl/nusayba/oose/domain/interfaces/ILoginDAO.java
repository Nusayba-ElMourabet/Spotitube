package nl.nusayba.oose.domain.interfaces;

import nl.nusayba.oose.domain.dto.LoginDTO;
import nl.nusayba.oose.domain.dto.UserDTO;

public interface ILoginDAO {
    LoginDTO getUserAndToken(LoginDTO request);
    UserDTO getUserByToken(String token);

    UserDTO getUserAndToken(String user);
}