package nl.nusayba.oose.domain.interfaces;

import nl.nusayba.oose.domain.dto.UserDTO;

public interface ILoginDAO {
    UserDTO getUserByUsername(String username);
    UserDTO getUserByToken(String token);
    void insertUser(UserDTO user);
    void updateUser(UserDTO user);
    void deleteUser(int id);
}