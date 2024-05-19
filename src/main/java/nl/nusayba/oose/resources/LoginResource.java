package nl.nusayba.oose.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.nusayba.oose.domain.dto.LoginRequestDTO;
import nl.nusayba.oose.domain.dto.LoginResponseDTO;
import nl.nusayba.oose.domain.services.LoginService;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {
    private LoginService loginService = new LoginService();


    //    @POST
//    public LoginResponseDTO login(LoginRequestDTO request) {
//        System.out.println(request.getPassword());
//
//        return null;
//    }
    @POST
    public Response login(LoginRequestDTO request) {
        System.out.println(request.getUser());
        System.out.println(request.getPassword());

        LoginResponseDTO response = loginService.authenticate(request);
        if (response != null) {
            return Response.ok(response).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
