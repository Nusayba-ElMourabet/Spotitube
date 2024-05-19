package nl.nusayba.oose.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import nl.nusayba.oose.dto.LoginRequestDTO;
import nl.nusayba.oose.dto.LoginResponseDTO;

import java.util.List;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    @POST
    public LoginResponseDTO login(LoginRequestDTO request) {
        System.out.println(request.getPassword());

        return null;
    }
}
