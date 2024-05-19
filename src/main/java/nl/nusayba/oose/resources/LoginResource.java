package nl.nusayba.oose.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.nusayba.oose.domain.dto.LoginRequestDTO;
import nl.nusayba.oose.domain.dto.LoginResponseDTO;
import nl.nusayba.oose.domain.exceptions.AuthenticationException;
import nl.nusayba.oose.domain.services.LoginService;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {
    private LoginService loginService = new LoginService();


    @POST
    public Response login(LoginRequestDTO request) {
        try {
            LoginResponseDTO response = loginService.authenticate(request);
            return Response.ok(response).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
