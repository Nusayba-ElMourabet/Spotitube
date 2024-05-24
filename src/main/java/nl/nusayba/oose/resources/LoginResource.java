package nl.nusayba.oose.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.nusayba.oose.domain.dto.LoginDTO;
import nl.nusayba.oose.domain.dto.UserDTO;
import nl.nusayba.oose.domain.exceptions.AuthenticationException;
import nl.nusayba.oose.domain.services.LoginService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/login")
public class LoginResource {

    @Inject
    private LoginService loginService;

    @POST
    public UserDTO login(LoginDTO request) {
        return loginService.authenticate(request);
    }
}

