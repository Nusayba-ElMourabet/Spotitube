package nl.nusayba.oose.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.nusayba.oose.domain.dto.UserDTO;
import nl.nusayba.oose.domain.exceptions.AuthenticationException;
import nl.nusayba.oose.domain.services.LoginService;

@Path("/login")
public class LoginResource {

    @Inject
    private LoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO loginRequest) {
        try {
            UserDTO user = loginService.authenticate(loginRequest);
            return Response.ok(user).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(UserDTO user) {
        loginService.addUser(user);
        return Response.status(Response.Status.CREATED).build();
    }
}

